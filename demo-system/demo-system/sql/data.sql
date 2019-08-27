/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : house

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 27/08/2019 08:36:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_agent
-- ----------------------------
DROP TABLE IF EXISTS `t_agent`;
CREATE TABLE `t_agent`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '涓粙鐢ㄦ埛琛?',
  `nickName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鏄电О',
  `realName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鐪熷疄濮撳悕',
  `sex` tinyint(2) DEFAULT 0 COMMENT '0鏈煡锛?鐢凤紝2濂?',
  `desc` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鐢ㄦ埛鎻忚堪',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鎵嬫満鍙?',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鐢ㄦ埛瀵嗙爜',
  `photo` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '澶村儚',
  `loginTime` datetime(0) DEFAULT NULL COMMENT '涓婃鐧诲綍鏃堕棿',
  `sessionId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'sessionId',
  `device` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '璁惧绫诲瀷',
  `appVersion` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'app鐗堟湰鍙?',
  `createAt` datetime(0) DEFAULT NULL COMMENT '鍒涘缓鏃堕棿',
  `updateAt` datetime(0) DEFAULT NULL COMMENT '鏇存柊鏃堕棿',
  `cityName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鍩庡競鍚?',
  `companyId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鎵€灞炲叕鍙窱D',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_agent
-- ----------------------------
INSERT INTO `t_agent` VALUES ('1', 'xiejunbo', NULL, 0, NULL, '13527885182', 'e10adc3949ba59abbe56e057f20f883e', NULL, '2018-11-03 02:11:44', NULL, 'iPhone', '1.0.0', '2018-11-03 02:12:00', '2018-11-03 02:12:02', NULL, NULL);

-- ----------------------------
-- Table structure for t_agent_favor
-- ----------------------------
DROP TABLE IF EXISTS `t_agent_favor`;
CREATE TABLE `t_agent_favor`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '涓粙鐢ㄦ埛鏀惰棌琛?',
  `userId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鐢ㄦ埛ID',
  `houseId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '妤肩洏ID',
  `createAt` datetime(0) DEFAULT NULL COMMENT '鏀惰棌鏃堕棿',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_client
-- ----------------------------
DROP TABLE IF EXISTS `t_client`;
CREATE TABLE `t_client`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '瀹㈡簮琛?',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鍚嶅瓧',
  `sex` tinyint(2) DEFAULT 0 COMMENT '鎬у埆锛?鏈煡锛?鐢凤紝2濂?',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鎵嬫満鍙?',
  `buyWilling` tinyint(3) DEFAULT NULL COMMENT '璐埧鎰忔効锛?鏄燂紝2鏄燂紝3鏄?',
  `idCard` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '韬唤璇佸彿',
  `age` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '骞撮緞鑼冨洿',
  `marry` tinyint(2) DEFAULT 0 COMMENT '濠氬Щ鐘舵€侊細0鏈煡锛?鏈锛?宸插',
  `localHouse` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鏈湴鎴垮眿鐘跺喌绠€杩?',
  `buyRight` tinyint(1) DEFAULT 0 COMMENT '璐埧璧勮川锛?鏈煡锛?鍏峰锛?涓嶅叿澶?',
  `moneyReady` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '棣栦粯棰勭畻/鎬婚绠?',
  `locationReady` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '璐埧浣嶇疆',
  `purpose` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '璐埧鐩殑锛氳嚜浣忥紝鎶曡祫',
  `acreage` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '闈㈢Н澶у皬:90骞崇背-100骞崇背',
  `standard` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '灞呭瑙勬牸:涓€灞呭',
  `houseType` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鐗╀笟绫诲瀷锛氬叕瀵擄紝浣忓畢',
  `payType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浠樻鏂瑰紡锛氬叏棰?',
  `houseWilling` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鎰忓悜妤肩洏',
  `floor` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '妤煎眰',
  `decoration` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '瑁呬慨绫诲瀷锛氱簿瑁呬慨锛岀畝瑁呬慨锛屾瘺鍧?',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '澶囨敞',
  `agentId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鎵€灞炵粡绾汉ID',
  `createAt` datetime(0) DEFAULT NULL COMMENT '鍒涘缓鏃堕棿',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '缁忕邯鍏徃琛?',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鍏徃鍚?',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '缁忕邯鍏徃缂栫爜',
  `createAt` datetime(0) DEFAULT NULL COMMENT '鍒涘缓鏃堕棿',
  `updateAt` datetime(0) DEFAULT NULL COMMENT '鏇存柊鏃堕棿',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_house
-- ----------------------------
DROP TABLE IF EXISTS `t_house`;
CREATE TABLE `t_house`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '妤肩洏涓昏〃',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '妤肩洏鍚?',
  `price` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浠锋牸锛?7000鍏?m^2',
  `tip` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '婵€鍔辨斂绛栵紝灏忔彁绀?',
  `lon` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '绾害',
  `lat` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '缁忓害',
  `devCompany` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '寮€鍙戝晢鍏徃鍚?',
  `state` tinyint(2) DEFAULT NULL COMMENT '妤肩洏鐘舵€侊細1鍦ㄥ敭锛?鍞畬',
  `averagePrice` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鍙傝€冨潎浠凤細55000鍏?骞虫柟绫?',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '澶囨敞',
  `createAt` datetime(0) DEFAULT NULL COMMENT '鍒涘缓鏃堕棿',
  `hot` tinyint(1) DEFAULT 0 COMMENT '鏄惁鐑棬妤肩洏锛?鍚︼紝1鏄?',
  `saleTime` datetime(0) DEFAULT NULL COMMENT '鏈€鏂板紑鐩樻棩鏈?',
  `commitTime` datetime(0) DEFAULT NULL COMMENT '浜ゆ埧鏃堕棿',
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鎵€鍦ㄧ渷',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鎵€鍦ㄥ競',
  `district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鎵€鍦ㄥ尯锛屽锛氬ぉ娌冲尯',
  `address` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '妤肩洏鍦板潃',
  `saleAddress` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鍞ゼ澶勫湴鍧€',
  `buildingType` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '寤虹瓚绫诲瀷:濉旀ゼ',
  `years` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鏉冮檺骞撮檺锛?0骞达紝50骞达紝40骞?',
  `decoration` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '瑁呬慨鏍囧噯锛氱簿瑁呬慨锛岀畝瑁呬慨锛屾瘺鍧?',
  `decorationPrice` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '瑁呬慨浠锋牸锛?500鍏?骞虫柟绫?',
  `greenRate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '缁垮寲鐜囷紝35%',
  `familyCount` int(8) DEFAULT NULL COMMENT '瑙勫垝鎴锋暟',
  `carPlace` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '瑙勫垝杞︿綅: 鍦颁笅1000锛屽湴闈?000',
  `houseType` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鐗╀笟绫诲瀷锛氫綇瀹咃紝鍏瘬',
  `houseCompany` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鐗╀笟鍏徃鍚?',
  `housePay` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鐗╀笟璐癸細2.8鍏?骞虫柟绫?鏈?',
  `hotAir` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '渚涙殩鏂瑰紡锛氭殏鏃犱俊鎭?',
  `subwayLine` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鍦伴搧锛?鍙风嚎',
  `subwayName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鍦伴搧绔欏悕瀛楋細鐕曞',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_house
