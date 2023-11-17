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
package me.zhengjie.modules.project.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

/**
* @website https://eladmin.vip
* @description /
* @author hb
* @date 2023-11-15
**/
@Data
public class ApplicationDto implements Serializable {

    /** ID */
    /** 防止精度丢失 */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long projectId;

    /** 机构ID */
    private Long organId;

    /** 项目名称 */
    private String projectName;

    /** 项目编号 */
    private String projectCode;

    /** 项目类别 */
    private String categoryId;

    /** 上级项目 */
    private Long parentId;

    /** 项目计划及资助方 */
    private Long planId;

    /** 项目负责人 */
    private Integer leaderId;

    /** 项目类型 */
    private String projectType;

    /** 省 */
    private Long provinceId;

    /** 市 */
    private Long cityId;

    /** 区 */
    private Long countyId;

    /** 街道 */
    private Long streetId;

    /** 社区 */
    private Long communityId;

    /** 启动时间 */
    private Timestamp startTime;

    /** 结束时间 */
    private Timestamp endTime;

    /** 项目概述 */
    private String overview;

    /** 需求分析 */
    private String demand;

    /** 项目备注 */
    private String remark;

    /** 项目状态：0、未开始；1、执行中；2、已完成 */
    private Integer projectStatus;

    /** 审批状态：0、未审批；1、审批通过；2、审批不通过 */
    private Integer approvalStatus;

    /** 项目书 */
    private String proposal;

    /** 项目合同 */
    private String contract;

    /** 合同金额 */
    private BigDecimal amount;

    /** 配套资金 */
    private BigDecimal counterpartFunding;

    /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createBy;
}