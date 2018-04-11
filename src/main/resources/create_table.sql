SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID 唯一主键',
  `user_name` varchar(32) NOT NULL COMMENT '用户名，唯一',
  `phone` varchar(32) NOT NULL COMMENT '用户手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `password` varchar(32) NOT NULL COMMENT '用户密码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `name_index` (`user_name`),
  KEY `phone_pwd_index` (`phone`,`password`) USING BTREE,
  KEY `email_pwd_index` (`email`,`password`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `id` bigint(20) NOT NULL COMMENT 'id 主键',
  `user_id` bigint(20) NOT NULL COMMENT '关联user表的user id ',
  `user_name` varchar(32) NOT NULL COMMENT '打开用户名 关联user表',
  `sign_location` varchar(255) DEFAULT NULL COMMENT '打卡地点',
  `sign_date` date NOT NULL COMMENT '打卡日期',
  `sign_time` time NOT NULL COMMENT '打卡时间',
  PRIMARY KEY (`id`),
  KEY `date_index` (`sign_date`) USING BTREE,
  KEY `time_index` (`sign_time`) USING BTREE,
  KEY `name_date_index` (`user_name`,`sign_date`) USING BTREE,
  KEY `name_time_index` (`user_name`,`sign_time`) USING BTREE,
  KEY `name_location_index` (`user_name`,`sign_location`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;