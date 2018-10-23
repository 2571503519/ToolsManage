/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : tools_manage

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-10-22 18:57:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tm_admin`
-- ----------------------------
DROP TABLE IF EXISTS `tm_admin`;
CREATE TABLE `tm_admin` (
  `admin_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(120) NOT NULL,
  `password` varchar(255) NOT NULL,
  `id_card` varchar(30) DEFAULT NULL COMMENT '管理员身份证号码',
  `admin_name` varchar(30) DEFAULT NULL COMMENT '管理员姓名',
  `dept_id` int(11) unsigned NOT NULL COMMENT '所属部门ID',
  `res_ids` varchar(255) DEFAULT NULL COMMENT '管理员拥有资源ID的集合，格式为 1,2,3,4',
  `state` tinyint(4) unsigned NOT NULL COMMENT '管理员状态，正常 2、禁用 1、删除 0',
  `gmt_create` datetime NOT NULL COMMENT '记录创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '记录更改时间',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `uk_username` (`username`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_admin
-- ----------------------------
INSERT INTO `tm_admin` VALUES ('2', '2570150.7496842077323721', '2570150.7225363589353039', '152654646445646546546', 'jackaroo', '3', '1,2,3,4', '2', '2018-10-13 20:24:52', '2018-10-13 20:24:52');
INSERT INTO `tm_admin` VALUES ('4', '2570150.7814951591305326', '2570150.9300681180400625', '152654646445646546546', 'jackaroo', '3', '1,2,3,4', '2', '2018-10-13 20:25:58', '2018-10-13 20:25:58');
INSERT INTO `tm_admin` VALUES ('5', '2570150.01980616904320598', '2570150.5047681413731622', '152654646445646546546', 'jackaroo', '3', '1,2,3,4', '2', '2018-10-17 20:23:48', '2018-10-17 20:23:48');
INSERT INTO `tm_admin` VALUES ('6', '2570150.6050227960715377', '2570150.404798235553643', '152654646445646546546', 'jackaroo', '3', '1,2,3,4', '2', '2018-10-19 18:53:53', '2018-10-19 18:53:53');
INSERT INTO `tm_admin` VALUES ('7', 'admin', '21232f297a57a5a743894a0e4a801fc3', '46546546546465', 'jack', '2', '1,2,3', '2', '2018-10-20 17:52:14', '2018-10-20 17:52:19');
INSERT INTO `tm_admin` VALUES ('8', 'zhangjiale', '123', '1234', 'Jack', '1', '1', '2', '2018-10-21 14:27:24', '2018-10-21 14:27:24');
INSERT INTO `tm_admin` VALUES ('9', 'zhangjiale1', '11', 'fsdf', '', '1', '', '2', '2018-10-21 14:29:50', '2018-10-21 14:29:50');
INSERT INTO `tm_admin` VALUES ('10', 'zhangjiale12', '11', 'fsdf', 'sdfsd', '1', null, '2', '2018-10-21 14:33:20', '2018-10-21 14:33:20');

-- ----------------------------
-- Table structure for `tm_admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `tm_admin_role`;
CREATE TABLE `tm_admin_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) unsigned DEFAULT NULL,
  `role_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_admin_role
-- ----------------------------
INSERT INTO `tm_admin_role` VALUES ('1', '7', '1');
INSERT INTO `tm_admin_role` VALUES ('2', '7', '2');
INSERT INTO `tm_admin_role` VALUES ('3', '7', '3');

-- ----------------------------
-- Table structure for `tm_dept`
-- ----------------------------
DROP TABLE IF EXISTS `tm_dept`;
CREATE TABLE `tm_dept` (
  `dept_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(60) DEFAULT NULL COMMENT '部门名称',
  `dept_pid` int(11) unsigned DEFAULT NULL COMMENT '上级部门ID',
  `dept_manager` varchar(30) DEFAULT NULL COMMENT '部门负责人',
  `dept_manager_phone` varchar(30) DEFAULT NULL COMMENT '部门负责人联系方式',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `tm_repertory`
