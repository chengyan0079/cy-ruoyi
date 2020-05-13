/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : cy-ruoyi-product

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-05-13 15:13:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_goods_info`
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_info`;
CREATE TABLE `tb_goods_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_no` varchar(100) DEFAULT NULL COMMENT '商品编号',
  `goods_name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `status` varchar(10) DEFAULT '0' COMMENT '商品状态 0-正常 1-下架',
  `goods_type` varchar(10) DEFAULT NULL COMMENT '商品类别 0-食品 1-家电 2-小商品 3-书籍 4-生活用品 5-虚拟商品',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品存货量',
  `buying_price` decimal(8,2) DEFAULT '0.00' COMMENT '商品进货单价',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `selling_price` decimal(8,2) DEFAULT '0.00' COMMENT '商品售价',
  `update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(20) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

-- ----------------------------
-- Records of tb_goods_info
-- ----------------------------
INSERT INTO `tb_goods_info` VALUES ('1', '21310001', 'csssss', '1', '1', '100', '50.11', '2020-03-24 15:45:43', null, '144.12', null, 'cy', null);
INSERT INTO `tb_goods_info` VALUES ('2', '21310002', 'csfds', '1', '2', '100', '150.11', '2020-03-24 15:46:07', null, '244.12', null, 'cy', null);
