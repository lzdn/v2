/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : xuexi

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-05-22 23:32:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('schedulerFactoryBean', 'STATE_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('schedulerFactoryBean', 'zhanglin-PC1526656798741', '1526656808231', '7500');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键dept_id',
  `parent_dept_id` int(11) DEFAULT NULL COMMENT '父部门parent_dept_id',
  `dept_simple_name` varchar(45) NOT NULL COMMENT '简称',
  `dept_full_name` varchar(255) DEFAULT NULL COMMENT '全称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '1', '总经理办公室', '总经理办公室', '');
INSERT INTO `t_dept` VALUES ('2', '1', 'abc', 'abc', 'abc');
INSERT INTO `t_dept` VALUES ('3', '1', '123', '123', '123');
INSERT INTO `t_dept` VALUES ('4', '1', '22', '222', '22');
INSERT INTO `t_dept` VALUES ('5', '2', '33', '333', '333');
INSERT INTO `t_dept` VALUES ('6', '1', '111111', '1111111', '1111');
INSERT INTO `t_dept` VALUES ('7', '1', '1111111', '222222222', '123213');
INSERT INTO `t_dept` VALUES ('8', '1', '7777777', '777777', '777777777');
INSERT INTO `t_dept` VALUES ('9', '1', '88888', '77777788888', '8888888888888888');
INSERT INTO `t_dept` VALUES ('10', '2', '3123', '231', '123123');
INSERT INTO `t_dept` VALUES ('11', '1', '123', '4123', '4212');

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dict_code` varchar(255) NOT NULL COMMENT '代码',
  `dict_name` varchar(255) NOT NULL COMMENT '名称',
  `dict_value` varchar(255) NOT NULL COMMENT '值',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '是否有效 1 有效 0 无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of t_dict
-- ----------------------------

-- ----------------------------
-- Table structure for t_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_job_log`;
CREATE TABLE `t_job_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `job_name` varchar(255) NOT NULL COMMENT '任务key',
  `description` varchar(255) DEFAULT NULL COMMENT '任务执行描述',
  `excute_time` datetime DEFAULT NULL COMMENT '执行时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='任务日志表';

-- ----------------------------
-- Records of t_job_log
-- ----------------------------
INSERT INTO `t_job_log` VALUES ('1', 'TestJob', '执行任务：TestJob 失败', '2018-05-20 01:19:11');
INSERT INTO `t_job_log` VALUES ('2', 'TestJob', '执行任务：TestJob 失败', '2018-05-20 01:19:31');
INSERT INTO `t_job_log` VALUES ('3', 'TestJob', '执行任务：TestJob 失败', '2018-05-20 01:21:52');
INSERT INTO `t_job_log` VALUES ('4', 'TestJob', '执行任务：TestJob 失败', '2018-05-20 01:22:15');
INSERT INTO `t_job_log` VALUES ('5', 'TestJob', '执行任务：TestJob 失败', '2018-05-20 21:20:04');
INSERT INTO `t_job_log` VALUES ('6', 'MyJob', '执行任务：MyJob 成功', '2018-05-20 22:28:39');
INSERT INTO `t_job_log` VALUES ('7', 'MyJob', '执行任务：MyJob 成功', '2018-05-20 22:35:39');
INSERT INTO `t_job_log` VALUES ('8', 'MyJob', '执行任务：MyJob 成功', '2018-05-20 22:44:31');
INSERT INTO `t_job_log` VALUES ('9', 'MyJob', '执行任务：MyJob 成功', '2018-05-20 23:14:13');
INSERT INTO `t_job_log` VALUES ('10', 'MyJob', '执行任务：MyJob 成功', '2018-05-21 00:34:47');
INSERT INTO `t_job_log` VALUES ('11', 'MyJob', '执行任务：MyJob 成功', '2018-05-21 20:46:28');
INSERT INTO `t_job_log` VALUES ('12', 'MyJob', '执行任务：MyJob 成功', '2018-05-21 21:00:00');
INSERT INTO `t_job_log` VALUES ('13', 'MyJob', '执行任务：MyJob 成功', '2018-05-21 22:00:00');
INSERT INTO `t_job_log` VALUES ('14', 'MyJob', '执行任务：MyJob 成功', '2018-05-21 23:23:04');
INSERT INTO `t_job_log` VALUES ('15', 'MyJob', '执行任务：MyJob 成功', '2018-05-22 21:21:38');
INSERT INTO `t_job_log` VALUES ('16', 'MyJob', '执行任务：MyJob 成功', '2018-05-22 22:21:48');
INSERT INTO `t_job_log` VALUES ('17', 'MyJob', '执行任务：MyJob 成功', '2018-05-22 22:26:28');
INSERT INTO `t_job_log` VALUES ('18', 'MyJob', '执行任务：MyJob 成功', '2018-05-22 22:26:34');
INSERT INTO `t_job_log` VALUES ('19', 'MyJob', '执行任务：MyJob 成功', '2018-05-22 22:27:17');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `module_id` int(11) NOT NULL COMMENT '菜单组id',
  `super_id` int(11) NOT NULL DEFAULT '0',
  `menu_name` varchar(255) NOT NULL COMMENT '菜单名称',
  `menu_code` varchar(255) DEFAULT NULL COMMENT '菜单代码',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '级别',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '是否有效 1 有效 0 无效',
  `order_by` int(11) NOT NULL DEFAULT '999' COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '2', '0', '部门管理', '', '/dept/main', '0', '', '1', '1');
INSERT INTO `t_menu` VALUES ('2', '2', '0', '权限管理', '', '/right/main', '0', '', '1', '2');
INSERT INTO `t_menu` VALUES ('3', '2', '0', '模块管理', '', '/module/main', '0', '', '1', '4');
INSERT INTO `t_menu` VALUES ('4', '2', '0', '菜单管理', '', '/menu/main', '0', '', '1', '5');
INSERT INTO `t_menu` VALUES ('5', '3', '0', '产品列表', '', '/product/main', '0', '', '1', '1');
INSERT INTO `t_menu` VALUES ('6', '2', '0', '角色管理', '', '/role/main', '0', '', '1', '3');
INSERT INTO `t_menu` VALUES ('7', '2', '0', '用户管理', '', '/user/main', '0', '', '1', '6');
INSERT INTO `t_menu` VALUES ('8', '3', '0', '产品类别', '', '/kind/main', '0', '', '1', '2');
INSERT INTO `t_menu` VALUES ('9', '3', '0', '产品品牌', '', '/brand/main', '0', '', '1', '3');
INSERT INTO `t_menu` VALUES ('10', '2', '0', '系统字典', '', '/dict/main', '0', '', '1', '7');
INSERT INTO `t_menu` VALUES ('11', '2', '0', '任务管理', null, '/job/main', '0', null, '1', '999');

-- ----------------------------
-- Table structure for t_module
-- ----------------------------
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module` (
  `module_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键group_id',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `module_name` varchar(255) NOT NULL COMMENT '组名称',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `order_by` int(11) NOT NULL DEFAULT '999' COMMENT '排序',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '是否有效 1 有效 0 无效',
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='菜单组表';

-- ----------------------------
-- Records of t_module
-- ----------------------------
INSERT INTO `t_module` VALUES ('1', 'fa fa-user', '个人中心', '个人中心', '1', '1');
INSERT INTO `t_module` VALUES ('2', 'fa fa-power-off', '系统设置', '系统设置', '2', '1');
INSERT INTO `t_module` VALUES ('3', 'fa fa-bookmark', '产品维护', '产品维护', '3', '1');
INSERT INTO `t_module` VALUES ('4', '564', '465', '12', '12', '1');

-- ----------------------------
-- Table structure for t_right
-- ----------------------------
DROP TABLE IF EXISTS `t_right`;
CREATE TABLE `t_right` (
  `right_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键right_id',
  `right_url` varchar(255) NOT NULL COMMENT '权限url',
  `upper_right_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级权限',
  `right_name` varchar(255) NOT NULL COMMENT '权限名称',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单Id',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '是否有效 1 有效 0 无效',
  PRIMARY KEY (`right_id`),
  UNIQUE KEY `t_right_right_url` (`right_url`),
  KEY `t_right_menu_index` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_right
-- ----------------------------
INSERT INTO `t_right` VALUES ('1', '/dept/main', '0', '部门管理', '1', '', '1');
INSERT INTO `t_right` VALUES ('2', '/right/main', '0', '权限管理', '2', '', '1');
INSERT INTO `t_right` VALUES ('3', '/group/main', '0', '菜单组管理', '3', '', '1');
INSERT INTO `t_right` VALUES ('4', '/menu/main', '0', '菜单管理', '4', '', '1');
INSERT INTO `t_right` VALUES ('5', '/product/main', '0', '产品列表', '5', '', '1');
INSERT INTO `t_right` VALUES ('6', '/role/main', '0', '角色管理', '6', '', '1');
INSERT INTO `t_right` VALUES ('7', '/user/main', '0', '用户管理', '7', null, '1');
INSERT INTO `t_right` VALUES ('8', '/job/main', '0', '任务管理', '11', null, '1');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键role_id',
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '是否有效 1 有效 0 无效',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', '', '1');
INSERT INTO `t_role` VALUES ('2', '管理员', '', '1');
INSERT INTO `t_role` VALUES ('3', '普通会员', '', '1');
INSERT INTO `t_role` VALUES ('4', '游客', '', '1');
INSERT INTO `t_role` VALUES ('5', '测试', '测试', '1');

-- ----------------------------
-- Table structure for t_role_right_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_role_right_relation`;
CREATE TABLE `t_role_right_relation` (
  `role_id` int(11) NOT NULL COMMENT '角色',
  `right_id` int(11) NOT NULL COMMENT '权限',
  PRIMARY KEY (`role_id`,`right_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of t_role_right_relation
-- ----------------------------
INSERT INTO `t_role_right_relation` VALUES ('1', '1');
INSERT INTO `t_role_right_relation` VALUES ('1', '2');
INSERT INTO `t_role_right_relation` VALUES ('1', '3');
INSERT INTO `t_role_right_relation` VALUES ('1', '4');
INSERT INTO `t_role_right_relation` VALUES ('1', '5');
INSERT INTO `t_role_right_relation` VALUES ('1', '6');
INSERT INTO `t_role_right_relation` VALUES ('1', '7');
INSERT INTO `t_role_right_relation` VALUES ('1', '8');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键user_id',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `account` varchar(45) NOT NULL COMMENT '账号',
  `password` varchar(45) NOT NULL COMMENT '密码',
  `salt` varchar(45) NOT NULL COMMENT 'md5密码盐',
  `username` varchar(45) NOT NULL COMMENT '名字',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态(1：启用  2：冻结  3：删除）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1000', '1', null, 'admin', '42f6bfd8e35a280d7a212cb76ae76280', '&|^286._', '刘德华', null, null, '415656544@qq.com', null, '1', '2017-10-18 17:42:40', null, null);
INSERT INTO `t_user` VALUES ('1001', null, null, 'abd', 'abc', 'abc', '测试', '2018-05-18 00:00:00', '1', null, null, '1', '2018-05-17 21:52:11', null, null);

-- ----------------------------
-- Table structure for t_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role_relation`;
CREATE TABLE `t_user_role_relation` (
  `user_id` int(11) NOT NULL COMMENT '用户',
  `role_id` int(11) NOT NULL COMMENT '角色',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of t_user_role_relation
-- ----------------------------
INSERT INTO `t_user_role_relation` VALUES ('1000', '1');

-- ----------------------------
-- Function structure for queryChildrenRight
-- ----------------------------
DROP FUNCTION IF EXISTS `queryChildrenRight`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `queryChildrenRight`(rootId varchar(100)) RETURNS varchar(2000) CHARSET utf8
BEGIN   
DECLARE str varchar(2000);  
DECLARE cid varchar(100);   
SET str = '$';   
SET cid = rootId;   
WHILE cid is not null DO   
    SET str = concat(str, ',', cid);   
    SELECT group_concat(right_id) INTO cid FROM t_right where FIND_IN_SET(upper_right_id, cid) > 0;   
END WHILE;   
RETURN str;   
END
;;
DELIMITER ;
