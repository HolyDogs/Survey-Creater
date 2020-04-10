/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : survey

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 29/04/2019 19:36:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for surveys
-- ----------------------------
DROP TABLE IF EXISTS `surveys`;
CREATE TABLE `surveys`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pageid` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userid` int(11) NOT NULL DEFAULT -1,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  CONSTRAINT `surveys_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 87 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `identify` int(11) NOT NULL,
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `possess` int(11) NULL DEFAULT 0,
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (6, 'admin', 0, '525443029@qq.com', 0, 'foWBiZExm5a15JkX9Emuuw==');
INSERT INTO `user` VALUES (7, '小强', 1, 'xf@qq.com', 6, 'wVckMshFXLPDZxPr3gAmYg==');