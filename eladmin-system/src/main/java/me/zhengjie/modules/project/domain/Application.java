/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.modules.project.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author hb
* @date 2023-11-15
**/
@Entity
@Data
@Table(name="project_application")
public class Application implements Serializable {

    @Id
    @Column(name = "`project_id`")
    @ApiModelProperty(value = "ID")
    private Long projectId;

    @Column(name = "`organ_id`")
    @ApiModelProperty(value = "机构ID")
    private Long organId;

    @Column(name = "`project_name`")
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @Column(name = "`project_code`")
    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    @Column(name = "`category_id`")
    @ApiModelProperty(value = "项目类别")
    private String categoryId;

    @Column(name = "`parent_id`")
    @ApiModelProperty(value = "上级项目")
    private Long parentId;

    @Column(name = "`plan_id`")
    @ApiModelProperty(value = "项目计划及资助方")
    private Long planId;

    @Column(name = "`leader_id`")
    @ApiModelProperty(value = "项目负责人")
    private Integer leaderId;

    @Column(name = "`project_type`")
    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @Column(name = "`province_id`")
    @ApiModelProperty(value = "省")
    private Long provinceId;

    @Column(name = "`city_id`")
    @ApiModelProperty(value = "市")
    private Long cityId;

    @Column(name = "`county_id`")
    @ApiModelProperty(value = "区")
    private Long countyId;

    @Column(name = "`street_id`")
    @ApiModelProperty(value = "街道")
    private Long streetId;

    @Column(name = "`community_id`")
    @ApiModelProperty(value = "社区")
    private Long communityId;

    @Column(name = "`start_time`")
    @ApiModelProperty(value = "启动时间")
    private Timestamp startTime;

    @Column(name = "`end_time`")
    @ApiModelProperty(value = "结束时间")
    private Timestamp endTime;

    @Column(name = "`overview`")
    @ApiModelProperty(value = "项目概述")
    private String overview;

    @Column(name = "`demand`")
    @ApiModelProperty(value = "需求分析")
    private String demand;

    @Column(name = "`remark`")
    @ApiModelProperty(value = "项目备注")
    private String remark;

    @Column(name = "`project_status`")
    @ApiModelProperty(value = "项目状态：0、未开始；1、执行中；2、已完成")
    private Integer projectStatus;

    @Column(name = "`approval_status`")
    @ApiModelProperty(value = "审批状态：0、未审批；1、审批通过；2、审批不通过")
    private Integer approvalStatus;

    @Column(name = "`proposal`")
    @ApiModelProperty(value = "项目书")
    private String proposal;

    @Column(name = "`contract`")
    @ApiModelProperty(value = "项目合同")
    private String contract;

    @Column(name = "`amount`")
    @ApiModelProperty(value = "合同金额")
    private BigDecimal amount;

    @Column(name = "`counterpart_funding`")
    @ApiModelProperty(value = "配套资金")
    private BigDecimal counterpartFunding;

    @Column(name = "`create_time`")
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "`create_by`")
    @ApiModelProperty(value = "创建人")
    private String createBy;

    public void copy(Application source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
