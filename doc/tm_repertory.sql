/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : tools_manage

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-10-29 20:00:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tm_repertory`
-- ----------------------------
DROP TABLE IF EXISTS `tm_repertory`;
CREATE TABLE `tm_repertory` (
  `rep_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rep_name` varchar(50) DEFAULT NULL COMMENT '库房名称',
  `rep_manager` varchar(60) DEFAULT NULL COMMENT '库房负责人',
  `phone` varchar(30) DEFAULT NULL COMMENT '库房联系电话',
  `dept_id` int(11) unsigned DEFAULT NULL COMMENT '库房所属部门',
  `rep_location` varchar(255) DEFAULT NULL COMMENT '库房地址',
  `state` tinyint(4) unsigned NOT NULL COMMENT '正常 2、禁用 1、删除 0',
  PRIMARY KEY (`rep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_repertory
-- ----------------------------
