/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : cy-ruoyi-order

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-05-13 15:13:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_order_info`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_info`;
CREATE TABLE `tb_order_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户表id',
  `pay_amt` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '交易金额',
  `order_no` varchar(25) CHARACTER SET utf8mb4 NOT NULL COMMENT '订单编号',
  `remark` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  `pay_status` varchar(2) CHARACTER SET utf8mb4 NOT NULL COMMENT '订单交易状态 0-未支付 1-支付中 2-支付成功 3-支付失败',
  `pay_order_no` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '支付订单号',
  `pay_date` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '订单支付日期',
  `create_by` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建者',
  `create_time` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建时间',
  `pay_time` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '支付时间',
  `update_time` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='订单信息表';

-- ----------------------------
-- Records of tb_order_info
-- ----------------------------
INSERT INTO `tb_order_info` VALUES ('1', '1', '0.00', '1000001', null, '1', '1120032', '2020-03-23', 'cy', '2020-03-24 15:41:09', '2020-03-24 14:33:11', null, null);
INSERT INTO `tb_order_info` VALUES ('2', '1', '0.00', '1000002', null, '1', '1120033', '2020-03-23', 'cy', '2020-03-24 15:41:28', '2020-03-24 14:33:11', null, null);