-- ----------------------------
INSERT INTO `t_house` VALUES ('4bfc6d9fdfe111e8aaa90242c0a80002', '碧桂园二期01', '29500元/m^2', '小提示', '20', '45.23', '大公司', 1, '75000元/平方米', '备注', '2018-11-04 11:18:01', 1, '2018-11-08 11:18:11', '2018-12-13 11:18:17', '广东省', '广州市', '越秀区', '寺右新马路', '寺右新马路1号', '塔楼', '70年', '精装修', '2800元/平方米', '25%', 100, '地下3000', '住宅', '珠江物业', '3.8元/平方米/月', '地暖', '1号线', '体育西路');
INSERT INTO `t_house` VALUES ('4c0dba2adfe111e8aaa90242c0a80002', '碧桂园二期02', '39500元/m^2', '小提示', '20', '12.9', '大公司', 1, '45000元/平方米', '备注', '2018-11-04 11:18:01', 1, '2018-11-08 11:18:11', '2018-12-13 11:18:17', '广东省', '广州市', '越秀区', '寺右新马路', '寺右新马路2号', '塔楼', '50年', '毛坯', '2800元/平方米', '25%', 100, '地下3000', '住宅', '珠江物业', '3.8元/平方米/月', '地暖', '2号线', '嘉禾望岗');
INSERT INTO `t_house` VALUES ('4c101e8bdfe111e8aaa90242c0a80002', '碧桂园二期03', '19500元/m^2', '小提示', '20', '15', '大公司', 1, '34000元/平方米', '备注', '2018-11-04 11:18:01', 1, '2018-11-08 11:18:11', '2018-12-13 11:18:17', '广东省', '广州市', '越秀区', '寺右新马路', '寺右新马路3号', '塔楼', '70年', '精装修', '2800元/平方米', '25%', 100, '地下3000', '住宅', '珠江物业', '3.8元/平方米/月', '地暖', '5号线', '动物园');
INSERT INTO `t_house` VALUES ('4c127403dfe111e8aaa90242c0a80002', '碧桂园二期04', '69500元/m^2', '小提示', '20', '78', '大公司', 1, '74000元/平方米', '备注', '2018-11-04 11:18:01', 1, '2018-11-08 11:18:11', '2018-12-13 11:18:17', '广东省', '广州市', '越秀区', '寺右新马路', '寺右新马路4号', '塔楼', '50年', '毛坯', '2800元/平方米', '25%', 100, '地下3000', '住宅', '珠江物业', '3.8元/平方米/月', '地暖', '3号线', '体育西路');
INSERT INTO `t_house` VALUES ('4c14d16cdfe111e8aaa90242c0a80002', '碧桂园二期05', '89500元/m^2', '小提示', '20', '65', '大公司', 1, '25000元/平方米', '备注', '2018-11-04 11:18:01', 1, '2018-11-08 11:18:11', '2018-12-13 11:18:17', '广东省', '广州市', '越秀区', '寺右新马路', '寺右新马路5号', '塔楼', '70年', '精装修', '2800元/平方米', '25%', 100, '地下3000', '住宅', '珠江物业', '3.8元/平方米/月', '地暖', '3号线', '体育西路');
INSERT INTO `t_house` VALUES ('4c1700e9dfe111e8aaa90242c0a80002', '碧桂园二期06', '37500元/m^2', '小提示', '20', '12', '大公司', 1, '46000元/平方米', '备注', '2018-11-04 11:18:01', 1, '2018-11-08 11:18:11', '2018-12-13 11:18:17', '广东省', '广州市', '越秀区', '寺右新马路', '寺右新马路6号', '塔楼', '50年', '毛坯', '2800元/平方米', '25%', 100, '地下3000', '住宅', '珠江物业', '3.8元/平方米/月', '地暖', '6号线', '动物园');
INSERT INTO `t_house` VALUES ('a9000648e01411e8985e309c231424e5', '碧桂园一期', '27000元/m^2', '小提示', '20', '20', '大公司', 1, '55000元/平方米', '备注', '2018-11-04 11:18:01', 1, '2018-11-08 11:18:11', '2018-12-13 11:18:17', '广东省', '广州市', '白云区', '白云大道', '白云大道北', '塔楼', '70年', '精装修', '2500元/平方米', '35%', 100, '地下1000', '住宅', '白云物业', '2.8元/平方米/月', '地暖', '3号线', '燕塘');

