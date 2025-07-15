/*
Navicat MySQL Data Transfer

Source Server         : mysqlOfManagerSoftware
Source Server Version : 80039
Source Host           : 8.138.176.215:3306
Source Database       : projectmasterdb

Target Server Type    : MYSQL
Target Server Version : 80039
File Encoding         : 65001

Date: 2025-05-03 20:18:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `yearmonth` date DEFAULT NULL COMMENT '计入年月',
  `business_type` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务类型',
  `industry_category` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属行业大类',
  `industry_subcategory` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属行业细分',
  `region` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地域',
  `project_scale` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目规模大小',
  `contract_sxsj` date DEFAULT NULL COMMENT '合同生效时间',
  `contract_status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '合同状态',
  `prjexec_status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目执行状态',
  `department` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目所属部门',
  `original_department` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '结转原部门',
  `project_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工程号',
  `project_phase` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目阶段',
  `contract_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '合同号',
  `project_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目名称',
  `construction_unit` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '建设单位（委托方）',
  `project_scope` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目规模及内容',
  `total_investment` decimal(12,6) DEFAULT NULL COMMENT '项目总投资(万元)',
  `amtIncTax` decimal(12,6) DEFAULT NULL COMMENT '合同金额（含税）',
  `amtExcTax` decimal(12,6) DEFAULT NULL COMMENT '合同金额（不含税）',
  `stageAmtIncTa` decimal(12,6) DEFAULT NULL COMMENT '阶段合同额（含税）',
  `project_manager` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目负责人',
  `negResp` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '合同洽谈负责人',
  `industry_director` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '行业总监',
  `execResp` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '执行负责人',
  `certResp` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '持证负责人',
  `eimResp` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'EIM负责人',
  `subcAmtIncTax` decimal(12,6) DEFAULT NULL COMMENT '分包金额（含税）',
  `recvdAmt` decimal(12,6) DEFAULT NULL COMMENT '项目已收款',
  `unrecdAmt` decimal(12,6) DEFAULT NULL COMMENT '项目未收款',
  `recvRatio` decimal(5,2) DEFAULT NULL COMMENT '已收款比例 %',
  `prevYrRecv` decimal(12,6) DEFAULT NULL COMMENT '以前年度收款',
  `currYrRecv` decimal(12,6) DEFAULT NULL COMMENT '本年度收款',
  `recvreMarks` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收费备注',
  `def1` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段1',
  `def2` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段2',
  `def3` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段3',
  `def4` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段4',
  `def5` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段5',
  `def6` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段6',
  `def7` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段7',
  `def8` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段8',
  `def9` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段9',
  `def10` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段10',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目信息表';
