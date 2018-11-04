/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : tools_manage

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-04 13:27:43
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
  `res_ids` varchar(255) DEFAULT NULL COMMENT '管理员拥有资源ID的集合，格式为 1,2,3,4。已废弃字段',
  `state` tinyint(4) unsigned NOT NULL COMMENT '管理员状态，正常 2、禁用 1、删除 0',
  `gmt_create` datetime NOT NULL COMMENT '记录创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '记录更改时间',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `uk_username` (`username`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_admin
-- ----------------------------
INSERT INTO `tm_admin` VALUES ('7', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'fsdf', 'sdfsd', '3', '1,2,3', '2', '2018-10-20 17:52:14', '2018-10-24 12:27:47');
INSERT INTO `tm_admin` VALUES ('11', 'zhangjiale123', '6512bd43d9caa6e02c990b0a82652dca', 'fsdf', 'zhang', '3', null, '2', '2018-10-23 22:47:10', '2018-10-23 22:51:36');
INSERT INTO `tm_admin` VALUES ('12', 'zhang', 'd0cd2693b3506677e4c55e91d6365bff', '456454132123', 'Jack', '5', null, '0', '2018-10-29 15:49:46', '2018-10-29 20:21:23');

-- ----------------------------
-- Table structure for `tm_admin_repertory`
-- ----------------------------
DROP TABLE IF EXISTS `tm_admin_repertory`;
CREATE TABLE `tm_admin_repertory` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) unsigned NOT NULL,
  `rep_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_admin_repertory
-- ----------------------------

-- ----------------------------
-- Table structure for `tm_admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `tm_admin_role`;
CREATE TABLE `tm_admin_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) unsigned DEFAULT NULL,
  `role_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_adminId_roleId` (`admin_id`,`role_id`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_admin_role
-- ----------------------------
INSERT INTO `tm_admin_role` VALUES ('14', '6', '1');
INSERT INTO `tm_admin_role` VALUES ('15', '6', '2');
INSERT INTO `tm_admin_role` VALUES ('16', '6', '3');
INSERT INTO `tm_admin_role` VALUES ('1', '7', '1');
INSERT INTO `tm_admin_role` VALUES ('2', '7', '2');
INSERT INTO `tm_admin_role` VALUES ('3', '7', '3');
INSERT INTO `tm_admin_role` VALUES ('17', '9', '1');
INSERT INTO `tm_admin_role` VALUES ('18', '10', '1');
INSERT INTO `tm_admin_role` VALUES ('19', '11', '1');
INSERT INTO `tm_admin_role` VALUES ('23', '11', '8');
INSERT INTO `tm_admin_role` VALUES ('20', '12', '1');
INSERT INTO `tm_admin_role` VALUES ('21', '12', '2');
INSERT INTO `tm_admin_role` VALUES ('22', '12', '3');
INSERT INTO `tm_admin_role` VALUES ('24', '12', '8');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_dept
-- ----------------------------
INSERT INTO `tm_dept` VALUES ('3', '工程部', '0', '张家乐', '54151516515');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_resource
-- ----------------------------
INSERT INTO `tm_resource` VALUES ('1', '库房管理', 'respotery:list', '/api/v1/repertory', '0', '2018-10-12 21:15:51', '2018-10-12 21:15:51');
INSERT INTO `tm_resource` VALUES ('2', '部门管理', 'dept:list', '/api/v1/dept', '0', '2018-10-21 09:49:53', '2018-10-21 09:49:56');
INSERT INTO `tm_resource` VALUES ('5', '库房管理1', 'respotery:list', '/api/v2/repertory', '0', '2018-10-22 12:55:56', '2018-10-22 12:55:56');
INSERT INTO `tm_resource` VALUES ('6', '是多少', 'fsss', '/asdas/asda', '1', '2018-10-28 11:27:18', '2018-10-28 11:27:21');
INSERT INTO `tm_resource` VALUES ('7', '按时发', 'sada', '/asd/asd', '6', '2018-10-28 11:27:41', '2018-10-28 11:27:45');
INSERT INTO `tm_resource` VALUES ('8', '发送方', 'sdf', '/fdgf/dfgdf', '2', '2018-10-28 11:28:04', '2018-10-28 11:28:08');

-- ----------------------------
-- Table structure for `tm_role`
-- ----------------------------
DROP TABLE IF EXISTS `tm_role`;
CREATE TABLE `tm_role` (
  `role_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `role_code` varchar(50) NOT NULL COMMENT '角色代码，例如：admin',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `uk_role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_role
-- ----------------------------
INSERT INTO `tm_role` VALUES ('1', '管理员', 'admin', '2018-10-21 09:26:15', '2018-10-21 09:26:17');
INSERT INTO `tm_role` VALUES ('2', '用户', 'user', '2018-10-21 09:26:45', '2018-10-21 09:26:48');
INSERT INTO `tm_role` VALUES ('3', '超级管理员', 'root', '2018-10-21 13:33:49', '2018-10-21 13:33:53');
INSERT INTO `tm_role` VALUES ('5', 'VIP用户', 'super', '2018-10-26 11:55:17', '2018-10-27 22:15:55');
INSERT INTO `tm_role` VALUES ('6', '普通用户', 'commonUser', '2018-10-29 20:51:58', '2018-10-29 20:51:58');
INSERT INTO `tm_role` VALUES ('8', '一号管理员', 'firstAdmin', '2018-10-29 21:00:21', '2018-10-29 21:09:52');

-- ----------------------------
-- Table structure for `tm_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tm_role_resource`;
CREATE TABLE `tm_role_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) unsigned DEFAULT NULL,
  `res_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_role_resource
-- ----------------------------
INSERT INTO `tm_role_resource` VALUES ('1', '1', '1');
INSERT INTO `tm_role_resource` VALUES ('2', '1', '2');
INSERT INTO `tm_role_resource` VALUES ('3', '1', '5');
INSERT INTO `tm_role_resource` VALUES ('4', '1', '6');
INSERT INTO `tm_role_resource` VALUES ('5', '1', '7');
INSERT INTO `tm_role_resource` VALUES ('6', '1', '8');
INSERT INTO `tm_role_resource` VALUES ('7', '2', '1');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_tool
-- ----------------------------

-- ----------------------------
-- Table structure for `tm_tool_bag`
-- ----------------------------
DROP TABLE IF EXISTS `tm_tool_bag`;
CREATE TABLE `tm_tool_bag` (
  `tb_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rfid_code` varchar(64) DEFAULT NULL COMMENT '工具包上所有RFID标签的ID集合，例如：1,2,3,4',
  `rfid_reader_code` varchar(64) DEFAULT NULL COMMENT 'RFID读取器机器码',
  `type` varchar(20) NOT NULL DEFAULT '' COMMENT '工具包型号，大 BIG、中 MEDIUM、小 SMALL',
  `rep_id` int(11) unsigned NOT NULL COMMENT '所属库房ID',
  `state` tinyint(4) unsigned NOT NULL COMMENT '工具包当前状态，使用中 3,、待使用 2、维修中 1、报废 0',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`tb_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_tool_cate
-- ----------------------------
INSERT INTO `tm_tool_cate` VALUES ('1', '便携类', '0');
