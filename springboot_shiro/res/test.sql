/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2020-08-25 17:29:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `name` varchar(50) default NULL,
  `perms` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zhangsan', '123', '张三', 'user:add');
INSERT INTO `user` VALUES ('2', 'lisi', '123', '李四', 'user:update');
