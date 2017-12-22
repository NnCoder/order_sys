/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : order_demo

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-12-22 11:33:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `food_id` int(11) NOT NULL,
  `comment` text,
  `response` text,
  `customer_id` int(11) NOT NULL,
  `time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `food_id` (`food_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '订单', '商家回复: 测试', '1', '2017-12-14 23:17:40');
INSERT INTO `comment` VALUES ('24', '1', '好评:好吃好吃~', null, '2', '2017-12-15 22:51:04');
INSERT INTO `comment` VALUES ('25', '1', '好评:好吃好吃~', null, '2', '2017-12-16 04:21:57');
INSERT INTO `comment` VALUES ('26', '14', '满意:还行还行~ 不错', null, '2', '2017-12-16 20:49:54');
INSERT INTO `comment` VALUES ('27', '12', '好评:好吃好吃~', '商家回复: 感谢您的评价，我们会继续加油的~~', '2', '2017-12-18 10:55:10');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneNum` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', 'sadf', '123456', '123456', '吉林大学珠海学院竹园九栋');
INSERT INTO `customer` VALUES ('2', '陈mou', '123456', '13750033459', '吉林大学珠海学院竹园九栋');
INSERT INTO `customer` VALUES ('4', '沙发', '123456', '13750033458', '的');

-- ----------------------------
-- Table structure for customer_ordeer
-- ----------------------------
DROP TABLE IF EXISTS `customer_ordeer`;
CREATE TABLE `customer_ordeer` (
  `Customer_id` int(11) NOT NULL,
  `orders_id` int(11) NOT NULL,
  PRIMARY KEY (`Customer_id`,`orders_id`),
  UNIQUE KEY `orders_id` (`orders_id`),
  KEY `FKE28D8CD2C42598B8` (`orders_id`),
  KEY `FKE28D8CD22B78672C` (`Customer_id`),
  CONSTRAINT `FKE28D8CD22B78672C` FOREIGN KEY (`Customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKE28D8CD2C42598B8` FOREIGN KEY (`orders_id`) REFERENCES `ordeer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_ordeer
-- ----------------------------

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `imgSrc` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(20,2) NOT NULL,
  `shop_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK21807E3B82592C` (`shop_id`),
  CONSTRAINT `FK21807E3B82592C` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('1', 'nice', '/ord_upload/imgSrc/tiantiansifangmao-02.png', 'food', '5.00', '1');
INSERT INTO `food` VALUES ('2', '测试', '/ord_upload/imgSrc/tiantiansifangmao-02.png', '面包卷', '8.00', '1');
INSERT INTO `food` VALUES ('3', '我是要当 上海 贼王的男人', '/ord_upload/imgSrc/Game_Controller_128px_1187271_easyicon.net.ico', '路呃呃呃', '5.00', '1');
INSERT INTO `food` VALUES ('8', '123321', '/ord_upload/imgSrc/tiantiansifangmao-026.png', '123231', '123.00', '1');
INSERT INTO `food` VALUES ('9', '测试用', '/ord_upload/imgSrc/f99f82a38c58d7216c94f3b1ea70fc2c.jpg', '面包卷', '5.00', '2');
INSERT INTO `food` VALUES ('10', '测试用', '/ord_upload/imgSrc/2de8f24baaef53d65f4efbd8fb04d157.jpg', '薯条+鸡块+沙拉', '8.00', '2');
INSERT INTO `food` VALUES ('11', '测试用', '/ord_upload/imgSrc/5fa1c2b7af3133e5f9de707a0df3e95b.jpg', '草莓蛋糕', '9.00', '2');
INSERT INTO `food` VALUES ('12', '测试', '/ord_upload/imgSrc/022930bedfae5486d0ef8c513a2d2964.jpg', '至美火麒麟', '13.00', '3');
INSERT INTO `food` VALUES ('13', '测试', '/ord_upload/imgSrc/50bd8c21bfafa6e4e962f6a948b1ef92.jpg', '家常饭', '13.00', '3');
INSERT INTO `food` VALUES ('14', '测试', '/ord_upload/imgSrc/2ad8f2fa21944fed9c66f8d1e0d8ef14.jpg', '焖猪手饭', '13.00', '3');
INSERT INTO `food` VALUES ('15', '测试', '/ord_upload/imgSrc/a83fb4646c34830bfde2a8d19d6be244.jpg', '盖饭', '11.00', '3');
INSERT INTO `food` VALUES ('16', '测试', '/ord_upload/imgSrc/77ed36f4b18679ce54d4cebda306117e.jpg', '测试', '998.00', '3');

