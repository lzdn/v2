 

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB   DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '1', '总经理办公室', '总经理办公室', '');
 

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
) ENGINE=InnoDB   DEFAULT CHARSET=utf8 COMMENT='字典表';

 

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `module_id` int(11) NOT NULL COMMENT '菜单组id',
  `menu_name` varchar(255) NOT NULL COMMENT '菜单名称',
  `menu_code` varchar(255) DEFAULT NULL COMMENT '菜单代码',
  `url` varchar(255) NOT NULL COMMENT 'url地址',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '级别',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '是否有效 1 有效 0 无效',
  `order_by` int(11) NOT NULL DEFAULT '999' COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB   DEFAULT CHARSET=utf8 COMMENT='菜单表';

 

-- ----------------------------
-- Table structure for t_menu_group
-- ----------------------------
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module` (
  `module_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键group_id',
  `icon` varchar(255) NOT NULL COMMENT '图标',
  `module_name` varchar(255) NOT NULL COMMENT '组名称',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `order_by` int(11) NOT NULL DEFAULT '999' COMMENT '排序',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '是否有效 1 有效 0 无效',
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB   DEFAULT CHARSET=utf8 COMMENT='菜单组表';

-- ----------------------------
-- Records of t_menu_group
-- ----------------------------
INSERT INTO `t_module` VALUES ('1', 'fa fa-user', '个人中心', '个人中心', '1', '1');
INSERT INTO `t_module` VALUES ('2', 'fa fa-power-off', '系统设置', '系统设置', '2', '1');
INSERT INTO `t_module` VALUES ('3', 'fa fa-bookmark', '产品维护', '产品维护', '3', '1');

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
) ENGINE=InnoDB   DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_right
-- ----------------------------
 

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', '', '1');
INSERT INTO `t_role` VALUES ('2', '管理员', '', '1');
INSERT INTO `t_role` VALUES ('3', '普通会员', '', '1');
INSERT INTO `t_role` VALUES ('4', '游客', '', '1');

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
) ENGINE=InnoDB   DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1000', '1', null, 'admin', '9c9d6357c2e36766', '&|^286._', '刘德华', null, null, '415656544@qq.com', null, '1', '2017-10-18 17:42:40', null, null);

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
