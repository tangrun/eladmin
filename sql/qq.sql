DROP TABLE IF EXISTS `sys_user_competent_apply`;
CREATE TABLE `sys_user_competent_apply`
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `administrative_level` int(5) DEFAULT NULL COMMENT '行政等级',
    `province`             varchar(20)  DEFAULT NULL COMMENT '省',
    `city`                 varchar(20)  DEFAULT NULL COMMENT '市',
    `county`               varchar(20)  DEFAULT NULL COMMENT '县、区',
    `street`               varchar(50)  DEFAULT NULL COMMENT '街道',
    `community`            varchar(50)  DEFAULT NULL COMMENT '社区',
    `name`                 varchar(50)  DEFAULT NULL COMMENT '机构名称',
    `description`          varchar(255) DEFAULT NULL COMMENT '机构简介',
    `phone`                varchar(50)  DEFAULT NULL COMMENT '机构电话',
    `email`                varchar(50)  DEFAULT NULL COMMENT '机构邮箱',
    `fax`                  varchar(50)  DEFAULT NULL COMMENT '机构传真',
    `address`              varchar(255) DEFAULT NULL COMMENT '机构地址',
    `manager_name`         varchar(50)  DEFAULT NULL COMMENT '管理员名字',
    `manager_phone`        varchar(50)  DEFAULT NULL COMMENT '管理员电话',
    `manager_email`        varchar(50)  DEFAULT NULL COMMENT '管理员邮箱',
    `state`                int(5) DEFAULT NULL COMMENT '审核状态',
    `remark`               varchar(255) DEFAULT NULL COMMENT '审核说明',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=196 ROW_FORMAT=COMPACT COMMENT='主管机构注册申请';

DROP TABLE IF EXISTS `sys_user_competent`;
CREATE TABLE `sys_user_competent`
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `administrative_level` int(5) DEFAULT NULL COMMENT '行政等级',
    `province`             varchar(20)  DEFAULT NULL COMMENT '省',
    `city`                 varchar(20)  DEFAULT NULL COMMENT '市',
    `county`               varchar(20)  DEFAULT NULL COMMENT '县、区',
    `street`               varchar(50)  DEFAULT NULL COMMENT '街道',
    `community`            varchar(50)  DEFAULT NULL COMMENT '社区',
    `name`                 varchar(50)  DEFAULT NULL COMMENT '机构名称',
    `description`          varchar(255) DEFAULT NULL COMMENT '机构简介',
    `phone`                varchar(50)  DEFAULT NULL COMMENT '机构电话',
    `email`                varchar(50)  DEFAULT NULL COMMENT '机构邮箱',
    `fax`                  varchar(50)  DEFAULT NULL COMMENT '机构传真',
    `address`              varchar(255) DEFAULT NULL COMMENT '机构地址',
    `manager_name`         varchar(50)  DEFAULT NULL COMMENT '管理员名字',
    `manager_phone`        varchar(50)  DEFAULT NULL COMMENT '管理员电话',
    `manager_email`        varchar(50)  DEFAULT NULL COMMENT '管理员邮箱',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=196 ROW_FORMAT=COMPACT COMMENT='主管机构';



