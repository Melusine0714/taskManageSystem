/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : mission

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 03/03/2023 16:40:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan`  (
  `plan_id` int NOT NULL AUTO_INCREMENT,
  `plan_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `plan_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `plan_employee` int NOT NULL,
  `plan_start_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `plan_end_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `plan_status` int NOT NULL DEFAULT 0,
  `plan_is_feedback` int NOT NULL DEFAULT 0,
  `plan_feedback` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `task_id` int NOT NULL,
  PRIMARY KEY (`plan_id`, `task_id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of plan
-- ----------------------------
INSERT INTO `plan` VALUES (1, '创建数据库', '还有结构上的疑问', 2, '2023-02-23', '2023-02-23', 0, 0, NULL, 1);
INSERT INTO `plan` VALUES (2, '完成管理员功能', NULL, 2, '2023-02-24', '2023-02-24', 0, 0, NULL, 1);
INSERT INTO `plan` VALUES (5, '测试', '测试', 6, '2023-03-01', '2023-03-01', 1, 1, '123', 2);
INSERT INTO `plan` VALUES (7, 'ceshi', 'ceshi', 6, '2023-03-01', '2023-03-01', 2, 1, '1111', 2);
INSERT INTO `plan` VALUES (11, '1234', '1234', 6, '2023-02-28', '2023-03-04', 0, 0, NULL, 2);

-- ----------------------------
-- Table structure for supervisor_employee
-- ----------------------------
DROP TABLE IF EXISTS `supervisor_employee`;
CREATE TABLE `supervisor_employee`  (
  `supervisor_id` int NOT NULL,
  `employee_id` int NOT NULL,
  PRIMARY KEY (`supervisor_id`, `employee_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of supervisor_employee
-- ----------------------------
INSERT INTO `supervisor_employee` VALUES (3, 2);
INSERT INTO `supervisor_employee` VALUES (3, 4);
INSERT INTO `supervisor_employee` VALUES (5, 6);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `task_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `task_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `task_start_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `task_end_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `task_status` int NOT NULL DEFAULT 0,
  `supervisor_id` int NOT NULL,
  `employee_id` int NOT NULL,
  PRIMARY KEY (`task_id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `employee_id`(`employee_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (1, '第二阶段项目考试', '答辩', '2023-02-23', '2023-03-05', 0, 3, 2);
INSERT INTO `task` VALUES (2, '测试项目代码', '查找项目bug', '2023-02-26', '2023-02-26', 1, 5, 6);
INSERT INTO `task` VALUES (3, '测试', '检查代码bug', '2023-02-26', '2023-02-26', 1, 3, 2);
INSERT INTO `task` VALUES (6, '答辩', '今天下午答辩', '2023-03-01', '2023-03-01', 1, 3, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_type` int NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_birth` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  `user_degree` int NULL DEFAULT NULL,
  `user_position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_boarding_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_experience` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_account`(`user_account`) USING BTREE COMMENT '用户名不可重复'
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Ayano', 'Ayano2002', 1, 'Ayano', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, '吴泽安', '123', 3, '吴泽安', '男', '2002-08-18', 2, '后端开发', '2023-03-01', '无');
INSERT INTO `user` VALUES (3, 'lixi', '123', 2, '李熙', '男', NULL, 3, '开发部门主管', '2022-12-01', NULL);
INSERT INTO `user` VALUES (4, 'Ironina', '123', 3, 'Ironina', '男', '', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, 'Melusine', '123', 2, '美露莘', '女', '', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (6, 'shintaro', '123', 3, '伸太郎', '男', '', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (7, 'abigail', '123', 2, '阿比盖尔', '女', '', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (30, '1234', '1234', 3, 'qwer', '男', '2023-03-01', 3, '123', '2023-03-01', '1');

-- ----------------------------
-- Table structure for user_degree
-- ----------------------------
DROP TABLE IF EXISTS `user_degree`;
CREATE TABLE `user_degree`  (
  `degree_id` int NOT NULL AUTO_INCREMENT,
  `degree_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`degree_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_degree
-- ----------------------------
INSERT INTO `user_degree` VALUES (1, '中职');
INSERT INTO `user_degree` VALUES (2, '大专');
INSERT INTO `user_degree` VALUES (3, '本科');
INSERT INTO `user_degree` VALUES (4, '研究生');
INSERT INTO `user_degree` VALUES (5, '博士');

-- ----------------------------
-- Table structure for user_type
-- ----------------------------
DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type`  (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_type
-- ----------------------------
INSERT INTO `user_type` VALUES (1, '管理员');
INSERT INTO `user_type` VALUES (2, '主管');
INSERT INTO `user_type` VALUES (3, '员工');

SET FOREIGN_KEY_CHECKS = 1;
