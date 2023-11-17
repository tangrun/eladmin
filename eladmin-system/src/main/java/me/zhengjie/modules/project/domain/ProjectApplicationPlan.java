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
* @date 2023-11-14
**/
@Entity
@Data
@Table(name="project_application_plan")
public class ProjectApplicationPlan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`plan_id`")
    @ApiModelProperty(value = "ID")
    private Long planId;

    @Column(name = "`project_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @Column(name = "`name`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "目标名称")
    private String name;

    @Column(name = "`target_type`")
    @ApiModelProperty(value = "目标阶段：投入、产出、影响、成效")
    private String targetType;

    @Column(name = "`target_num`")
    @ApiModelProperty(value = "目标值")
    private Integer targetNum;

    @Column(name = "`weight`")
    @ApiModelProperty(value = "权重")
    private BigDecimal weight;

    @Column(name = "`start_time`")
    @ApiModelProperty(value = "开始时间")
    private Timestamp startTime;

    @Column(name = "`end_time`")
    @ApiModelProperty(value = "结束时间")
    private Timestamp endTime;

    @Column(name = "`remark`")
    @ApiModelProperty(value = "备注")
    private String remark;

    @Column(name = "`create_by`")
    @ApiModelProperty(value = "录入人")
    private String createBy;

    @Column(name = "`create_time`")
    @ApiModelProperty(value = "录入时间")
    private Timestamp createTime;

    public void copy(ProjectApplicationPlan source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