DROP TABLE IF EXISTS `sys_user_expert_apply`;
CREATE TABLE `sys_user_expert_apply`
(
    `id`                  bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`                varchar(50)  DEFAULT NULL COMMENT '姓名',
    `sex`                 int(5) DEFAULT NULL COMMENT '性别 1男 2女',
    `company`             varchar(50)  DEFAULT NULL COMMENT '单位',
    `qualification`       varchar(20)  DEFAULT NULL COMMENT '资质',
    `political`           varchar(20)  DEFAULT NULL COMMENT '政治面貌',
    `identity`            varchar(20)  DEFAULT NULL COMMENT '身份证',
    `mobile`              varchar(20)  DEFAULT NULL COMMENT '电话',
    `bank_account_number` varchar(50)  DEFAULT NULL COMMENT '银行账户',
    `deposit_bank`        varchar(20)  DEFAULT NULL COMMENT '开户银行',
    `personal_profile`    varchar(500) DEFAULT NULL COMMENT '个人简介',
    `identity_front_id`   bigint(20) DEFAULT NULL COMMENT '身份证复印件正面',
    `identity_back_id`    bigint(20) DEFAULT NULL COMMENT '身份证复印件背面',
    `qualification_id`    bigint(20) DEFAULT NULL COMMENT '资质扫描件',
    `state`               int(5) DEFAULT NULL COMMENT '审核状态',
    `remark`              varchar(255) DEFAULT NULL COMMENT '审核说明',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=196 ROW_FORMAT=COMPACT COMMENT='评审专家注册申请';

DROP TABLE IF EXISTS `sys_user_expert`;
CREATE TABLE `sys_user_expert`
(
    `id`                  bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`                varchar(50)  DEFAULT NULL COMMENT '姓名',
    `sex`                 int(5) DEFAULT NULL COMMENT '性别 1男 2女',
    `company`             varchar(50)  DEFAULT NULL COMMENT '单位',
    `qualification`       varchar(20)  DEFAULT NULL COMMENT '资质',
    `political`           varchar(20)  DEFAULT NULL COMMENT '政治面貌',
    `identity`            varchar(20)  DEFAULT NULL COMMENT '身份证',
    `mobile`              varchar(20)  DEFAULT NULL COMMENT '电话',
    `bank_account_number` varchar(50)  DEFAULT NULL COMMENT '银行账户',
    `deposit_bank`        varchar(20)  DEFAULT NULL COMMENT '开户银行',
    `personal_profile`    varchar(500) DEFAULT NULL COMMENT '个人简介',
    `identity_front_id`   bigint(20) DEFAULT NULL COMMENT '身份证复印件正面',
    `identity_back_id`    bigint(20) DEFAULT NULL COMMENT '身份证复印件背面',
    `qualification_id`    bigint(20) DEFAULT NULL COMMENT '资质扫描件',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=196 ROW_FORMAT=COMPACT COMMENT='评审专家';



DROP TABLE IF EXISTS `sys_user_social_apply`;
CREATE TABLE `sys_user_social_apply`
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `administrative_level` int(5) DEFAULT NULL COMMENT '行政等级',
    `province`             varchar(20)  DEFAULT NULL COMMENT '省',
    `city`                 varchar(20)  DEFAULT NULL COMMENT '市',
    `county`               varchar(20)  DEFAULT NULL COMMENT '县、区',
    `street`               varchar(50)  DEFAULT NULL COMMENT '街道',
    `community`            varchar(50)  DEFAULT NULL COMMENT '社区',
    `name`                 varchar(50)  DEFAULT NULL COMMENT '机构名称',
    `description`          varchar(500) DEFAULT NULL COMMENT '机构简介',
    `credit_code`          varchar(50)  DEFAULT NULL COMMENT '机构社会信用代码',
    `manager_name`         varchar(20)  DEFAULT NULL COMMENT '管理员名字',
    `manager_phone`        varchar(20)  DEFAULT NULL COMMENT '管理员电话',
    `manager_email`        varchar(50)  DEFAULT NULL COMMENT '管理员邮箱',
    `business_license_id`  bigint(20) DEFAULT NULL COMMENT '资质扫描件',
    `state`                int(5) DEFAULT NULL COMMENT '审核状态',
    `remark`               varchar(255) DEFAULT NULL COMMENT '审核说明',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=196 ROW_FORMAT=COMPACT COMMENT='社会组织注册申请';

DROP TABLE IF EXISTS `sys_user_social`;
CREATE TABLE `sys_user_social`
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `administrative_level` int(5) DEFAULT NULL COMMENT '行政等级',
    `province`             varchar(20)  DEFAULT NULL COMMENT '省',
    `city`                 varchar(20)  DEFAULT NULL COMMENT '市',
    `county`               varchar(20)  DEFAULT NULL COMMENT '县、区',
    `street`               varchar(50)  DEFAULT NULL COMMENT '街道',
    `community`            varchar(50)  DEFAULT NULL COMMENT '社区',
    `name`                 varchar(50)  DEFAULT NULL COMMENT '机构名称',
    `description`          varchar(500) DEFAULT NULL COMMENT '机构简介',
    `credit_code`          varchar(50)  DEFAULT NULL COMMENT '机构社会信用代码',
    `manager_name`         varchar(20)  DEFAULT NULL COMMENT '管理员名字',
    `manager_phone`        varchar(20)  DEFAULT NULL COMMENT '管理员电话',
    `manager_email`        varchar(50)  DEFAULT NULL COMMENT '管理员邮箱',
    `business_license_id`  bigint(20) DEFAULT NULL COMMENT '资质扫描件',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=196 ROW_FORMAT=COMPACT COMMENT='社会组织';