-- ----------------------------
-- Table structure for ordeer
-- ----------------------------
DROP TABLE IF EXISTS `ordeer`;
CREATE TABLE `ordeer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `arriveTime` timestamp NULL DEFAULT NULL,
  `orderTime` timestamp NULL DEFAULT NULL,
  `status` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `food_id` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '0',
  `remarks` text,
  PRIMARY KEY (`id`),
  KEY `FK8D444D713B82592C` (`shop_id`),
  KEY `FK8D444D712B78672C` (`customer_id`),
  KEY `food_id` (`food_id`),
  CONSTRAINT `FK8D444D712B78672C` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK8D444D713B82592C` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `ordeer_ibfk_1` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ordeer
-- ----------------------------
INSERT INTO `ordeer` VALUES ('1', null, '2017-09-09 11:07:59', '100', '2', '1', '1', '0', null);
INSERT INTO `ordeer` VALUES ('2', null, '2017-10-05 15:48:47', '100', '2', '1', '1', '0', null);
INSERT INTO `ordeer` VALUES ('3', null, '2017-10-05 20:55:58', '1', '1', '1', '1', '1', null);
INSERT INTO `ordeer` VALUES ('5', null, '2017-11-28 19:13:33', '1', '1', '1', '1', '0', null);
INSERT INTO `ordeer` VALUES ('6', null, '2017-11-28 19:33:15', '1', '1', '1', '1', '1', '');
INSERT INTO `ordeer` VALUES ('7', null, '2017-11-28 19:39:36', '1', '1', '1', '1', '1', '');
INSERT INTO `ordeer` VALUES ('8', null, '2017-11-28 19:40:09', '1', '1', '1', '1', '1', '即时取餐: 看');
INSERT INTO `ordeer` VALUES ('9', null, '2017-11-29 20:27:15', '99', '1', '1', '2', '0', '尽快送达');
INSERT INTO `ordeer` VALUES ('10', null, '2017-12-14 06:35:50', '100', '2', '1', '1', '0', '');
INSERT INTO `ordeer` VALUES ('11', null, '2017-12-14 20:59:21', '99', '2', '1', '1', '0', '');
INSERT INTO `ordeer` VALUES ('12', null, '2017-12-14 20:59:40', '99', '2', '1', '2', '1', '打包: 卡卡卡卡');
INSERT INTO `ordeer` VALUES ('13', null, '2017-12-14 21:00:05', '99', '2', '1', '2', '1', '即时取餐: 额');
INSERT INTO `ordeer` VALUES ('16', null, '2017-12-15 21:58:15', '-1', '2', '1', '1', '1', '即时取餐: ');
INSERT INTO `ordeer` VALUES ('17', null, '2017-12-15 22:00:05', '-1', '2', '1', '3', '1', '即时取餐: ');
INSERT INTO `ordeer` VALUES ('18', null, '2017-12-15 22:01:38', '-1', '2', '1', '8', '1', '即时取餐: ');
INSERT INTO `ordeer` VALUES ('19', '2017-12-16 04:20:14', '2017-12-15 22:12:41', '99', '2', '1', '3', '1', '即时取餐: ');
INSERT INTO `ordeer` VALUES ('20', '2017-12-16 04:18:26', '2017-12-15 22:14:40', '-1', '2', '1', '3', '0', '');
INSERT INTO `ordeer` VALUES ('21', '2017-12-16 04:18:24', '2017-12-15 22:34:33', '-1', '2', '1', '3', '0', '');
INSERT INTO `ordeer` VALUES ('22', '2017-12-16 04:18:20', '2017-12-15 22:47:46', '100', '2', '1', '1', '0', '');
INSERT INTO `ordeer` VALUES ('23', '2017-12-16 04:18:18', '2017-12-16 03:48:06', '99', '2', '1', '1', '0', '');
INSERT INTO `ordeer` VALUES ('24', '2017-12-16 04:18:17', '2017-12-16 03:55:43', '99', '2', '1', '1', '0', '');
INSERT INTO `ordeer` VALUES ('25', null, '2017-12-16 04:33:43', '99', '2', '3', '13', '0', '');
INSERT INTO `ordeer` VALUES ('26', '2017-12-16 04:39:26', '2017-12-16 04:33:48', '99', '2', '3', '14', '0', '');
INSERT INTO `ordeer` VALUES ('27', null, '2017-12-16 16:55:16', '0', '2', '3', '12', '1', '即时取餐: ');
INSERT INTO `ordeer` VALUES ('28', null, '2017-12-16 16:55:19', '99', '2', '3', '13', '0', '');
INSERT INTO `ordeer` VALUES ('29', null, '2017-12-16 16:55:22', '99', '2', '3', '15', '0', '');
INSERT INTO `ordeer` VALUES ('30', null, '2017-12-16 16:55:32', '99', '2', '3', '13', '0', '');
INSERT INTO `ordeer` VALUES ('31', null, '2017-12-16 16:59:31', '1', '2', '3', '14', '0', '');
INSERT INTO `ordeer` VALUES ('32', null, '2017-12-16 16:59:35', '1', '2', '3', '13', '0', '');
INSERT INTO `ordeer` VALUES ('33', null, '2017-12-16 16:59:57', '1', '2', '3', '14', '0', '');
INSERT INTO `ordeer` VALUES ('34', '2017-12-16 17:02:16', '2017-12-16 16:59:59', '100', '2', '3', '14', '0', '');
INSERT INTO `ordeer` VALUES ('35', null, '2017-12-16 17:00:02', '-1', '2', '3', '13', '0', '');
INSERT INTO `ordeer` VALUES ('36', null, '2017-12-16 20:54:36', '0', '2', '3', '12', '0', '');
INSERT INTO `ordeer` VALUES ('37', null, '2017-12-16 20:55:17', '0', '2', '3', '14', '0', '');
INSERT INTO `ordeer` VALUES ('38', null, '2017-12-17 21:46:38', '0', '2', '2', '11', '0', '');
INSERT INTO `ordeer` VALUES ('39', '2017-12-18 10:55:03', '2017-12-18 10:54:28', '100', '2', '3', '12', '0', 'dsce');

