/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50516
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50516
File Encoding         : 65001

Date: 2018-12-02 23:24:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `c_id` int(11) DEFAULT NULL,
  `content` longtext,
  `createTime` varchar(255) DEFAULT NULL,
  `modifyTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_article_category` (`c_id`),
  CONSTRAINT `fk_article_category` FOREIGN KEY (`c_id`) REFERENCES `tb_category` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('110', '12312321', '123456', '103', '11111111111111111', '2018-09-01 17:31:44', '2018-09-01 17:31:44');
INSERT INTO `tb_article` VALUES ('111', '12', '123456', '105', '222222222222222', '2018-09-01 18:15:04', '2018-09-01 18:15:04');
INSERT INTO `tb_article` VALUES ('112', '21321444444444444444', '123456', '103', '<div align=\"center\">\n	<strong><u>43567890-</u></strong>\n</div>', '2018-09-01 18:16:16', '2018-09-01 18:16:16');
INSERT INTO `tb_article` VALUES ('113', '345346', '123456', '104', '<div align=\"center\">\n	<em><u>12345678901111111</u></em> \n</div>', '2018-09-01 18:40:59', '2018-09-01 18:40:59');
INSERT INTO `tb_article` VALUES ('114', '1111', '123456', '103', '<div align=\"center\">\n	1111111123124324\n</div>', '2018-09-01 19:06:38', '2018-09-01 19:06:38');
INSERT INTO `tb_article` VALUES ('115', '222', '123456', '105', '<div align=\"center\">\n	22222\n</div>', '2018-09-01 19:10:21', '2018-09-01 19:10:21');
INSERT INTO `tb_article` VALUES ('116', '21312312', '123456', '103', '<div align=\"center\">\n	21312321312\n</div>', '2018-09-02 23:33:27', '2018-09-02 23:33:27');
INSERT INTO `tb_article` VALUES ('117', '111111', '123456', '103', '11111111', '2018-09-03 00:59:40', '2018-09-03 00:59:40');
INSERT INTO `tb_article` VALUES ('118', '00000', '123456', '103', '123135156465465465465', '2018-09-03 01:01:14', '2018-09-03 01:01:14');
INSERT INTO `tb_article` VALUES ('119', '000000', '123456', '103', '000000', '2018-09-03 01:01:34', '2018-09-03 01:01:34');

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('103', '随笔');
INSERT INTO `tb_category` VALUES ('104', 'java');
INSERT INTO `tb_category` VALUES ('105', '大数据');
INSERT INTO `tb_category` VALUES ('106', 'linux');
INSERT INTO `tb_category` VALUES ('107', '42342');
INSERT INTO `tb_category` VALUES ('108', 'asdfdgfg');

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `commentTime` varchar(255) DEFAULT NULL,
  `replyPerson` varchar(45) DEFAULT NULL,
  `reply` varchar(255) DEFAULT NULL,
  `replyTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_article` (`a_id`),
  CONSTRAINT `fk_comment_article` FOREIGN KEY (`a_id`) REFERENCES `tb_article` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES ('1', null, 'xub', 'hello', '1', 'aishan', '你好12312', '2018-08-29 21:09:29');
INSERT INTO `tb_comment` VALUES ('3', null, 'xub', 'hello', '1', 'aishan', '1234243254356345', '2018-08-28 18:17:28');
INSERT INTO `tb_comment` VALUES ('4', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('5', null, 'xub', 'hello', '1', 'aishan', '235436', '2018-08-28 18:17:36');
INSERT INTO `tb_comment` VALUES ('6', null, 'xub', 'hello', '1', 'aishan', '大家好', '2018-08-28 18:17:44');
INSERT INTO `tb_comment` VALUES ('7', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('8', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('9', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('10', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('11', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('12', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('13', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('14', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('15', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('16', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('17', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('18', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('19', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('20', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('21', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('22', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('23', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('24', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('25', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('26', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('27', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('28', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('29', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('30', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('31', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('32', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('33', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('34', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('35', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('36', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('37', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('38', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('39', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('40', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('41', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('42', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('43', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('44', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('45', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('46', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('47', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('48', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('49', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('50', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('51', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('52', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('53', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('54', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('55', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('56', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('57', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('58', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('59', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('60', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('61', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('62', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('63', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('64', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('65', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('66', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('67', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('68', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('69', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('70', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('71', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('72', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('73', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('74', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('75', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('76', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('77', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('78', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('79', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('80', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('81', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('82', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('83', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('84', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('85', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('86', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('87', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('88', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('89', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('90', null, 'xub', 'hello', '1', 'aishan', null, '243');
INSERT INTO `tb_comment` VALUES ('91', null, 'xub', 'hello', '1', 'aishan', null, '243');

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `createTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_message
-- ----------------------------
INSERT INTO `tb_message` VALUES ('103', '123', '8361@qq.com', 'hello', '111', '2018-08-28 18:26:36');

-- ----------------------------
-- Table structure for tb_timeline
-- ----------------------------
DROP TABLE IF EXISTS `tb_timeline`;
CREATE TABLE `tb_timeline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `color` char(1) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `createTime` varchar(255) DEFAULT NULL,
  `modifyTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_timeline
-- ----------------------------
INSERT INTO `tb_timeline` VALUES ('103', '恋爱开始', 'G', '黎清许和张嫒珊的第一天', '2017-11-22', '2018-08-28 18:21:11', '2018-08-28 19:50:57');
INSERT INTO `tb_timeline` VALUES ('104', '开始博客制作', 'G', '主要是作为娱乐，以及方便展示自己。当然也为了秀恩爱', '2018-04-18', '2018-08-28 18:21:50', '2018-08-28 18:21:50');
INSERT INTO `tb_timeline` VALUES ('105', '开始博客制作1', 'R', '完成博客架构设计。再接再厉', '2018-04-25', '2018-08-28 18:22:51', '2018-08-28 18:23:37');
INSERT INTO `tb_timeline` VALUES ('106', '后台源码完成', 'Y', '完成博客后台。再接再厉', '2018-05-01', '2018-08-28 18:23:30', '2018-08-28 18:23:30');
INSERT INTO `tb_timeline` VALUES ('107', '博客显示界面完成', 'Y', '心情很开心，开始老师的项目跟自己接的项目', '2018-05-12', '2018-08-28 18:24:08', '2018-08-28 18:29:45');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `createTime` varchar(255) DEFAULT NULL,
  `modifyTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('103', 'xub1997', '8C794BF29F7C86024DBEB4FE4EB368AA', '1', '2018-08-28 18:33:17', '2018-08-29 21:06:33');
INSERT INTO `tb_user` VALUES ('104', 'xubxub', 'AD297A569C9B4480D3A025111ABF7E4E', '2', '2018-08-28 18:34:21', '2018-08-28 18:34:21');
INSERT INTO `tb_user` VALUES ('105', '123456', 'C2832E6A06313FF1958EBF60D94619BA', '1', '2018-08-29 21:14:32', '2018-08-29 21:14:32');
INSERT INTO `tb_user` VALUES ('106', '12345678', '59C0CC8018EECF251AF3B0350F56CCAD', '1', '2018-08-29 21:16:31', '2018-08-29 21:16:31');
