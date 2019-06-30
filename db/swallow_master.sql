/*
 Navicat Premium Data Transfer

 Source Server         : 本地-shenyu
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : swallow_master

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 01/07/2019 00:31:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级ids',
  `simple_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `full_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `version` int(11) NULL DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  `delflag` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典code',
  `label` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名',
  `val` int(5) NULL DEFAULT NULL COMMENT '字典值',
  `tips` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `sort` int(5) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `version` int(11) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '性别', 'sex', '男', 0, '性别', 10, '2019-06-30 20:01:43', '2019-06-30 20:01:44', NULL, 0);
INSERT INTO `sys_dict` VALUES ('1145291561702305792', '支付状态', 'pay_status', '1', 1, '支付状态描述', 21, '2019-06-30 19:58:19', '2019-06-30 19:58:19', NULL, 1);
INSERT INTO `sys_dict` VALUES ('1145293820079853568', '支付状态', 'pay_status_test', '待支付', 1, '1', 1, '2019-06-30 19:37:37', '2019-06-30 19:37:37', NULL, 1);
INSERT INTO `sys_dict` VALUES ('1145334594773086208', '是否', 'yes_no', '是', 1, '是否', 10, '2019-06-30 22:13:41', '2019-06-30 22:13:41', NULL, 0);
INSERT INTO `sys_dict` VALUES ('1145339653651910656', '是否', 'yes_no', '否', 0, '', 10, '2019-06-30 22:33:47', '2019-06-30 22:33:47', NULL, 0);
INSERT INTO `sys_dict` VALUES ('2', '性别', 'sex', '女', 1, '性别', 11, '2019-06-30 19:58:35', '2019-06-30 19:58:36', NULL, 0);

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `log_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `succeed` tinyint(1) NULL DEFAULT NULL,
  `message` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `sort` int(11) NULL DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(5) NULL DEFAULT NULL COMMENT '菜单层级',
  `is_menu` tinyint(1) NULL DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `is_open` tinyint(1) NULL DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0', 'root', NULL, NULL, '根目录', NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('1', 'system', 'root', 'root', '系统管理', '', NULL, 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('2', 'cms', 'root', 'root', 'CMS客户管理', NULL, NULL, 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('3', 'system-user', 'system', 'system,root', '用户管理', NULL, '/user/list.html', 1, 2, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('4', 'system-dict', 'system', 'system,root', '字典管理', NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `tittle` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `type` int(3) NULL DEFAULT NULL COMMENT '类型',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `to_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('6', '世界', 10, '欢迎使用Guns管理系统', '1', NULL, '2017-01-11 08:53:20', NULL, NULL, NULL);
INSERT INTO `sys_notice` VALUES ('8', '你好', NULL, '你好', '1', NULL, '2017-05-10 19:28:57', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `log_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `succeed` tinyint(1) NULL DEFAULT NULL,
  `message` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `log_type` int(3) NULL DEFAULT NULL,
  `class_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `dept_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门id',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '保留字段(暂时没用）',
  `delflag` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `account` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `role_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `dept_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门id',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delflag` tinyint(1) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1143873210717908992', NULL, 'admin1', '123', NULL, '123', NULL, 0, '123@qq.com', NULL, NULL, NULL, NULL, '2019-06-26 21:26:40', '2019-06-26 21:26:40', 0, NULL);
INSERT INTO `sys_user` VALUES ('1143874274766696448', NULL, 'admin2', '123', NULL, 'admin2', NULL, 0, '123@153.com', NULL, NULL, NULL, 0, '2019-06-26 21:32:26', '2019-06-26 21:30:53', 0, NULL);
INSERT INTO `sys_user` VALUES ('1143879991238336512', NULL, '修改了哦', '123', NULL, 'yu swallow', NULL, 1, '123@qq.com', '13647617481', NULL, NULL, 0, '2019-06-30 16:15:32', '2019-06-26 21:53:36', 0, NULL);
INSERT INTO `sys_user` VALUES ('1143880318071087104', NULL, 'admin_test', '123', NULL, 'yu swallow', NULL, 1, '123@qq.com', '13647617481', NULL, NULL, 1, '2019-06-27 00:32:21', '2019-06-26 21:54:54', 1, NULL);
INSERT INTO `sys_user` VALUES ('1144962353804636160', NULL, '3123123', '123123', NULL, '12312', NULL, 0, NULL, NULL, NULL, NULL, 1, '2019-06-29 21:34:32', '2019-06-29 21:34:32', 1, NULL);
INSERT INTO `sys_user` VALUES ('1144962465264070656', NULL, '123', '12312', NULL, '123', NULL, 1, NULL, NULL, NULL, NULL, 1, '2019-06-29 21:34:59', '2019-06-29 21:34:59', 1, NULL);
INSERT INTO `sys_user` VALUES ('1145244974368108544', NULL, '123test', '123', NULL, '測試用戶23', NULL, 1, NULL, NULL, NULL, NULL, 1, '2019-06-30 16:17:45', '2019-06-30 16:17:34', 1, NULL);
INSERT INTO `sys_user` VALUES ('1145246675736657920', NULL, 'joiwefsfefe', '123123', NULL, '測試用戶', NULL, 1, NULL, NULL, NULL, NULL, 1, '2019-06-30 16:24:26', '2019-06-30 16:24:20', 1, NULL);
INSERT INTO `sys_user` VALUES ('1145339051375022080', NULL, '123sfe', '123', NULL, '123', NULL, 0, NULL, NULL, NULL, NULL, 1, '2019-06-30 22:31:24', '2019-06-30 22:31:24', 1, NULL);
INSERT INTO `sys_user` VALUES ('54', NULL, 'admin', 'fa3b4fff6c2c5fe5d6b034e0e1254bfa', 'h3zpe', 'shenyu', '2019-03-15', 1, NULL, '17320393360', NULL, NULL, 1, '2019-06-26 19:59:06', '2019-03-15 19:59:00', 0, NULL);
INSERT INTO `sys_user` VALUES ('55', NULL, 'shenyu', '1aa730e23bf07dc1660dcb1ee46b3f38', 'i3r7r', 'shenyu', '2019-03-15', 1, NULL, '17320393360', NULL, NULL, 0, '2019-06-26 19:58:58', '2019-03-15 20:01:23', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
