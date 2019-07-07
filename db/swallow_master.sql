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

 Date: 08/07/2019 01:14:58
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
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1147399870437113856', 11, '0', '0', '总部', '总部', NULL, '2019-07-06 15:00:31', '2019-07-06 15:00:31', NULL, 1);
INSERT INTO `sys_dept` VALUES ('1147399960534958080', 11, '0', '0', '', '重庆总公司', NULL, '2019-07-06 15:01:07', '2019-07-06 15:01:08', NULL, 0);
INSERT INTO `sys_dept` VALUES ('1147400031796183040', 11, '0', '0', '', '成都分公司', NULL, '2019-07-06 15:01:00', '2019-07-06 15:01:00', NULL, 0);
INSERT INTO `sys_dept` VALUES ('1147400152348868608', 11, '0', '0', '', '北京分公司', NULL, '2019-07-06 15:01:29', '2019-07-06 15:01:29', NULL, 0);
INSERT INTO `sys_dept` VALUES ('1147400211660521472', 11, '0', '0', '', '上海分公司', NULL, '2019-07-06 15:01:43', '2019-07-06 15:01:43', NULL, 0);
INSERT INTO `sys_dept` VALUES ('1147400357978816512', 11, '1147400211660521472', '0,1147400211660521472', '', '上海分公司-总经办', NULL, '2019-07-07 15:09:15', '2019-07-07 15:09:16', NULL, 0);
INSERT INTO `sys_dept` VALUES ('1147400433144938496', 11, '1147400211660521472', '0,1147400211660521472', '', '行政部', NULL, '2019-07-06 15:50:00', '2019-07-06 15:50:00', NULL, 1);
INSERT INTO `sys_dept` VALUES ('1147400494981562368', 11, '1147400211660521472', '0,1147400211660521472', '', '上海分公司-研发部', NULL, '2019-07-07 15:09:10', '2019-07-07 15:09:10', NULL, 0);
INSERT INTO `sys_dept` VALUES ('1147400547011903488', 11, '1147400211660521472', '0,1147400211660521472', '', '上海分公司-市场部', NULL, '2019-07-07 15:08:58', '2019-07-07 15:08:59', NULL, 0);
INSERT INTO `sys_dept` VALUES ('1147400648795078656', 11, '1147400152348868608', '0,1147400152348868608', '', '北京分公司-市场部', NULL, '2019-07-07 15:09:26', '2019-07-07 15:09:26', NULL, 0);
INSERT INTO `sys_dept` VALUES ('1147400747696766976', 11, '1147399960534958080', '0,1147399960534958080', '', '重庆总公司-研发部', NULL, '2019-07-07 15:09:51', '2019-07-07 15:09:52', NULL, 0);
INSERT INTO `sys_dept` VALUES ('1147400814939848704', 11, '1147399960534958080', '0,1147399960534958080', '', '重庆总公司-市场部', NULL, '2019-07-07 15:09:43', '2019-07-07 15:09:44', NULL, 0);
INSERT INTO `sys_dept` VALUES ('1147400938004922368', 11, '1147400031796183040', '0,1147400031796183040', '', '成都总公司-市场部', NULL, '2019-07-07 15:10:01', '2019-07-07 15:10:01', NULL, 0);
INSERT INTO `sys_dept` VALUES ('1147401035526684672', 11, '1147399960534958080', '0,1147399960534958080', '', '重庆总公司-总经办', NULL, '2019-07-07 15:09:37', '2019-07-07 15:09:37', NULL, 0);
INSERT INTO `sys_dept` VALUES ('1147414735902797824', 1, '0', '0', '', '测试部门', NULL, '2019-07-06 16:01:57', '2019-07-06 16:01:57', NULL, 1);
INSERT INTO `sys_dept` VALUES ('1147415283587948544', 12, '1147414735902797824', '0,1147414735902797824', '', '测试部门2', NULL, '2019-07-06 16:01:57', '2019-07-06 16:01:57', NULL, 1);
INSERT INTO `sys_dept` VALUES ('1147765012990468096', 11, '1147399960534958080', '0,1147399960534958080', '', '重庆总公司-运营部', NULL, '2019-07-07 15:11:18', '2019-07-07 15:11:18', NULL, 0);

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
INSERT INTO `sys_dict` VALUES ('1146014988489969664', '订单状态', 'order_status', '待支付', 0, '', 10, '2019-07-02 19:17:20', '2019-07-02 19:17:20', NULL, 0);
INSERT INTO `sys_dict` VALUES ('1147828791905468416', '订单状态', 'order_status', '支付中', 1, '', 11, '2019-07-07 19:24:44', '2019-07-07 19:24:44', NULL, 0);
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
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
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
INSERT INTO `sys_menu` VALUES ('1', 'sys', '0', '0', '系统管理', '', NULL, 1, 1, 1, NULL, NULL, NULL, NULL, NULL, '2019-07-01 14:03:34', NULL, 1);
INSERT INTO `sys_menu` VALUES ('1145585582578065408', 'sys-menu', '1145586687131246592', '0,1145586687131246592', '菜单管理', '', '/menu/list.html', 12, 2, 1, 0, '', NULL, NULL, '2019-07-01 14:51:01', '2019-07-01 16:25:14', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1145586265012297728', 'sys-dict', '1145586687131246592', '0,1145586687131246592', '字典管理', '', '/dict/list.html', 11, 2, 1, 0, '', NULL, NULL, '2019-07-01 14:53:44', '2019-07-07 00:49:04', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1145586466829623296', 'sys-user', '1145586687131246592', '0,1145586687131246592', '用户管理', '', '/user/list.html', 13, 2, 1, 0, '', NULL, NULL, '2019-07-01 14:54:32', '2019-07-01 16:25:23', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1145586687131246592', 'sys', '0', '0', '系统管理', '', '', 10, 1, 1, 1, '', NULL, NULL, '2019-07-01 14:55:25', '2019-07-01 16:22:30', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1145586936444870656', 'cms', '0', '0', 'CMS管理', '', '', 20, 1, 1, 1, '', NULL, NULL, '2019-07-01 14:56:24', '2019-07-01 16:22:14', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1146047989245411328', 'sys-dept', '1145586687131246592', '0,1145586687131246592', '部门管理', '', '/dept/list.html', 15, 2, 1, 0, '', NULL, NULL, '2019-07-02 21:28:28', '2019-07-02 21:28:28', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1146075598121975808', 'component-demo', '1145586687131246592', '0,1145586687131246592', '组件样例demo', '', '/common/demo.html', 50, 2, 1, 0, '', NULL, NULL, '2019-07-02 23:18:10', '2019-07-02 23:18:10', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1146803310301523968', 'sys-role', '1145586687131246592', '0,1145586687131246592', '角色管理', '', '/role/list.html', 13, 2, 1, 0, '', NULL, NULL, '2019-07-04 23:29:50', '2019-07-04 23:30:33', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147544305674096640', 'cms-second', '1145586936444870656', '0,1145586936444870656', 'CMS-二级', '', '', 11, 2, 1, 1, '', NULL, NULL, '2019-07-07 00:34:17', '2019-07-07 00:50:35', NULL, 0);
INSERT INTO `sys_menu` VALUES ('1147544647300157440', 'cms-third', '1147544305674096640', '0,1145586936444870656,1147544305674096640', 'CMS-三级', '', '/role/list.html', 10, 3, 1, 0, '', NULL, NULL, '2019-07-07 00:35:39', '2019-07-07 00:50:04', NULL, 0);
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
INSERT INTO `sys_menu` VALUES ('2', 'cms', NULL, NULL, 'CMS客户管理', NULL, NULL, 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES ('3', 'sys-user', NULL, NULL, '用户管理', NULL, '/user/list.html', 1, 2, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-01 14:39:57', NULL, 1);
INSERT INTO `sys_menu` VALUES ('4', 'sys-dict', NULL, NULL, '字典管理', NULL, '/dict/list.html', 1, 2, 0, NULL, NULL, NULL, NULL, NULL, '2019-07-01 14:49:08', NULL, 1);

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
-- Records of sys_operation_log
-- ----------------------------
INSERT INTO `sys_operation_log` VALUES ('1147914525425553408', '', '0', 1, 'cn.swallowff.modules.core.excepiton.NoPermissionsException: 权限异常\r\n	at cn.swallowff.modules.core.aop.PermissionAop.checkRoleCodes(PermissionAop.java:85)\r\n	at cn.swallowff.modules.core.aop.PermissionAop.doPermission(PermissionAop.java:65)\r\n	at sun.reflect.GeneratedMethodAccessor307.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at cn.swallowff.modules.core.system.controller.DictControllerTTEnhancerBySpringCGLIBTT6d76b857.listHtml(<generated>)\r\n	at sun.reflect.GeneratedMethodAccessor305.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:189)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:892)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:797)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1005)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:897)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:882)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at cn.swallowff.modules.core.filter.HttpServletRequestFilter.doFilter(HttpServletRequestFilter.java:34)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)\r\n	at org.apache.catalina.core.StandardContextValve.__invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:41002)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:834)\r\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:617)\r\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n', 3, NULL, NULL, '2019-07-08 01:05:25', '2019-07-08 01:05:25', NULL, 0);
INSERT INTO `sys_operation_log` VALUES ('1147915308441448448', '', '0', 1, 'cn.swallowff.modules.core.excepiton.NoPermissionsException: 权限异常\r\n	at cn.swallowff.modules.core.aop.PermissionAop.checkRoleCodes(PermissionAop.java:85)\r\n	at cn.swallowff.modules.core.aop.PermissionAop.doPermission(PermissionAop.java:65)\r\n	at sun.reflect.GeneratedMethodAccessor306.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at cn.swallowff.modules.core.system.controller.DictControllerTTEnhancerBySpringCGLIBTTaa34a773.listHtml(<generated>)\r\n	at sun.reflect.GeneratedMethodAccessor304.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:189)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:892)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:797)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1005)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:897)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:882)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at cn.swallowff.modules.core.filter.HttpServletRequestFilter.doFilter(HttpServletRequestFilter.java:34)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)\r\n	at org.apache.catalina.core.StandardContextValve.__invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:41002)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:834)\r\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:617)\r\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n', 3, NULL, NULL, '2019-07-08 01:08:31', '2019-07-08 01:08:31', NULL, 0);

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
INSERT INTO `sys_role` VALUES ('1147190777785622528', 10, '1147355770108231680', '0,1147186721762508800,1147355770108231680', NULL, '运营主管', '1147171194538803200', '', '2019-07-06 01:09:30', '2019-07-06 12:05:17', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147355770108231680', 1, '1147186721762508800', '0,1147186721762508800', NULL, '运营总监', '1147400938004922368', '', '2019-07-06 12:05:07', '2019-07-06 17:32:48', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147355891000655872', 10, '1147190777785622528', '0,1147186721762508800,1147355770108231680,1147190777785622528', NULL, '运营专员', '1147171194538803200', '', '2019-07-06 12:05:36', '2019-07-06 12:05:36', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147356735112724480', 12, '1147186721762508800', '0,1147186721762508800', NULL, '技术总监', '1147228408341856256', '', '2019-07-06 12:08:57', '2019-07-06 12:08:57', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147360106557194240', 10, '1147356735112724480', '0,1147186721762508800,1147356735112724480', NULL, '技术主管', '1147228408341856256', '', '2019-07-06 12:22:21', '2019-07-06 12:22:21', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147360231677476864', 12, '1147360106557194240', '0,1147186721762508800,1147356735112724480,1147360106557194240', NULL, '后端开发工程师', '1147228408341856256', '', '2019-07-06 12:22:51', '2019-07-06 12:22:51', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147360327957725184', 12, '1147360106557194240', '0,1147186721762508800,1147356735112724480,1147360106557194240', NULL, '运维工程师', '1147228408341856256', '', '2019-07-06 12:23:14', '2019-07-06 12:23:14', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147398274613493760', 12, '1147360106557194240', '0,1147186721762508800,1147356735112724480,1147360106557194240', NULL, '测试人员', '1147171194538803200', '', '2019-07-06 14:54:01', '2019-07-06 14:54:01', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147398513210671104', 12, '1147398274613493760', '0,1147186721762508800,1147356735112724480,1147360106557194240,1147398274613493760', NULL, '测试人员2', '1147228408341856256', '', '2019-07-06 14:54:58', '2019-07-06 14:54:58', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147398615455219712', 12, '1147398513210671104', '0,1147186721762508800,1147356735112724480,1147360106557194240,1147398274613493760,1147398513210671104', NULL, '测试3', '1147228408341856256', '', '2019-07-06 14:55:22', '2019-07-06 14:55:22', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147398677920989184', 12, '1147398615455219712', '0,1147186721762508800,1147356735112724480,1147360106557194240,1147398274613493760,1147398513210671104,1147398615455219712', NULL, '测试4', '1147228408341856256', '', '2019-07-06 14:55:37', '2019-07-06 14:55:37', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147765089536516096', 11, '0', '0', NULL, '运营', '1147765012990468096', '', '2019-07-07 15:11:36', '2019-07-07 15:11:36', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147765379237093376', 11, '1147765089536516096', '0,1147765089536516096', NULL, '运营主管', '1147765012990468096', '', '2019-07-07 15:12:46', '2019-07-07 15:12:46', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147765540898152448', 11, '0', '0', NULL, '后端开发', '1147400747696766976', '', '2019-07-07 15:13:24', '2019-07-07 15:13:24', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147765671324229632', 11, '0', '0', NULL, '前端开发', '1147400747696766976', '', '2019-07-07 15:13:55', '2019-07-07 15:13:55', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147765782360039424', 11, '1147765540898152448', '0,1147765540898152448', NULL, '后端主管', '1147400747696766976', '', '2019-07-07 15:14:22', '2019-07-07 15:14:22', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147766048975167488', 11, '1147765540898152448', '0,1147765540898152448', NULL, '项目经理', '1147400747696766976', '', '2019-07-07 15:15:25', '2019-07-07 15:15:25', NULL, 1);
INSERT INTO `sys_role` VALUES ('1147808255628795904', 10, '0', '0', NULL, '研发部', '1147400747696766976', '', '2019-07-07 18:03:08', '2019-07-07 18:03:08', NULL, 0);
INSERT INTO `sys_role` VALUES ('1147808346204790784', 11, '1147808255628795904', '0,1147808255628795904', 'tech-java', 'Java开发', '1147400747696766976', '', '2019-07-07 18:03:30', '2019-07-08 00:09:58', NULL, 0);
INSERT INTO `sys_role` VALUES ('1147808432641007616', 11, '1147808255628795904', '0,1147808255628795904', NULL, '前端开发', '1147400747696766976', '', '2019-07-07 18:03:50', '2019-07-07 18:03:50', NULL, 0);
INSERT INTO `sys_role` VALUES ('1147808511426813952', 11, '1147808255628795904', '0,1147808255628795904', NULL, '运维工程师', '1147400747696766976', '', '2019-07-07 18:04:09', '2019-07-07 18:04:09', NULL, 0);

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
INSERT INTO `sys_role_auth_relation` VALUES ('1147733163197571072', '1147190777785622528', 'cms', '1145586936444870656', '2019-07-07 13:04:45', '2019-07-07 13:04:45', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147733163315011584', '1147190777785622528', 'cms-second', '1147544305674096640', '2019-07-07 13:04:45', '2019-07-07 13:04:45', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147733163361148928', '1147190777785622528', 'cms-third', '1147544647300157440', '2019-07-07 13:04:45', '2019-07-07 13:04:45', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147748919324844032', '1147398513210671104', 'cms', '1145586936444870656', '2019-07-07 14:07:21', '2019-07-07 14:07:21', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147748919366787072', '1147398513210671104', 'cms-second', '1147544305674096640', '2019-07-07 14:07:21', '2019-07-07 14:07:21', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147748919404535808', '1147398513210671104', 'cms-third', '1147544647300157440', '2019-07-07 14:07:21', '2019-07-07 14:07:21', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147749807917506560', '1147398274613493760', 'sys', '1145586687131246592', '2019-07-07 14:10:53', '2019-07-07 14:10:53', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147749807967838208', '1147398274613493760', 'sys-menu', '1145585582578065408', '2019-07-07 14:10:53', '2019-07-07 14:10:53', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147777076379717632', '1147765782360039424', 'sys', '1145586687131246592', '2019-07-07 15:59:14', '2019-07-07 15:59:14', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147777076442632192', '1147765782360039424', 'sys-dict', '1145586265012297728', '2019-07-07 15:59:14', '2019-07-07 15:59:14', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147777076509741056', '1147765782360039424', 'sys-menu', '1145585582578065408', '2019-07-07 15:59:14', '2019-07-07 15:59:14', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147810326574784512', '1147808346204790784', 'sys', '1145586687131246592', '2019-07-07 18:11:22', '2019-07-07 18:11:22', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147810326612533248', '1147808346204790784', 'sys-dict', '1145586265012297728', '2019-07-07 18:11:22', '2019-07-07 18:11:22', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147810326683836416', '1147808346204790784', 'sys-dict-list', '1147781499772407808', '2019-07-07 18:11:22', '2019-07-07 18:11:22', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147810326725779456', '1147808346204790784', 'sys-dict-delete', '1147781172163710976', '2019-07-07 18:11:22', '2019-07-07 18:11:22', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147810326759333888', '1147808346204790784', 'sys-dict-add', '1147780070462656512', '2019-07-07 18:11:22', '2019-07-07 18:11:22', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068174176256', '1', 'sys', '1145586687131246592', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068228702208', '1', 'sys-dict', '1145586265012297728', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068270645248', '1', 'sys-dict-edit', '1147810246732013568', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068308393984', '1', 'sys-dict-list', '1147781499772407808', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068350337024', '1', 'sys-dict-delete', '1147781172163710976', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068388085760', '1', 'sys-dict-add', '1147780070462656512', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068425834496', '1', 'sys-role', '1146803310301523968', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068463583232', '1', 'sys-role-edit', '1147860171155156992', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068505526272', '1', 'sys-role-delete', '1147860093447286784', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068543275008', '1', 'sys-role-add', '1147859968306032640', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068581023744', '1', 'component-demo', '1146075598121975808', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068631355392', '1', 'sys-dept', '1146047989245411328', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068669104128', '1', 'sys-user', '1145586466829623296', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068702658560', '1', 'sys-user-setup-role', '1147834171725299712', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068744601600', '1', 'sys-user-delete', '1147834029647446016', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068786544640', '1', 'sys-user-edit', '1147833955441819648', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068824293376', '1', 'sys-user-add', '1147833849208487936', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068862042112', '1', 'sys-menu', '1145585582578065408', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068899790848', '1', 'cms', '1145586936444870656', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068937539584', '1', 'cms-second', '1147544305674096640', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);
INSERT INTO `sys_role_auth_relation` VALUES ('1147861068975288320', '1', 'cms-third', '1147544647300157440', '2019-07-07 21:33:00', '2019-07-07 21:33:00', 0, NULL);

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
INSERT INTO `sys_user` VALUES ('0', 'http://localhost:8080/swear/upload/img/2019-07-05/df4ccba4e667464985206b910c90f3d7.jpeg', 'admin', '89b09f643f5eeb2c1eeb904f1561df1b', 'yz2QNdhY', '超级管理员', '2019-07-17', 0, 'swallowff@163.com', '13647617481', '1147186721762508800', NULL, 1, '2019-07-06 16:21:26', '2019-07-01 22:29:54', 0, NULL);
INSERT INTO `sys_user` VALUES ('1145700901089951744', 'http://localhost:8080/swear/upload/img/2019-07-03/bd4cdbea207c40e1a0793018937cc0d9.jpeg', 'shenyu', 'f726f0ed80719458ccd425b8f55cbc31', 'WofSn8BA', '沈渝', '2019-07-10', 0, 'swallowff@163.com', '13647617481', '1147186721762508800', '', 1, '2019-07-07 15:59:32', '2019-07-01 22:29:16', 0, NULL);
INSERT INTO `sys_user` VALUES ('1145706884594966528', 'http://sy.ngrok.helloccs.cn:18099/swear/upload/img/2019-07-01/769659b816dd4edf94dbc4e9a9b07c1a.jpeg', 'exception', 'd0602a041f3aa10467e4fa5cfa14c1eb', 'oeCUGL4g', 'exception', NULL, NULL, NULL, '13647617481', NULL, NULL, 1, '2019-07-01 22:53:02', '2019-07-01 22:53:02', 1, NULL);
INSERT INTO `sys_user` VALUES ('1146021237445050368', '', 'op', '4a8742c5e95bd8303e7041387190e7d1', 'FCO370k5', 'abc1', NULL, 0, NULL, '13647617481', NULL, NULL, 1, '2019-07-02 19:46:29', '2019-07-02 19:42:10', 1, NULL);
INSERT INTO `sys_user` VALUES ('1146022998289367040', '', 'gggggg', '5a1de7f6f65173d9291f6f11def28c95', 'ED5HVRB5', 'gggggg', NULL, 1, NULL, '13647617481', NULL, NULL, 1, '2019-07-02 19:49:10', '2019-07-02 19:49:10', 1, NULL);
INSERT INTO `sys_user` VALUES ('1146026780062597120', 'http://localhost:8080/swear/upload/img/2019-07-02/9c84705b9a6c4127b501521408427001.jpg', 'swallowff', 'c22a0d72077f075f96ae1f1d1bb83fef', '5Yt56Dx5', 'shenyu', NULL, 0, NULL, '13647617481', NULL, NULL, 1, '2019-07-02 20:04:11', '2019-07-02 20:04:11', 1, NULL);
INSERT INTO `sys_user` VALUES ('1146027436487950336', '', 'swallow1', 'ad6ef8afbf83d5e4b3e8f84f87240223', 'UvH25AR6', 'swallow1', NULL, 0, NULL, '13647617481', NULL, NULL, 1, '2019-07-02 20:06:48', '2019-07-02 20:06:48', 1, NULL);
INSERT INTO `sys_user` VALUES ('1146027585461239808', '', 'abcd', '32e329d37c6f0df4d2cc2a7e63512454', 'xIkE7PKi', '123s', '2019-07-02', NULL, NULL, '13647617481', NULL, NULL, 1, '2019-07-02 20:25:07', '2019-07-02 20:07:23', 1, NULL);
INSERT INTO `sys_user` VALUES ('1146027733461450752', 'http://localhost:8080/swear/upload/img/2019-07-02/3f34314038e14c1585b00ccc8bfa67c3.jpg', 'abcde', '17ee654307869f98a58fdf6c85a8c251', 'Qx3k9VPD', 'abcde', '2019-07-06', 1, NULL, '13647617481', NULL, NULL, 1, '2019-07-02 20:30:38', '2019-07-02 20:07:58', 1, NULL);
INSERT INTO `sys_user` VALUES ('1146042609260081152', '', 'test', '', 'z3v0IhwB', 'test', '2019-07-11', NULL, '552395285@qq.com', '13647617481', NULL, NULL, 1, '2019-07-02 21:10:18', '2019-07-02 21:07:05', 1, NULL);
INSERT INTO `sys_user` VALUES ('1147422347466973184', 'http://localhost:8080/swear/upload/img/2019-07-06/bfa2c530587d433f82e767353485eaae.jpeg', 'swallowff', '3963100eb20ab39acf6f83f3e58e4e31', 'a5WSdGYu', '临时用户', '1986-07-06', 0, '552395285@qq.com', '13647617481', '1147360106557194240', '1147400494981562368', 1, '2019-07-07 18:04:35', '2019-07-06 16:29:40', 0, NULL);
INSERT INTO `sys_user` VALUES ('1147845781430980608', 'http://localhost:8080/swear/upload/img/2019-07-07/06f2799f214e4bc0a1d69348794263c2.jpeg', 'swallowfff', '2551d8e5d0c68c56da5608f4c1ba8ba7', 'dIEonQv2', '测试用户', '2019-07-07', 1, 'swallowfff@163.com', '13647617481', NULL, '1147400938004922368', 1, '2019-07-07 21:14:52', '2019-07-07 20:32:15', 1, NULL);

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
INSERT INTO `sys_user_role_relation` VALUES ('1147777002744516608', '1145700901089951744', '1147765540898152448', '2019-07-07 15:58:57', '2019-07-07 15:58:57', 0, NULL);
INSERT INTO `sys_user_role_relation` VALUES ('1147777002769682432', '1145700901089951744', '1147765782360039424', '2019-07-07 15:58:57', '2019-07-07 15:58:57', 0, NULL);
INSERT INTO `sys_user_role_relation` VALUES ('1147808681333874688', '1147422347466973184', '1147808255628795904', '2019-07-07 18:04:50', '2019-07-07 18:04:50', 0, NULL);
INSERT INTO `sys_user_role_relation` VALUES ('1147808681367429120', '1147422347466973184', '1147808346204790784', '2019-07-07 18:04:50', '2019-07-07 18:04:50', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