-- ----------------------------
DROP TABLE IF EXISTS `tm_repertory`;
CREATE TABLE `tm_repertory` (
  `rep_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
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

-- ----------------------------
-- Table structure for `tm_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tm_resource`;
CREATE TABLE `tm_resource` (
  `res_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `res_name` varchar(120) NOT NULL COMMENT '资源名称',
  `res_permission` varchar(50) DEFAULT NULL COMMENT '资源权限，例如添加用户为user:add',
  `res_url` varchar(255) DEFAULT NULL COMMENT '资源地址，同一以根路径 / 开头，例如： /account/',
  `res_pid` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '上级资源的ID，如果为1则为顶级资源',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`res_id`),
  UNIQUE KEY `uk_res_url` (`res_url`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_resource
-- ----------------------------
INSERT INTO `tm_resource` VALUES ('1', '库房管理', null, '/api/v1/repertory', '0', '2018-10-12 21:15:51', '2018-10-12 21:15:51');
INSERT INTO `tm_resource` VALUES ('2', '部门管理', null, '/api/v1/dept', '0', '2018-10-21 09:49:53', '2018-10-21 09:49:56');
INSERT INTO `tm_resource` VALUES ('5', '库房管理', null, '/api/v2/repertory', '0', '2018-10-22 12:55:56', '2018-10-22 12:55:56');

-- ----------------------------
-- Table structure for `tm_role`
-- ----------------------------
DROP TABLE IF EXISTS `tm_role`;
CREATE TABLE `tm_role` (
  `role_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_role
-- ----------------------------
INSERT INTO `tm_role` VALUES ('1', 'admin', '2018-10-21 09:26:15', '2018-10-21 09:26:17');
INSERT INTO `tm_role` VALUES ('2', 'user', '2018-10-21 09:26:45', '2018-10-21 09:26:48');
INSERT INTO `tm_role` VALUES ('3', 'root', '2018-10-21 13:33:49', '2018-10-21 13:33:53');

-- ----------------------------
-- Table structure for `tm_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tm_role_resource`;
CREATE TABLE `tm_role_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) unsigned DEFAULT NULL,
  `res_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_role_resource
-- ----------------------------
INSERT INTO `tm_role_resource` VALUES ('1', '1', '1');
INSERT INTO `tm_role_resource` VALUES ('2', '1', '2');

-- ----------------------------
-- Table structure for `tm_tool`
-- ----------------------------
DROP TABLE IF EXISTS `tm_tool`;
CREATE TABLE `tm_tool` (
  `tool_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tool_name` varchar(60) DEFAULT NULL COMMENT '工具名称',
  `cate_id` int(11) unsigned DEFAULT NULL COMMENT '工具类别',
  `rep_id` int(11) unsigned DEFAULT NULL COMMENT '所属库房ID',
  `img_url` varchar(255) DEFAULT NULL COMMENT '工具图片地址',
  `type` varchar(10) DEFAULT NULL COMMENT '工具型号，大 BIG、中 MEDIUM、小 SMALL',
  `state` tinyint(4) unsigned NOT NULL COMMENT '工具当前状态，使用中 3,、待使用 2、维修中 1、报废 0',
  `rfid_code` varchar(255) DEFAULT NULL COMMENT '工具上所有RFID标签的ID集合，例如：1,2,3,4',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`tool_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `tm_tool_bag`
-- ----------------------------
DROP TABLE IF EXISTS `tm_tool_bag`;
CREATE TABLE `tm_tool_bag` (
  `tb_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rfid_code` varchar(64) DEFAULT NULL COMMENT '工具包上的RFID标签码',
  `rfid_reader_code` varchar(64) DEFAULT NULL COMMENT 'RFID读取器机器码',
  `type` varchar(20) NOT NULL DEFAULT '' COMMENT '工具包型号，大 BIG、中 MEDIUM、小 SMALL',
  `rep_id` int(11) unsigned NOT NULL COMMENT '所属库房ID',
  `state` tinyint(4) unsigned NOT NULL COMMENT '工具包当前状态，使用中 3,、待使用 2、维修中 1、报废 0',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`tb_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_tool_bag
-- ----------------------------

-- ----------------------------
-- Table structure for `tm_tool_cate`
-- ----------------------------
DROP TABLE IF EXISTS `tm_tool_cate`;
CREATE TABLE `tm_tool_cate` (
  `cate_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cate_name` varchar(60) DEFAULT NULL,
  `cate_pid` int(11) unsigned DEFAULT NULL COMMENT '当前分类的父分类ID',
  PRIMARY KEY (`cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_tool_cate
-- ----------------------------
INSERT INTO `tm_tool_cate` VALUES ('1', '便携类', '0');
