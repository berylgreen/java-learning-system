CREATE
DATABASE demo;
use
demo;
CREATE TABLE t_student
(
    id       INT(10) PRIMARY KEY,
    name     VARCHAR(20),
    birthday DATE,
    gender   VARCHAR(2)
);

INSERT INTO t_student(id, name, birthday, gender)
VALUES (1, '李华', '2004-11-04',),
       (2, '张明', '123456', 'zm@qq.com', '2003-1-04'),
       (3, '赵亮', '123456', 'zl@qq.com', '2005-12-14');