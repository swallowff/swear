/*
 Navicat Premium Data Transfer

 Source Server         : 本地-shenyu
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : dump_swear

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 30/08/2019 22:45:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cs_friend_group
-- ----------------------------
DROP TABLE IF EXISTS `cs_friend_group`;
CREATE TABLE `cs_friend_group`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `cs_uid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '聊天系统用户id',
  `group_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cs_friend_group_user
-- ----------------------------
DROP TABLE IF EXISTS `cs_friend_group_user`;
CREATE TABLE `cs_friend_group_user`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `ug_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户分组id',
  `cs_uid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '聊天系统用户id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cs_group_team
-- ----------------------------
DROP TABLE IF EXISTS `cs_group_team`;
CREATE TABLE `cs_group_team`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `gt_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '群组名称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '群组头像',
  `notice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '群公告',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cs_group_team_user
-- ----------------------------
DROP TABLE IF EXISTS `cs_group_team_user`;
CREATE TABLE `cs_group_team_user`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `gt_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '群组id',
  `cs_uid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组内用户id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cs_user
-- ----------------------------
DROP TABLE IF EXISTS `cs_user`;
CREATE TABLE `cs_user`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `sys_uid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '系统用户id',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录用户名',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `telephone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签名',
  `is_online` tinyint(1) NULL DEFAULT 0 COMMENT '是否在线',
  `last_login_ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上次登录ip',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '用户状态: 启用 停用',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delflag` tinyint(1) NOT NULL DEFAULT 0,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cs_user_contact
-- ----------------------------
DROP TABLE IF EXISTS `cs_user_contact`;
CREATE TABLE `cs_user_contact`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `uid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户uid',
  `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人分组id',
  `cuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人uid',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cs_user_contact_group
-- ----------------------------
DROP TABLE IF EXISTS `cs_user_contact_group`;
CREATE TABLE `cs_user_contact_group`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `uid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '聊天系统用户id',
  `group_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cs_user_friend
-- ----------------------------
DROP TABLE IF EXISTS `cs_user_friend`;
CREATE TABLE `cs_user_friend`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `cs_uid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '聊天系统用户id',
  `fuid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '好友用户id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父部门id',
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
INSERT INTO `sys_dict` VALUES ('1145334594773086208', '是否', 'yes_no', '是', 1, '是否', 10, '2019-06-30 22:13:41', '2019-06-30 22:13:41', NULL, 0);
INSERT INTO `sys_dict` VALUES ('1145339653651910656', '是否', 'yes_no', '否', 0, '', 10, '2019-06-30 22:33:47', '2019-06-30 22:33:47', NULL, 0);
INSERT INTO `sys_dict` VALUES ('1155353499915206656', '媒体类型', 'media_type', '视频', 0, '', 11, '2019-07-28 13:45:14', '2019-07-28 13:45:14', NULL, 0);
INSERT INTO `sys_dict` VALUES ('1155353582131953664', '媒体类型', 'media_type', '音频', 1, '', 11, '2019-07-28 13:45:34', '2019-07-28 13:45:34', NULL, 0);
INSERT INTO `sys_dict` VALUES ('2', '性别', 'sex', '女', 1, '性别', 11, '2019-06-30 19:58:35', '2019-06-30 19:58:36', NULL, 0);

-- ----------------------------
-- Table structure for sys_image_gallery
-- ----------------------------
DROP TABLE IF EXISTS `sys_image_gallery`;
CREATE TABLE `sys_image_gallery`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `origin_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原始名称',
  `img_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `thumbnail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图地址',
  `img_format` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片格式(jpg; png; gif ; jpeg)',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '图片大小',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图片库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `log_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `succeed` tinyint(1) NULL DEFAULT NULL,
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_media_gallery
-- ----------------------------
DROP TABLE IF EXISTS `sys_media_gallery`;
CREATE TABLE `sys_media_gallery`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `media_type` tinyint(3) NULL DEFAULT NULL COMMENT '媒体类型(0:视频  1:音乐)',
  `origin_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原始名称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储名称',
  `local_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地文件路径',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源地址',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频封面图',
  `media_format` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '格式',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '大小',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图片库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `pid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级ID',
  `pids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所有父级ID',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `sort` int(11) NULL DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(5) NULL DEFAULT NULL COMMENT '菜单层级',
  `is_menu` tinyint(1) NULL DEFAULT 0 COMMENT '是否是菜单（1：是  0：不是）',
  `is_expandable` tinyint(1) NULL DEFAULT 0 COMMENT '是否可展开',
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
INSERT INTO `sys_menu` VALUES ('1145585582578065408', 'sys-menu', '1145586687131246592', '0,1145586687131246592', '菜单管理', '', '/a/sys/menu/list.html', 12, 2, 1, 0, '', NULL, NULL, '2019-07-01 14:51:01', '2019-07-01 16:25:14', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1145586265012297728', 'sys-dict', '1145586687131246592', '0,1145586687131246592', '字典管理', '', '/a/sys/dict/list.html', 11, 2, 1, 0, '', NULL, NULL, '2019-07-01 14:53:44', '2019-07-07 00:49:04', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1145586466829623296', 'sys-user', '1145586687131246592', '0,1145586687131246592', '用户管理', '', '/a/sys/user/list.html', 13, 2, 1, 0, '', NULL, NULL, '2019-07-01 14:54:32', '2019-07-01 16:25:23', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1145586687131246592', 'sys', '0', '0', '系统管理', '', '', 10, 1, 1, 1, '', NULL, NULL, '2019-07-01 14:55:25', '2019-07-01 16:22:30', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1145586936444870656', 'cms', '0', '0', 'CMS管理', '', '', 20, 1, 1, 1, '', NULL, NULL, '2019-07-01 14:56:24', '2019-07-01 16:22:14', NULL, 1);
INSERT INTO `sys_menu` VALUES ('1146047989245411328', 'sys-dept', '1145586687131246592', '0,1145586687131246592', '部门管理', '', '/a/sys/dept/list.html', 15, 2, 1, 0, '', NULL, NULL, '2019-07-02 21:28:28', '2019-07-02 21:28:28', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1146803310301523968', 'sys-role', '1145586687131246592', '0,1145586687131246592', '角色管理', '', '/a/sys/role/list.html', 13, 2, 1, 0, '', NULL, NULL, '2019-07-04 23:29:50', '2019-07-04 23:30:33', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147544305674096640', 'cms-second', '1145586936444870656', '0,1145586936444870656', 'CMS-二级', '', '', 11, 2, 1, 1, '', NULL, NULL, '2019-07-07 00:34:17', '2019-07-07 00:50:35', NULL, 1);
INSERT INTO `sys_menu` VALUES ('1147544647300157440', 'cms-third', '1147544305674096640', '0,1145586936444870656,1147544305674096640', 'CMS-三级', '', '', 10, 3, 1, 0, '', NULL, NULL, '2019-07-07 00:35:39', '2019-07-24 23:51:52', NULL, 1);
INSERT INTO `sys_menu` VALUES ('1147780070462656512', 'sys-dict-add', '1145586265012297728', '0,1145586687131246592,1145586265012297728', '字典添加', '', '', 12, NULL, 0, 0, '', NULL, NULL, '2019-07-07 16:11:08', '2019-07-07 16:11:08', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147781172163710976', 'sys-dict-delete', '1145586265012297728', '0,1145586687131246592,1145586265012297728', '字典删除', '', '', 12, NULL, 0, 0, '', NULL, NULL, '2019-07-07 16:15:31', '2019-07-07 16:15:31', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147781499772407808', 'sys-dict-list', '1145586265012297728', '0,1145586687131246592,1145586265012297728', '字典列表', '', '', 12, NULL, 0, 0, '', NULL, NULL, '2019-07-07 16:16:49', '2019-07-07 16:16:49', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147810246732013568', 'sys-dict-edit', '1145586265012297728', '0,1145586687131246592,1145586265012297728', '字典修改', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-07 18:11:03', '2019-07-07 18:11:03', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147833849208487936', 'sys-user-add', '1145586466829623296', '0,1145586687131246592,1145586466829623296', '用户添加', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-07 19:44:50', '2019-07-07 19:44:50', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147833955441819648', 'sys-user-edit', '1145586466829623296', '0,1145586687131246592,1145586466829623296', '用户编辑', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-07 19:45:15', '2019-07-07 19:45:15', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147834029647446016', 'sys-user-delete', '1145586466829623296', '0,1145586687131246592,1145586466829623296', '用户删除', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-07 19:45:33', '2019-07-07 19:45:33', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147834171725299712', 'sys-user-setup-role', '1145586466829623296', '0,1145586687131246592,1145586466829623296', '用户角色设置', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-07 19:46:07', '2019-07-07 19:46:07', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147859968306032640', 'sys-role-add', '1146803310301523968', '0,1145586687131246592,1146803310301523968', '角色添加', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-07 21:28:37', '2019-07-07 21:28:37', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147860093447286784', 'sys-role-delete', '1146803310301523968', '0,1145586687131246592,1146803310301523968', '角色删除', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-07 21:29:07', '2019-07-07 21:29:07', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147860171155156992', 'sys-role-edit', '1146803310301523968', '0,1145586687131246592,1146803310301523968', '角色修改', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-07 21:29:26', '2019-07-07 21:29:26', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1148506973277294592', 'sys-menu-list', '1145585582578065408', '0,1145586687131246592,1145585582578065408', '菜单列表', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-09 16:19:35', '2019-07-09 16:19:35', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1148507079674204160', 'sys-menu-add', '1145585582578065408', '0,1145586687131246592,1145585582578065408', '菜单添加', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-09 16:20:01', '2019-07-09 16:20:01', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1148507168681529344', 'sys-menu-edit', '1145585582578065408', '0,1145586687131246592,1145585582578065408', '菜单修改', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-09 16:20:22', '2019-07-09 16:20:22', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1148507264819171328', 'sys-menu-delete', '1145585582578065408', '0,1145586687131246592,1145585582578065408', '菜单删除', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-09 16:20:45', '2019-07-09 16:20:45', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1148507956606701568', 'sys-role-list', '1146803310301523968', '0,1145586687131246592,1146803310301523968', '角色列表', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-09 16:23:30', '2019-07-09 16:23:30', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1148508268184768512', 'sys-user-list', '1145586466829623296', '0,1145586687131246592,1145586466829623296', '用户列表', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-09 16:24:44', '2019-07-09 16:24:44', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1148508552550191104', 'sys-dept-list', '1146047989245411328', '0,1145586687131246592,1146047989245411328', '部门列表', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-09 16:25:52', '2019-07-09 16:25:52', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1148508644376088576', 'sys-dept-add', '1146047989245411328', '0,1145586687131246592,1146047989245411328', '部门添加', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-09 16:26:14', '2019-07-09 16:26:14', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1148508729482711040', 'sys-dept-edit', '1146047989245411328', '0,1145586687131246592,1146047989245411328', '部门修改', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-09 16:26:34', '2019-07-09 16:26:34', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1148508822680145920', 'sys-dept-delete', '1146047989245411328', '0,1145586687131246592,1146047989245411328', '部门删除', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-09 16:26:56', '2019-07-09 16:26:56', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1148509487464747008', 'sys-role-setup-auth', '1146803310301523968', '0,1145586687131246592,1146803310301523968', '角色权限设置', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-09 16:29:35', '2019-07-09 16:29:35', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1149289793895292928', 'cms-article-list', '1145586936444870656', '0,1145586936444870656', '文章管理', '', '/a/cms/article/list.html', 11, 2, 1, 0, '', NULL, NULL, '2019-07-11 20:10:14', '2019-07-11 20:10:14', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1149293579818848256', 'cms-category', '1145586936444870656', '0,1145586936444870656', '文章分类管理', '', '/a/cms/category/list.html', 11, 2, 1, 0, '', NULL, NULL, '2019-07-11 20:25:17', '2019-07-11 20:25:17', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1149294897491738624', 'sys-code-gen', '1145586687131246592', '0,1145586687131246592', '代码生成', '', '/a/sys/code/list.html', 11, 2, 1, 0, '', NULL, NULL, '2019-07-11 20:30:31', '2019-07-11 20:30:31', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1150432984603013120', 'resource', '0', '0', '资源管理', '', '', 11, 1, 1, 1, '', NULL, NULL, '2019-07-14 23:52:52', '2019-07-14 23:52:52', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1150433227490963456', 'sys-resource-image', '1150432984603013120', '0,1150432984603013120', '图片库', '', '/a/sys/imageGallery/list.html', 11, 2, 1, 0, '', NULL, NULL, '2019-07-14 23:53:50', '2019-07-14 23:54:49', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1152614987142144000', 'cs', '0', '0', '在线聊天', '', '', 11, 1, 1, 1, '', NULL, NULL, '2019-07-21 00:23:22', '2019-07-21 19:15:03', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1152615593470730240', 'cm-chat-page', '1152614987142144000', '0,1152614987142144000', '进入系统', '', '/a/demo/layim.html', 8, 2, 1, 0, '', NULL, NULL, '2019-07-21 00:25:47', '2019-07-21 18:17:41', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1152822617061474304', 'demo', '0', '0', '页面Demo', '', '', 11, 1, 1, 1, '', NULL, NULL, '2019-07-21 14:08:25', '2019-07-21 14:08:25', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1152822814487363584', 'demo-component', '1152822617061474304', '0,1152822617061474304', '组件展示', '', '/a/demo/demo.html', 11, 2, 1, 0, '', NULL, NULL, '2019-07-21 14:09:12', '2019-07-24 23:14:45', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1152852499152547840', 'cs-csUser', '1152614987142144000', '0,1152614987142144000', '聊天用户', '', '/a/cs/csUser/list.html', 9, 2, 1, 0, '', NULL, NULL, '2019-07-21 16:07:09', '2019-07-24 23:12:40', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1152884133543104512', 'cs-csGroupTeam', '1152614987142144000', '0,1152614987142144000', '群组', '', '/a/cs/csGroupTeam/list.html', 13, 2, 1, 0, '', NULL, NULL, '2019-07-21 18:12:52', '2019-07-24 23:12:58', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1152884352745820160', 'cs-csGroupTeamUser', '1152614987142144000', '0,1152614987142144000', '群组用户', '', '/a/cs/csGroupTeamUser/list.html', 14, 2, 1, 0, '', NULL, NULL, '2019-07-21 18:13:44', '2019-07-21 18:19:05', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1152988620124069888', 'sys-imageGallery-list', '1150433227490963456', '0,1150432984603013120,1150433227490963456', '图片列表', '', '', 11, NULL, 0, 0, '', NULL, NULL, '2019-07-22 01:08:03', '2019-07-22 01:08:03', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1153848091057086464', 'cs-csUserContact', '1152614987142144000', '0,1152614987142144000', '联系人', '', '/a/cs/csUserContact/list.html', 11, 2, 1, 0, '', NULL, NULL, '2019-07-24 10:03:17', '2019-07-25 23:48:33', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1153848316463177728', 'cs-csUserContactGroup', '1152614987142144000', '0,1152614987142144000', '联系人分组', '', '/a/cs/csUserContactGroup/list.html', 11, 2, 1, 0, '', NULL, NULL, '2019-07-24 10:04:11', '2019-07-25 23:49:16', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154025160857862144', 'sys-doc-api', '1145586687131246592', '0,1145586687131246592', 'Swagger-Api', '', '/swagger-ui.html', 35, 2, 1, 0, '', NULL, NULL, '2019-07-24 21:46:54', '2019-07-24 21:50:27', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154025390672166912', 'sys-druid-monitor', '1145586687131246592', '0,1145586687131246592', '数据源监控', '', '/druid/sql.html', 36, 2, 1, 0, '', NULL, NULL, '2019-07-24 21:47:49', '2019-07-24 21:50:49', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154412155019124736', 'cs-csUser-list', '1152852499152547840', '0,1152614987142144000,1152852499152547840', '聊天用户列表', '', '', 11, 3, 0, 0, '', NULL, NULL, '2019-07-25 23:24:40', '2019-07-25 23:24:40', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154412426818412544', 'cs-csUser-add', '1152852499152547840', '0,1152614987142144000,1152852499152547840', '聊天用户添加', '', '', 11, 3, 0, 0, '', NULL, NULL, '2019-07-25 23:25:45', '2019-07-25 23:25:45', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154412554111344640', 'cs-csUser-edit', '1152852499152547840', '0,1152614987142144000,1152852499152547840', '聊天用户修改', '', '', 11, 3, 0, 0, '', NULL, NULL, '2019-07-25 23:26:16', '2019-07-25 23:26:16', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154412673309270016', 'cs-csUser-delete', '1152852499152547840', '0,1152614987142144000,1152852499152547840', '聊天用户删除', '', '', 11, 3, 0, 0, '', NULL, NULL, '2019-07-25 23:26:44', '2019-07-25 23:26:44', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154416499651174400', 'cs-csUserContactGroup-list', '1153848316463177728', '0,1152614987142144000,1153848316463177728', '联系人分组列表', '', '', 11, 3, 0, 0, '', NULL, NULL, '2019-07-25 23:41:56', '2019-07-25 23:49:51', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154416699182604288', 'cs-csUserContactGroup-add', '1153848316463177728', '0,1152614987142144000,1153848316463177728', '联系人分组添加', '', '', 11, 3, 0, 0, '', NULL, NULL, '2019-07-25 23:42:44', '2019-07-25 23:50:01', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154416776722702336', 'cs-csUserContactGroup-edit', '1153848316463177728', '0,1152614987142144000,1153848316463177728', '联系人分组修改', '', '', 11, 3, 0, 0, '', NULL, NULL, '2019-07-25 23:43:02', '2019-07-25 23:49:47', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154416870121463808', 'cs-csUserContactGroup-delete', '1153848316463177728', '0,1152614987142144000,1153848316463177728', '联系人分组删除', '', '', 11, 3, 0, 0, '', NULL, NULL, '2019-07-25 23:43:25', '2019-07-25 23:49:56', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154417000530763776', 'cs-csUserContact-list', '1153848091057086464', '0,1152614987142144000,1153848091057086464', '联系人列表', '', '', 11, 3, 0, 0, '', NULL, NULL, '2019-07-25 23:43:56', '2019-07-25 23:47:37', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154417091509411840', 'cs-csUserContact-add', '1153848091057086464', '0,1152614987142144000,1153848091057086464', '联系人添加', '', '', 11, 3, 0, 0, '', NULL, NULL, '2019-07-25 23:44:17', '2019-07-25 23:47:47', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154417161667534848', 'cs-csUserContact-edit', '1153848091057086464', '0,1152614987142144000,1153848091057086464', '联系人修改', '', '', 11, 3, 0, 0, '', NULL, NULL, '2019-07-25 23:44:34', '2019-07-25 23:47:29', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154417252226752512', 'cs-csUserContact-delete', '1153848091057086464', '0,1152614987142144000,1153848091057086464', '联系人删除', '', '', 11, 3, 0, 0, '', NULL, NULL, '2019-07-25 23:44:56', '2019-07-25 23:47:42', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1154559780093255680', 'cs-chat-mobile', '1152614987142144000', '0,1152614987142144000', '移动端', '', '/a/demo/mobile.html', 11, 2, 1, 0, '', NULL, NULL, '2019-07-26 09:11:17', '2019-07-26 09:11:17', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1155324134106599424', 'sys-mediaGallery', '1150432984603013120', '0,1150432984603013120', '媒体库', '', '/a/sys/mediaGallery/list.html', 11, 2, 1, 0, '', NULL, NULL, '2019-07-28 11:48:33', '2019-07-28 11:48:33', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1155429620658151424', 'demo-video', '1152822617061474304', '0,1152822617061474304', '视频播放', '', '/a/demo/video.html', 11, 2, 1, 0, '', NULL, NULL, '2019-07-28 18:47:43', '2019-07-28 18:48:26', NULL, 0);

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
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `log_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `succeed` tinyint(1) NULL DEFAULT NULL,
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
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
  `pids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
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
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 1, '0', '0', 'admin', '超级管理员', '1147401035526684672', '', '2019-07-06 00:53:23', '2019-07-08 00:09:38', NULL, 0);

-- ----------------------------
-- Table structure for sys_role_auth_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_auth_relation`;
CREATE TABLE `sys_role_auth_relation`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `auth_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `menu_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_role_id`(`role_id`) USING BTREE,
  INDEX `index_menu_id`(`menu_id`) USING BTREE,
  INDEX `index_auth_code`(`auth_code`) USING BTREE,
  INDEX `index_delflag`(`delflag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_auth_relation
-- ----------------------------
INSERT INTO `sys_role_auth_relation` VALUES ('1155483198178299904', '1', 'cs', '1152614987142144000', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483198522232832', '1', 'cs-chat-mobile', '1154559780093255680', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483198585147392', '1', 'cs-csUserContactGroup', '1153848316463177728', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483198639673344', '1', 'cs-csUserContact', '1153848091057086464', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483198727753728', '1', 'cs-csGroupTeam', '1152884133543104512', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483198765502464', '1', 'cs-csUser', '1152852499152547840', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483198820028416', '1', 'cs-csGroupTeamUser', '1152884352745820160', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483198857777152', '1', 'cm-chat-page', '1152615593470730240', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483198962634752', '1', 'demo', '1152822617061474304', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483199038132224', '1', 'demo-video', '1155429620658151424', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483199159767040', '1', 'demo-component', '1152822814487363584', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483199197515776', '1', 'resource', '1150432984603013120', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483199277207552', '1', 'sys-mediaGallery', '1155324134106599424', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483199310761984', '1', 'sys-resource-image', '1150433227490963456', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483199356899328', '1', 'sys', '1145586687131246592', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483199411425280', '1', 'sys-druid-monitor', '1154025390672166912', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483199474339840', '1', 'sys-doc-api', '1154025160857862144', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483199591780352', '1', 'sys-code-gen', '1149294897491738624', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483199868604416', '1', 'sys-dict', '1145586265012297728', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200002822144', '1', 'sys-dict-edit', '1147810246732013568', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200095096832', '1', 'sys-dict-list', '1147781499772407808', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200128651264', '1', 'sys-dict-delete', '1147781172163710976', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200158011392', '1', 'sys-dict-add', '1147780070462656512', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200195760128', '1', 'sys-role', '1146803310301523968', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200267063296', '1', 'sys-role-setup-auth', '1148509487464747008', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200300617728', '1', 'sys-role-list', '1148507956606701568', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200342560768', '1', 'sys-role-edit', '1147860171155156992', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200397086720', '1', 'sys-role-delete', '1147860093447286784', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200443224064', '1', 'sys-role-add', '1147859968306032640', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200472584192', '1', 'sys-dept', '1146047989245411328', '2019-07-28 22:20:37', '2019-07-28 22:20:37', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200535498752', '1', 'sys-dept-delete', '1148508822680145920', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200564858880', '1', 'sys-dept-edit', '1148508729482711040', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483200636162048', '1', 'sys-dept-add', '1148508644376088576', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483201533743104', '1', 'sys-dept-list', '1148508552550191104', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483201567297536', '1', 'sys-user', '1145586466829623296', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483201735069696', '1', 'sys-user-list', '1148508268184768512', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483201831538688', '1', 'sys-user-setup-role', '1147834171725299712', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483201860898816', '1', 'sys-user-delete', '1147834029647446016', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483201890258944', '1', 'sys-user-edit', '1147833955441819648', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483201928007680', '1', 'sys-user-add', '1147833849208487936', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483202808811520', '1', 'sys-menu', '1145585582578065408', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483202859143168', '1', 'sys-menu-delete', '1148507264819171328', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483202901086208', '1', 'sys-menu-edit', '1148507168681529344', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483202947223552', '1', 'sys-menu-add', '1148507079674204160', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483202980777984', '1', 'sys-menu-list', '1148506973277294592', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483203039498240', '1', 'cms', '1145586936444870656', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483203094024192', '1', 'cms-category', '1149293579818848256', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483203995799552', '1', 'cms-article-list', '1149289793895292928', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483204041936896', '1', 'cms-second', '1147544305674096640', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1155483204079685632', '1', 'cms-third', '1147544647300157440', '2019-07-28 22:20:38', '2019-07-28 22:20:38', 0, NULL);

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
INSERT INTO `sys_user` VALUES ('0', 'http://localhost:8080/swear/upload/img/2019-07-05/df4ccba4e667464985206b910c90f3d7.jpeg', 'admin', '113ca669352287722f0879f837a51f98', 'EBTkwj9L', '超级管理员', '2019-07-17', 0, 'swallowff@163.com', '13647617481', '1147186721762508800', '', 1, '2019-07-16 22:42:05', '2019-07-01 22:29:54', 0, NULL);

-- ----------------------------
-- Table structure for sys_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_relation`;
CREATE TABLE `sys_user_role_relation`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role_relation
-- ----------------------------
INSERT INTO `sys_user_role_relation` VALUES ('1147766270811906048', '0', '1', '2019-07-07 15:16:18', '2019-07-07 15:16:18', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