-- ----------------------------
-- Table structure for t_house_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_house_activity`;
CREATE TABLE `t_house_activity`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '妤肩洏鍔ㄦ€佽〃',
  `houseId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '妤肩洏ID',
  `title` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鏍囬',
  `desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鎻忚堪',
  `createAt` datetime(0) DEFAULT NULL COMMENT '鍒涘缓鏃堕棿',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_house_activity
-- ----------------------------
INSERT INTO `t_house_activity` VALUES ('0f027553e01c11e8985e309c231424e5', 'a9000648e01411e8985e309c231424e5', '碧桂园一期正式预售', '碧桂园一期正式预售碧桂园一期正式预售碧桂园一期正式预售碧桂园一期正式预售碧桂园一期正式预售', '2018-11-03 18:26:07');
INSERT INTO `t_house_activity` VALUES ('f3475388e01b11e8985e309c231424e5', 'a9000648e01411e8985e309c231424e5', '碧桂园一期正式开售', '碧桂园一期正式开售碧桂园一期正式开售碧桂园一期正式开售碧桂园一期正式开售', '2018-11-04 18:26:07');

-- ----------------------------
-- Table structure for t_house_label
-- ----------------------------
DROP TABLE IF EXISTS `t_house_label`;
CREATE TABLE `t_house_label`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '妤肩洏鏍囩琛?',
  `houseId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '妤肩洏ID',
  `labelName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鏍囩鍚?',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_house_label
-- ----------------------------
INSERT INTO `t_house_label` VALUES ('a9000648e01411e8985e309c231424e5', '0f1921a9dfe011e8aaa90242c0a80002', '品牌房企');
INSERT INTO `t_house_label` VALUES ('a9000648e01411e8985e309c231424e6', '0f1921a9dfe011e8aaa90242c0a80002', '住宅');
INSERT INTO `t_house_label` VALUES ('bbe6803ce01c11e8985e309c231424e5', 'a9000648e01411e8985e309c231424e5', '近地铁');

-- ----------------------------
-- Table structure for t_house_standard
-- ----------------------------
DROP TABLE IF EXISTS `t_house_standard`;
CREATE TABLE `t_house_standard`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '妤肩洏鎴峰瀷琛?',
  `houseId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '妤肩洏ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鎴峰瀷鍚?',
  `desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鎻忚堪',
  `acreage` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '闈㈢Н',
  `createAt` datetime(0) DEFAULT NULL COMMENT '鍒涘缓鏃堕棿',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_house_standard
-- ----------------------------
INSERT INTO `t_house_standard` VALUES ('91e01dcbe00311e8aaa20242ce580006', 'a9000648e01411e8985e309c231424e5', 'A0户型', '一房一厅', '50', '2018-11-04 15:33:21');
INSERT INTO `t_house_standard` VALUES ('91e01dcbe00311e8aaa23242ce534006', '4c101e8bdfe111e8aaa90242c0a80002', 'A1户型', '两房一厅', '90', '2018-11-04 15:32:09');
INSERT INTO `t_house_standard` VALUES ('91e01dcbe00311e8aaa23242de534006', '4c127403dfe111e8aaa90242c0a80002', 'A2户型', '两房两厅', '180', '2018-11-04 15:32:37');
INSERT INTO `t_house_standard` VALUES ('91e01dcbe00311e8aaa23242du734006', '4c1700e9dfe111e8aaa90242c0a80002', 'A4户型', '四房两厅', '200', '2018-11-04 15:32:37');
INSERT INTO `t_house_standard` VALUES ('91e01dcbe00311e8aaa53242de534006', '4c14d16cdfe111e8aaa90242c0a80002', 'A4户型', '四房两厅', '200', '2018-11-04 15:32:37');
INSERT INTO `t_house_standard` VALUES ('91e01dcbe00311e8aaa90242c0a80002', '0f1921a9dfe011e8aaa90242c0a80002', 'A1户型', '两房一厅', '90', '2018-11-04 15:32:09');
INSERT INTO `t_house_standard` VALUES ('91e01dcbe00311e8aaa90242ce534006', '4bfc6d9fdfe111e8aaa90242c0a80002', 'A1户型', '两房一厅', '90', '2018-11-04 15:32:09');
INSERT INTO `t_house_standard` VALUES ('91e01dcbe00311e8aaa90242ce580002', '0f1921a9dfe011e8aaa90242c0a80002', 'A2户型', '两房两厅', '120', '2018-11-04 15:32:37');
INSERT INTO `t_house_standard` VALUES ('91e01dcbe00311e8aaa90242ce580006', '0f1921a9dfe011e8aaa90242c0a80002', 'A3户型', '三房一厅', '150', '2018-11-04 15:33:01');
INSERT INTO `t_house_standard` VALUES ('91e01dcbe00311e8aaa93442ce534006', '4c0dba2adfe111e8aaa90242c0a80002', 'A2户型', '两房两厅', '120', '2018-11-04 15:32:37');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '数据管理', NULL, NULL, '0', '0');
INSERT INTO `t_menu` VALUES (2, '菜单管理', '/view/house/menuList', 1, '1', '1');
INSERT INTO `t_menu` VALUES (3, '角色管理', '/view/house/roleList', 1, '1', '2');
INSERT INTO `t_menu` VALUES (4, '用户管理', '/view/house/userList', 1, '1', '3');
INSERT INTO `t_menu` VALUES (10, '张老板的第一个小孩', '请填写子菜单url', 8, '1', '0');
INSERT INTO `t_menu` VALUES (11, '新闻管理', '/view/house/newsList', 1, '1', '4');

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '璧勮琛?',
  `type` tinyint(2) DEFAULT NULL COMMENT '0澶存潯锛?娲诲姩',
  `cover` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '灏侀潰鍥剧墖',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鏍囬',
  `desc` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鎻忚堪',
  `body` blob COMMENT '璧勮鐨刪tml鍐呭',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '澶囨敞',
  `createAt` datetime(0) DEFAULT NULL COMMENT '鍒涘缓鏃堕棿',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '閫氱煡琛?',
  `createAt` datetime(0) DEFAULT NULL COMMENT '鍒涘缓鏃堕棿',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '鎶ュ琛?',
  `houseId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '妤肩洏ID',
  `clientId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '瀹㈡簮ID',
  `agentId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '缁忕邯浜篒D',
  `meetTime` datetime(0) DEFAULT NULL COMMENT '棰勭害鏃堕棿',
  `state` tinyint(3) DEFAULT 0 COMMENT '鎶ュ鐘舵€?0寮€濮嬶紝1鍒拌锛?.鎴愪氦锛?.瀹℃牳锛?.棣栦粯锛?.鎸夋彮锛?.缁撲剑',
  `type` tinyint(1) DEFAULT NULL COMMENT '鎶ュ绫诲瀷锛?鏈煡锛?鍓嶄笁鍚庡洓鎶ュ',
  `managerPhone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '缁忕悊鑱旂郴鏂瑰紡',
  `managerLead` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '甯︾湅缁忕悊鍚?',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '澶囨敞',
  `createAt` datetime(0) DEFAULT NULL COMMENT '鍒涘缓鏃堕棿',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_photo
-- ----------------------------
DROP TABLE IF EXISTS `t_photo`;
CREATE TABLE `t_photo`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '鍥剧墖琛?',
  `relateId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '鍏宠仈ID锛?houseId锛宻tandardId锛?activityId',
  `type` tinyint(3) DEFAULT NULL COMMENT '鍏宠仈绫诲瀷锛?鎴峰瀷鍥撅紝1妤肩洏涓婚〉鍥? 2.妤肩洏鍔ㄦ€佸浘',
  `photo` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姝ｅ父鍥剧墖鍦板潃',
  `photoPlus` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '楂樻竻鍥剧墖鍦板潃',
  `createAt` datetime(0) DEFAULT NULL COMMENT '鍒涘缓鏃堕棿',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `state` int(255) DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'super', 1, 'super');