-- ----------------------------
-- Table structure for root
-- ----------------------------
DROP TABLE IF EXISTS `root`;
CREATE TABLE `root` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of root
-- ----------------------------
INSERT INTO `root` VALUES ('1', 'root', '123');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `status` tinyint(2) NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `imgSrc` varchar(255) DEFAULT 'assets/img/favicon.png',
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneNum` varchar(255) DEFAULT NULL,
  `idcard` varchar(18) DEFAULT NULL,
  `open` tinyint(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', '1', 'test', '吉林大学珠海学院竹园', '/ord_upload/imgSrc/Game_Controller_128px_1187271_easyicon.net.ico', '测试', '123456', '123456', '440582199705140914', '0');
INSERT INTO `shop` VALUES ('1', '2', null, '吉林大学珠海学院竹园4', '/ord_upload/imgSrc/timg1.jpg', '香香鸡专卖店', '123', '13755', '440582199705140914', '0');
INSERT INTO `shop` VALUES ('1', '3', 'test', '吉林大学珠海学院竹园9', '/ord_upload/imgSrc/timg.jpg', '测试', '123', '13750033459', '440582199705140914', '1');
INSERT INTO `shop` VALUES ('0', '4', '测试', null, '/ord_upload/imgSrc/tiantiansifangmao-022.png', '南昌月', '123456', '1234567', '440582199705140914', '0');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `food_id` int(11) DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKABBC40C83B82592C` (`shop_id`),
  KEY `FKABBC40C82B78672C` (`customer_id`),
  KEY `FKABBC40C897B8002C` (`food_id`),
  CONSTRAINT `FKABBC40C82B78672C` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKABBC40C83B82592C` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `FKABBC40C897B8002C` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES ('6', '2', '2', '1');
INSERT INTO `shoppingcart` VALUES ('7', '2', '1', '1');
INSERT INTO `shoppingcart` VALUES ('8', '2', '14', '3');
INSERT INTO `shoppingcart` VALUES ('9', '2', '13', '3');
INSERT INTO `shoppingcart` VALUES ('10', '2', '1', '1');
INSERT INTO `shoppingcart` VALUES ('11', '2', '2', '1');
INSERT INTO `shoppingcart` VALUES ('12', '2', '11', '2');
INSERT INTO `shoppingcart` VALUES ('13', '2', '13', '3');
