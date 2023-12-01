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
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
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
@Table(name="project_application_budget")
public class ProjectBudget implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`budget_id`")
    @ApiModelProperty(value = "ID")
    private Long budgetId;

    @Column(name = "`project_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @Column(name = "`name`")
    @ApiModelProperty(value = "预算名称")
    private String name;

    @Column(name = "`budget_value`")
    @ApiModelProperty(value = "预算值")
    private BigDecimal budgetValue;

    @Column(name = "`remark`")
    @ApiModelProperty(value = "说明")
    private String remark;

    @Column(name = "`create_by`")
    @ApiModelProperty(value = "录入人")
    private String createBy;

    @Column(name = "`create_time`")
    @CreationTimestamp
    @ApiModelProperty(value = "录入时间")
    private Timestamp createTime;

    public void copy(ProjectBudget source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