INSERT INTO `t_role` VALUES (2, '测试组', 1, NULL);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('2', '1');
INSERT INTO `t_role_menu` VALUES ('2', '2');
INSERT INTO `t_role_menu` VALUES ('2', '3');
INSERT INTO `t_role_menu` VALUES ('2', '4');
INSERT INTO `t_role_menu` VALUES ('1', '1');
INSERT INTO `t_role_menu` VALUES ('1', '2');
INSERT INTO `t_role_menu` VALUES ('1', '3');
INSERT INTO `t_role_menu` VALUES ('1', '4');
INSERT INTO `t_role_menu` VALUES ('1', '11');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` tinyint(255) DEFAULT NULL,
  `birthday` datetime(0) DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `old_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `login_time` datetime(0) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'ae48cb6843a8488901b741f556e7cd8c', 'admin', NULL, NULL, NULL, NULL, '1', 1, NULL, NULL, '2019-08-24 16:32:59', NULL, NULL);
INSERT INTO `t_user` VALUES ('61A786C702DC4272A6E390DF32CFFE45', 'wuchongcheng', 'ae48cb6843a8488901b741f556e7cd8c', 'wuchch', '563576277@qq.com', '18680523321', 1, NULL, '1', 2, NULL, NULL, '2019-08-24 15:38:16', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
