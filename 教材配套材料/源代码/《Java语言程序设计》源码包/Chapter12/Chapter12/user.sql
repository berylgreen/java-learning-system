CREATE TABLE `t_user`
(
    `id`       INT(4) PRIMARY KEY AUTO_INCREMENT,
    `name`     VARCHAR(40),
    `password` VARCHAR(60),
    `area`     VARCHAR(40),
    `work`     VARCHAR(40),
    `home`     VARCHAR(40)
);
INSERT INTO t_user(`name`, `password`, `area`, `work`, `home`)
VALUES ('Tony', '123456', '上海', '是', '是'),
       ('Jack', '123', '北京', '是', '是'),
       ('root', 'admin', '深圳', '是', '是');