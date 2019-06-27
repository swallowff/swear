/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 100137
Source Host           : localhost:3306
Source Database       : swallow_master

Target Server Type    : MYSQL
Target Server Version : 100137
File Encoding         : 65001

Date: 2019-06-27 19:15:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `pid` varchar(11) DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '父级ids',
  `simple_name` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '简称',
  `full_name` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '提示',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `version` int(11) DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  `delflag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `code` varchar(64) DEFAULT NULL COMMENT '字典code',
  `label` varchar(64) DEFAULT NULL COMMENT '字典名称',
  `val` int(5) DEFAULT NULL COMMENT '字典值',
  `tips` varchar(64) DEFAULT NULL COMMENT '提示',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `version` int(11) DEFAULT NULL,
  `delflag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', 'sex', '男', '0', '性别', '10', '2019-06-26 23:20:35', '2019-06-26 23:20:35', null, '0');
INSERT INTO `sys_dict` VALUES ('2', 'sex', '女', '1', '性别', '11', '2019-06-26 23:21:07', '2019-06-26 23:21:10', null, '0');

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` varchar(64) NOT NULL,
  `log_name` varchar(64) DEFAULT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `succeed` tinyint(1) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `delflag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `code` varchar(64) DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(64) DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` text COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(64) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(64) DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `sort` int(11) DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(5) DEFAULT NULL COMMENT '菜单层级',
  `is_menu` tinyint(1) DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `tips` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `is_open` tinyint(1) DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `delflag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('105', 'system', '0', '[0],', '系统管理', 'fa-user', '#', '4', '1', '1', null, '1', '1', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('106', 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', '1', '2', '1', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('107', 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', null, '/mgr/add', '1', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('1075302148775833601', 'uPassport', 'manage_blog', '[0],[manage_blog],', '用户通行证', '', '/uPassport', '99', '2', '1', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('1075302148775833602', 'uPassport_list', 'uPassport', '[0],[manage_laowu],[uPassport],', '用户通行证列表', '', '/uPassport/list', '99', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('1075302148775833603', 'uPassport_add', 'uPassport', '[0],[manage_laowu],[uPassport],', '用户通行证添加', '', '/uPassport/add', '99', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('1075302148775833604', 'uPassport_update', 'uPassport', '[0],[manage_laowu],[uPassport],', '用户通行证更新', '', '/uPassport/update', '99', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('1075302148775833605', 'uPassport_delete', 'uPassport', '[0],[manage_laowu],[uPassport],', '用户通行证删除', '', '/uPassport/delete', '99', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('1075302148775833606', 'uPassport_detail', 'uPassport', '[0],[manage_laowu],[uPassport],', '用户通行证详情', '', '/uPassport/detail', '99', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('108', 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', null, '/mgr/edit', '2', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('109', 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', null, '/mgr/delete', '3', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('110', 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', null, '/mgr/reset', '4', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('111', 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', null, '/mgr/freeze', '5', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('112', 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', null, '/mgr/unfreeze', '6', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('113', 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', null, '/mgr/setRole', '7', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('114', 'role', 'system', '[0],[system],', '角色管理', null, '/role', '2', '2', '1', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('115', 'role_add', 'role', '[0],[system],[role],', '添加角色', null, '/role/add', '1', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('116', 'role_edit', 'role', '[0],[system],[role],', '修改角色', null, '/role/edit', '2', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('117', 'role_remove', 'role', '[0],[system],[role],', '删除角色', null, '/role/remove', '3', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('118', 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', null, '/role/setAuthority', '4', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('119', 'menu', 'system', '[0],[system],', '菜单管理', null, '/menu', '4', '2', '1', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('120', 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', null, '/menu/add', '1', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('121', 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', null, '/menu/edit', '2', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('122', 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', null, '/menu/remove', '3', '3', '0', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('128', 'log', 'system', '[0],[system],', '业务日志', null, '/log', '6', '2', '1', null, '1', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('130', 'druid', 'system', '[0],[system],', '监控管理', null, '/druid', '7', '2', '1', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('131', 'dept', 'system', '[0],[system],', '部门管理', null, '/dept', '3', '2', '1', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('132', 'dict', 'system', '[0],[system],', '字典管理', null, '/dict', '4', '2', '1', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('133', 'loginLog', 'system', '[0],[system],', '登录日志', null, '/loginLog', '6', '2', '1', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('134', 'log_clean', 'log', '[0],[system],[log],', '清空日志', null, '/log/delLog', '3', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('135', 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', null, '/dept/add', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('136', 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', null, '/dept/update', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('137', 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', null, '/dept/delete', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('138', 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', null, '/dict/add', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('139', 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', null, '/dict/update', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('140', 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', null, '/dict/delete', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('141', 'notice', 'system', '[0],[system],', '通知管理', null, '/notice', '9', '2', '1', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('142', 'notice_add', 'notice', '[0],[system],[notice],', '添加通知', null, '/notice/add', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('143', 'notice_update', 'notice', '[0],[system],[notice],', '修改通知', null, '/notice/update', '2', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('144', 'notice_delete', 'notice', '[0],[system],[notice],', '删除通知', null, '/notice/delete', '3', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('145', 'hello', '0', '[0],', '通知', 'fa-rocket', '/notice/hello', '1', '1', '1', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('148', 'code', '0', '[0],', '代码生成', 'fa-code', '/code', '3', '1', '1', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('149', 'api_mgr', '0', '[0],', '接口文档', 'fa-leaf', '/swagger-ui.html', '2', '1', '1', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('150', 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', '4', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('151', 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', '5', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('152', 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', '4', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('153', 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', '5', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('154', 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', '6', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('155', 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', '4', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('156', 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', '5', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('157', 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', '6', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('158', 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', '2', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('159', 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', '3', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('160', 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', '1', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('161', 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', '2', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('162', 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', '5', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('163', 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', '6', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('164', 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', '7', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('165', 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', '8', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('166', 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', '9', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('167', 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', '10', '3', '0', null, '1', null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('168', 'manage_blog', '0', '[0],', '博客系统', 'fa-sellsy', '/manage', '1', '1', '1', null, '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `tittle` varchar(64) DEFAULT NULL COMMENT '标题',
  `type` int(3) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '内容',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `to_user` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `delflag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('6', '世界', '10', '欢迎使用Guns管理系统', '1', null, '2017-01-11 08:53:20', null, null, null);
INSERT INTO `sys_notice` VALUES ('8', '你好', null, '你好', '1', null, '2017-05-10 19:28:57', null, null, null);

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `id` varchar(64) NOT NULL,
  `log_name` varchar(64) DEFAULT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `succeed` tinyint(1) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `log_type` int(3) DEFAULT NULL,
  `class_name` varchar(64) DEFAULT NULL,
  `method` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `delflag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `pid` varchar(64) DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '角色名称',
  `dept_id` varchar(64) DEFAULT NULL COMMENT '部门id',
  `tips` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '提示',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) DEFAULT NULL COMMENT '保留字段(暂时没用）',
  `delflag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(45) NOT NULL COMMENT '主键id',
  `avatar` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '头像',
  `account` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '名字',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '电话',
  `role_id` varchar(45) DEFAULT NULL COMMENT '角色id',
  `dept_id` varchar(45) DEFAULT NULL COMMENT '部门id',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `delflag` tinyint(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1143873210717908992', null, 'admin1', '123', null, '123', null, '0', '123@qq.com', null, null, null, null, '2019-06-26 21:26:40', '2019-06-26 21:26:40', '0', null);
INSERT INTO `sys_user` VALUES ('1143874274766696448', null, 'admin2', '123', null, 'admin2', null, '0', '123@153.com', null, null, null, '0', '2019-06-26 21:32:26', '2019-06-26 21:30:53', '0', null);
INSERT INTO `sys_user` VALUES ('1143879991238336512', null, 'admina', '123', null, 'yu swallow', null, '0', '123@qq.com', '13647617481', null, null, null, '2019-06-26 21:53:36', '2019-06-26 21:53:36', '0', null);
INSERT INTO `sys_user` VALUES ('1143880318071087104', null, 'admin_test', '123', null, 'yu swallow', null, '1', '123@qq.com', '13647617481', null, null, '1', '2019-06-27 00:32:21', '2019-06-26 21:54:54', '0', null);
INSERT INTO `sys_user` VALUES ('54', null, 'admin', 'fa3b4fff6c2c5fe5d6b034e0e1254bfa', 'h3zpe', 'shenyu', '2019-03-15', '1', null, '17320393360', null, null, '1', '2019-06-26 19:59:06', '2019-03-15 19:59:00', null, null);
INSERT INTO `sys_user` VALUES ('55', null, 'shenyu', '1aa730e23bf07dc1660dcb1ee46b3f38', 'i3r7r', 'shenyu', '2019-03-15', '1', null, '17320393360', null, null, '0', '2019-06-26 19:58:58', '2019-03-15 20:01:23', null, null);
