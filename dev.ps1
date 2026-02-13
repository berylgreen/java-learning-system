param(
    [ValidateSet('start', 'stop', 'restart', 'status')]
    [string]$Action = 'status'
)

$ErrorActionPreference = 'Stop'

$ProjectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$LogDir = Join-Path $env:TEMP '7antigravity-test34\logs'
New-Item -ItemType Directory -Path $LogDir -Force | Out-Null

$Services = @(
    @{
        Name = 'sandbox-service'
        Port = 8081
        Command = 'mvn -f "backend/sandbox-service/pom.xml" spring-boot:run'
    },
    @{
        Name = 'course-service'
        Port = 8082
        Command = 'mvn -f "backend/course-service/pom.xml" spring-boot:run'
    },
    @{
        Name = 'frontend'
        Port = 5173
        Command = 'npm --prefix "frontend" run dev'
    }
)

$ServiceMap = @{}
foreach ($item in $Services) {
    $ServiceMap[$item.Name] = $item
}

$StartOrder = @('sandbox-service', 'course-service', 'frontend')
$StopOrder = @('frontend', 'course-service', 'sandbox-service')

function Get-ListeningProcesses {
    param([int]$Port)

    $processIds = Get-NetTCPConnection -State Listen -LocalPort $Port -ErrorAction SilentlyContinue |
        Select-Object -ExpandProperty OwningProcess -Unique

    if (-not $processIds) {
        return @()
    }

    $result = @()
    foreach ($processId in $processIds) {
        $proc = Get-CimInstance Win32_Process -Filter "ProcessId=$processId" -ErrorAction SilentlyContinue
        if ($proc) {
            $result += $proc
        }
    }

    return @($result)
}

function Wait-PortState {
    param(
        [int]$Port,
        [bool]$ShouldListen,
        [int]$TimeoutSec = 60
    )

    $deadline = (Get-Date).AddSeconds($TimeoutSec)
    while ((Get-Date) -lt $deadline) {
        $isListening = [bool](Get-NetTCPConnection -State Listen -LocalPort $Port -ErrorAction SilentlyContinue)
        if ($isListening -eq $ShouldListen) {
            return $true
        }
        Start-Sleep -Milliseconds 500
    }

    return $false
}

function Show-ServiceStatus {
    param($Service)

    $name = $Service.Name
    $port = $Service.Port
    $listeners = Get-ListeningProcesses -Port $port

    if ($listeners.Count -eq 0) {
        Write-Host "[$name] STOPPED (port $port)"
        return
    }

    foreach ($listener in $listeners) {
        $cmd = $listener.CommandLine
        if ($cmd -and $cmd.Length -gt 140) {
            $cmd = $cmd.Substring(0, 140) + '...'
        }
        Write-Host "[$name] RUNNING (port $port, pid $($listener.ProcessId)) $cmd"
    }
}

function Start-ServiceProcess {
    param($Service)

    $name = $Service.Name
    $port = $Service.Port
    $command = $Service.Command

    $listeners = Get-ListeningProcesses -Port $port
    if ($listeners.Count -gt 0) {
        Write-Host "[$name] already running, skip start."
        return
    }

    $stdoutLog = Join-Path $LogDir "$name.out.log"
    $stderrLog = Join-Path $LogDir "$name.err.log"

    if (Test-Path $stdoutLog) { Remove-Item $stdoutLog -Force }
    if (Test-Path $stderrLog) { Remove-Item $stderrLog -Force }

    $proc = Start-Process -FilePath 'cmd.exe' `
        -ArgumentList '/c', $command `
        -WorkingDirectory $ProjectRoot `
        -WindowStyle Minimized `
        -RedirectStandardOutput $stdoutLog `
        -RedirectStandardError $stderrLog `
        -PassThru

    Write-Host "[$name] starting... pid=$($proc.Id)"

    if (Wait-PortState -Port $port -ShouldListen $true -TimeoutSec 90) {
        Write-Host "[$name] started (port $port)."
    } else {
        Write-Host "[$name] start timeout."
        Write-Host "  OUT: $stdoutLog"
        Write-Host "  ERR: $stderrLog"
    }
}

function Stop-ServiceProcess {
    param($Service)

    $name = $Service.Name
    $port = $Service.Port
    $listeners = Get-ListeningProcesses -Port $port

    if ($listeners.Count -eq 0) {
        Write-Host "[$name] not running, skip stop."
        return
    }

    foreach ($listener in $listeners) {
        Write-Host "[$name] stopping pid=$($listener.ProcessId)..."
        Stop-Process -Id $listener.ProcessId -Force -ErrorAction SilentlyContinue
    }

    if (Wait-PortState -Port $port -ShouldListen $false -TimeoutSec 20) {
        Write-Host "[$name] stopped (port $port)."
    } else {
        Write-Host "[$name] stop timeout, check process manually."
    }
}

switch ($Action) {
    'status' {
        foreach ($name in $StartOrder) {
            Show-ServiceStatus -Service $ServiceMap[$name]
        }
    }
    'start' {
        foreach ($name in $StartOrder) {
            Start-ServiceProcess -Service $ServiceMap[$name]
        }
        Write-Host 'Done. Current status:'
        foreach ($name in $StartOrder) {
            Show-ServiceStatus -Service $ServiceMap[$name]
        }
    }
    'stop' {
        foreach ($name in $StopOrder) {
            Stop-ServiceProcess -Service $ServiceMap[$name]
        }
        Write-Host 'Done. Current status:'
        foreach ($name in $StartOrder) {
            Show-ServiceStatus -Service $ServiceMap[$name]
        }
    }
    'restart' {
        foreach ($name in $StopOrder) {
            Stop-ServiceProcess -Service $ServiceMap[$name]
        }
        foreach ($name in $StartOrder) {
            Start-ServiceProcess -Service $ServiceMap[$name]
        }
        Write-Host 'Done. Current status:'
        foreach ($name in $StartOrder) {
            Show-ServiceStatus -Service $ServiceMap[$name]
        }
    }
}
