package com.qianfeng.test3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
public class Mark {
	public static void main(String[] args) {
		System.out.println("请五位评委打分\n(提示：输入五个0-10内的整数，使用逗号分隔：");
		Scanner sc = new Scanner(System.in); // 控制台输入
		String score = sc.next();
		String[] scores = score.split(",");
		// 控制台输入五个整数
		int score1 = Integer.valueOf(scores[0]);
		int score2 = Integer.valueOf(scores[1]);
		int score3 = Integer.valueOf(scores[2]);
		int score4 = Integer.valueOf(scores[3]);
		int score5 = Integer.valueOf(scores[4]);
		HashMap<String, Integer> hm = new HashMap<>(); // 创建HashMap对象
		// 使用put()方法向集合hm中添加键、值对
		hm.put("评委1", score1);
		hm.put("评委2", score2);
		hm.put("评委3", score3);
		hm.put("评委4", score4);
		hm.put("评委5", score5);
		int total = 0; // 初始化一个int类型的变量total（总分数）
		Iterator<String> it = hm.keySet().iterator(); // 创建迭代器
		while (it.hasNext()){ // 判断集合hm中是否还有key
			String key = (String) it.next(); // 接收key
			System.out.println(key + "：" + hm.get(key) + "分"); // 输出集合hm中的键、值对
			total += (int) hm.get(key); // 替换int类型的变量total
		}
		System.out.println(); // 换行
		System.out.println("运动员本轮比赛的成绩：" + (double)(total/5) + "分"); // 输出total
		sc.close(); // 关闭控制台输入
	}
}