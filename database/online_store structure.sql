/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50742
 Source Host           : 8.130.118.219:3306
 Source Schema         : online_store

 Target Server Type    : MySQL
 Target Server Version : 50742
 File Encoding         : 65001

 Date: 18/07/2023 09:48:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品种类名称',
  `url_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `category_pk`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价ID',
  `content` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价内容',
  `uid` int(11) NOT NULL COMMENT '评价用户ID',
  `pid` int(11) NOT NULL COMMENT '商品ID',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_review_product`(`pid`) USING BTREE,
  INDEX `fk_review_user`(`uid`) USING BTREE,
  CONSTRAINT `fk_review_product` FOREIGN KEY (`pid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_review_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `post` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收者',
  `receiver_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收者手机号',
  `user_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户备注',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
  `pay_date` timestamp NULL DEFAULT NULL COMMENT '付款日期',
  `delivery_date` timestamp NULL DEFAULT NULL COMMENT '发货日期',
  `confirm_date` timestamp NULL DEFAULT NULL COMMENT '确认收货日期',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `status` int(255) NULL DEFAULT NULL COMMENT '订单状态，0 未付款，1 未发货，2 未收货，3 待评论',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_order_user`(`uid`) USING BTREE,
  CONSTRAINT `fk_order_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单项ID',
  `pid` int(11) NULL DEFAULT NULL COMMENT '商品ID',
  `oid` int(11) NULL DEFAULT NULL COMMENT '订单ID',
  `count` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_order_time_pid`(`pid`) USING BTREE COMMENT '商品ID索引',
  INDEX `index_order_item_oid`(`oid`) USING BTREE COMMENT '订单ID索引',
  CONSTRAINT `fk_orderitem_order` FOREIGN KEY (`oid`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_orderitem_product` FOREIGN KEY (`pid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单项表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `sub_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品小标题',
  `original_price` float NOT NULL COMMENT '原价',
  `promote_price` float NULL DEFAULT NULL COMMENT '活动价',
  `stock` int(11) NOT NULL COMMENT '库存',
  `cid` int(11) NOT NULL COMMENT '商品种类ID',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_product_category`(`cid`) USING BTREE,
  CONSTRAINT `fk_product_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1024 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for product_image
-- ----------------------------
DROP TABLE IF EXISTS `product_image`;
CREATE TABLE `product_image`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品图ID',
  `pid` int(11) NOT NULL COMMENT '商品ID',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片类型',
  `url_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片访问地址',
  `single_middle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'type_single类型的中等图片访问地址',
  `single_small` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'type_single类型的小图片OSS访问地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_productimage_product`(`pid`) USING BTREE,
  CONSTRAINT `fk_productimage_product` FOREIGN KEY (`pid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10255 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品图片表，存储了本地图片的名称（商品图片ID），图片类型标识了图片所在的文件夹' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性ID',
  `cid` int(11) NOT NULL COMMENT '商品种类ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_property_category`(`cid`) USING BTREE,
  CONSTRAINT `fk_property_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 312 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '此表存储不同商品种类的属性，一个商品种类可以有多个属性' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for property_value
-- ----------------------------
DROP TABLE IF EXISTS `property_value`;
CREATE TABLE `property_value`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int(11) NOT NULL COMMENT '商品ID',
  `ptid` int(11) NOT NULL COMMENT '商品对应种类所包含的属性ID，即属性表中的ID',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性值',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_propertyvalue_property`(`ptid`) USING BTREE,
  CONSTRAINT `fk_propertyvalue_property` FOREIGN KEY (`ptid`) REFERENCES `property` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14118 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '此表记录了所有商品的属性值。每个商品有一个类型，每个商品类型都会对应多个不同的属性，而这个表记录了不同商品的属性的值' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车内容 ID',
  `pid` int(11) NOT NULL COMMENT '商品 ID',
  `count` int(11) NOT NULL COMMENT '商品数量',
  `uid` int(11) NOT NULL COMMENT '用户 ID，即这个购物车内容是哪个用户的',
  `status` int(11) NOT NULL COMMENT '虚拟删除，1 是未删除，0 是已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_shopping_cart_user_id`(`uid`) USING BTREE COMMENT '会高频度使用用户ID查询该用户的购物车，因此在用户ID上建立索引',
  CONSTRAINT `foreign_key_shopping_cart_user_id` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱',
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮寄地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 119 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- View structure for comment_views
-- ----------------------------
DROP VIEW IF EXISTS `comment_views`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `comment_views` AS select `comment`.`id` AS `id`,`comment`.`content` AS `content`,`comment`.`pid` AS `pid`,`user`.`username` AS `username`,`comment`.`create_date` AS `create_date`,`comment`.`uid` AS `uid` from (`user` join `comment` on((`user`.`id` = `comment`.`uid`))) order by `comment`.`pid`;

-- ----------------------------
-- View structure for product_properties_view
-- ----------------------------
DROP VIEW IF EXISTS `product_properties_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `product_properties_view` AS select `property_value`.`id` AS `id`,`tab`.`id` AS `pid`,`tab`.`ptid` AS `ptid`,`tab`.`name` AS `name`,`property_value`.`value` AS `value` from (((select `product`.`id` AS `id`,`property`.`name` AS `name`,`property`.`id` AS `ptid` from (`property` left join `product` on((`product`.`cid` = `property`.`cid`))))) `tab` left join `property_value` on(((`tab`.`id` = `property_value`.`pid`) and (`tab`.`ptid` = `property_value`.`ptid`))));

-- ------------`----------------
-- Triggers structure for table comment
-- ----------------------------
DROP TRIGGER IF EXISTS `update_create_date`;
delimiter ;;
CREATE TRIGGER `update_create_date` BEFORE INSERT ON `comment` FOR EACH ROW BEGIN
    SET NEW.create_date = NOW();
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
