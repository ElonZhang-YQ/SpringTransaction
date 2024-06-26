/*
 Navicat Premium Data Transfer

 Source Server         : Elon`s Remote MySQL
 Source Server Type    : MySQL
 Source Server Version : 80020 (8.0.20)
 Source Host           : 192.168.31.77:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80020 (8.0.20)
 File Encoding         : 65001

 Date: 26/06/2024 18:31:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for transaction02
-- ----------------------------
DROP TABLE IF EXISTS `transaction02`;
CREATE TABLE `transaction02` (
  `id` int NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
