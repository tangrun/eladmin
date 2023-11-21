/*
 Navicat Premium Data Transfer

 Source Server         : 畅聊服务器 139
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 139.155.20.219:3306
 Source Schema         : eladmin

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 21/11/2023 17:03:25
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for project_active
-- ----------------------------
DROP TABLE IF EXISTS `project_active`;
CREATE TABLE `project_active`
(
    `active_id`   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '活动ID',
    `project_id`  bigint(20) NOT NULL COMMENT '项目ID',
    `plan_id`     bigint(20) NULL DEFAULT NULL COMMENT '项目目标ID',
    `name`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动主题',
    `content`     varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动内容',
    `active_time` datetime(0) NULL DEFAULT NULL COMMENT '活动时间',
    `venue`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动地点',
    `num`         int(11) NULL DEFAULT NULL COMMENT '参与活动人数',
    `effect`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动成效',
    `fee`         decimal(20, 0) NULL DEFAULT NULL COMMENT '活动费用',
    `ischecked`   tinyint(255) NULL DEFAULT 0 COMMENT '是否验收',
    `create_by`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`active_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '活动记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_application
-- ----------------------------
DROP TABLE IF EXISTS `project_application`;
CREATE TABLE `project_application`
(
    `project_id`          bigint(20) NOT NULL COMMENT 'ID',
    `organ_id`            bigint(20) NULL DEFAULT NULL COMMENT '机构ID',
    `project_name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目名称',
    `project_code`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目编号',
    `category_id`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目类别',
    `parent_id`           bigint(20) NULL DEFAULT NULL COMMENT '上级项目',
    `plan_id`             bigint(20) NULL DEFAULT NULL COMMENT '项目计划及资助方',
    `leader_id`           int(11) NULL DEFAULT NULL COMMENT '项目负责人',
    `project_type`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目类型',
    `province_id`         bigint(20) NULL DEFAULT NULL COMMENT '省',
    `city_id`             bigint(20) NULL DEFAULT NULL COMMENT '市',
    `county_id`           bigint(20) NULL DEFAULT NULL COMMENT '区',
    `street_id`           bigint(20) NULL DEFAULT NULL COMMENT '街道',
    `community_id`        bigint(20) NULL DEFAULT NULL COMMENT '社区',
    `start_time`          datetime(0) NULL DEFAULT NULL COMMENT '启动时间',
    `end_time`            datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
    `overview`            varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目概述',
    `demand`              varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '需求分析',
    `remark`              varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目备注',
    `project_status`      int(20) NULL DEFAULT NULL COMMENT '项目状态：0、未开始；1、执行中；2、已完成',
    `approval_status`     int(20) NULL DEFAULT NULL COMMENT '审批状态：0、未审批；1、审批通过；2、审批不通过',
    `proposal`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目书',
    `contract`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目合同',
    `amount`              decimal(20, 0) NULL DEFAULT NULL COMMENT '合同金额',
    `counterpart_funding` decimal(20, 0) NULL DEFAULT NULL COMMENT '配套资金',
    `create_time`         datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `create_by`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
    PRIMARY KEY (`project_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目申报' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_application_budget
-- ----------------------------
DROP TABLE IF EXISTS `project_application_budget`;
CREATE TABLE `project_application_budget`
(
    `budget_id`    bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `project_id`   bigint(20) NOT NULL COMMENT '项目ID',
    `name`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预算名称',
    `budget_value` decimal(20, 0) NULL DEFAULT NULL COMMENT '预算值',
    `totalize`     decimal(20, 0) NULL DEFAULT NULL COMMENT '累计值',
    `remark`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
    `create_by`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '录入人',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '录入时间',
    PRIMARY KEY (`budget_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目预算' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_application_plan
-- ----------------------------
DROP TABLE IF EXISTS `project_application_plan`;
CREATE TABLE `project_application_plan`
(
    `plan_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `project_id`  bigint(20) NOT NULL COMMENT '项目ID',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '目标名称',
    `target_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标阶段：投入、产出、影响、成效',
    `target_num`  int(11) NULL DEFAULT NULL COMMENT '目标值',
    `weight`      decimal(20, 0) NULL DEFAULT 0 COMMENT '权重',
    `start_time`  date NULL DEFAULT NULL COMMENT '开始时间',
    `end_time`    date NULL DEFAULT NULL COMMENT '结束时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    `create_by`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '录入人',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '录入时间',
    PRIMARY KEY (`plan_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目计划' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_application_team
-- ----------------------------
DROP TABLE IF EXISTS `project_application_team`;
CREATE TABLE `project_application_team`
(
    `member_id`   bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `project_id`  bigint(20) NULL DEFAULT NULL COMMENT '项目ID',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
    `sex`         int(11) NULL DEFAULT NULL COMMENT '性别',
    `age`         int(11) NULL DEFAULT NULL COMMENT '年龄',
    `title`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职称',
    `duties`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目职务',
    `mobile`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
    `email`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `entry_time`  date NULL DEFAULT NULL COMMENT '任职开始时间',
    `education`   varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学历',
    `speciality`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    `create_by`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '录入人',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '录入时间',
    PRIMARY KEY (`member_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目执行团队信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_category
-- ----------------------------
DROP TABLE IF EXISTS `project_category`;
CREATE TABLE `project_category`
(
    `category_id`   bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `pid`           bigint(20) NOT NULL COMMENT '上级类别ID',
    `name`          varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类别名称',
    `enabled`       bit(1)                                                        NOT NULL COMMENT '类别状态',
    `category_sort` int(5) NULL DEFAULT NULL COMMENT '排序',
    `create_by`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
    `update_by`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
    `create_time`   datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
    `update_time`   datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`category_id`) USING BTREE,
    UNIQUE INDEX `uniq_name`(`name`) USING BTREE,
    INDEX           `inx_enabled`(`enabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目类别' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for project_change
-- ----------------------------
DROP TABLE IF EXISTS `project_change`;
CREATE TABLE `project_change`
(
    `change_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '变更ID',
    `project_id`     bigint(20) NOT NULL COMMENT '项目ID',
    `change_type`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '变更类型：项目内容变更、预算变更',
    `change_time`    datetime(0) NULL DEFAULT NULL COMMENT '变更时间',
    `change_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '变更内容',
    `change_files`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '变更文件',
    PRIMARY KEY (`change_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目变更记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_experience
-- ----------------------------
DROP TABLE IF EXISTS `project_experience`;
CREATE TABLE `project_experience`
(
    `experience_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目经验ID',
    `project_id`    bigint(20) NOT NULL COMMENT '项目ID',
    `name`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目名称',
    `start_time`    datetime(0) NULL DEFAULT NULL COMMENT '开始日期',
    `work_unit`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行单位',
    `status`        varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目状态：0、执行中；1、已结项',
    `files`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目佐证',
    PRIMARY KEY (`experience_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目经验' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_month_work_report
-- ----------------------------
DROP TABLE IF EXISTS `project_month_work_report`;
CREATE TABLE `project_month_work_report`
(
    `report_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '报告ID',
    `project_id`     bigint(20) NOT NULL COMMENT '项目ID',
    `report_month`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '报告月份',
    `report_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工作总结',
    `create_by`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提交人',
    `create_time`    datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
    `files`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件',
    PRIMARY KEY (`report_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目月度工作报告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_pay
-- ----------------------------
DROP TABLE IF EXISTS `project_pay`;
CREATE TABLE `project_pay`
(
    `pay_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '支出ID',
    `project_id`  bigint(20) NOT NULL COMMENT '项目ID',
    `budget_id`   bigint(20) NULL DEFAULT NULL COMMENT '预算ID',
    `pay_type`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支出类型',
    `pay_date`    datetime(0) NULL DEFAULT NULL COMMENT '支出日期',
    `amount`      decimal(20, 0) NULL DEFAULT NULL COMMENT '支出金额',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支出说明',
    `create_by`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '录入人',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '录入时间',
    PRIMARY KEY (`pay_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目预算支出记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_plan
-- ----------------------------
DROP TABLE IF EXISTS `project_plan`;
CREATE TABLE `project_plan`
(
    `plan_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '计划ID',
    `parent_id`    bigint(20) NULL DEFAULT NULL COMMENT '上级项目',
    `plan_status`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目状态：0、储备；1、提交；2、立项',
    `plan_name`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目名称',
    `category_id`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目类别',
    `source`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资金来源：专项资金、配套资金、支持资金',
    `overview`     varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目概述',
    `remark`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目备注',
    `notice`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '项目公告',
    `leader_id`    int(11) NULL DEFAULT NULL COMMENT '项目负责人',
    `proposal`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目书',
    `contract`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目合同',
    `start_time`   datetime(0) NULL DEFAULT NULL COMMENT '启动时间',
    `end_time`     datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
    `create_by`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `contacts`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
    `phone`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
    `email`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目投稿邮箱',
    `address`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系地址',
    `deadline`     datetime(0) NULL DEFAULT NULL COMMENT '投稿截止时间',
    `landing_area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '落地地区',
    PRIMARY KEY (`plan_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '储备项目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_publicize
-- ----------------------------
DROP TABLE IF EXISTS `project_publicize`;
CREATE TABLE `project_publicize`
(
    `publicize_id` bigint(20) NOT NULL COMMENT '宣传ID',
    `project_id`   bigint(20) NOT NULL COMMENT '项目ID',
    `media_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '媒体名称',
    `theme`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '宣传主题',
    `media_level`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '媒体级别',
    `publish_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
    `url`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网站链接',
    `files`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
    `create_by`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`publicize_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '媒体宣传记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_self
-- ----------------------------
DROP TABLE IF EXISTS `project_self`;
CREATE TABLE `project_self`
(
    `self_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自评ID',
    `project_id`     bigint(20) NOT NULL COMMENT '项目ID',
    `interim`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评估期',
    `summary`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目执行总结',
    `next_rule`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下一步工作安排记建议',
    `innovation`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目创新性',
    `continuity`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目可持续性',
    `characteristic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目特色、高点',
    `reflect`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目反思',
    `create_by`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
    `create_time`    datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
    PRIMARY KEY (`self_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目自评记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_supervise
-- ----------------------------
DROP TABLE IF EXISTS `project_supervise`;
CREATE TABLE `project_supervise`
(
    `supervise_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '督导ID',
    `supervise_type`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '督导方式：个人督导、团体督导、线上督导、线下督导',
    `project_id`        bigint(20) NOT NULL COMMENT '项目ID',
    `content`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '督导内容',
    `theme`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '督导主题',
    `rating`            decimal(20, 0) NULL DEFAULT NULL COMMENT '督导评分',
    `advise`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '督导建议',
    `files`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
    `create_by`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '督导老师',
    `create_time`       datetime(0) NULL DEFAULT NULL COMMENT '督导时间',
    `address`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '督导地点',
    `supervised_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '被督导人',
    PRIMARY KEY (`supervise_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目督导记录' ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
