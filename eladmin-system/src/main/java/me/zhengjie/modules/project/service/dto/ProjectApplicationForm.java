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

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.commons.pojo.IdObject;
import me.zhengjie.domain.LocalStorage;
import me.zhengjie.modules.project.domain.PlanProject;
import me.zhengjie.modules.project.domain.ProjectApplication;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.service.dto.DeptSmallDto;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

/**
* @website https://eladmin.vip
* @description /
* @author hb
* @date 2023-11-15
**/
@Data
public class ProjectApplicationForm implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long id;

    @JSONField(deserialize = false)
    @ApiModelProperty(value = "机构部门",hidden = true)
    private IdObject dept;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    @ApiModelProperty(value = "项目类别")
    private String category;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "上级 招标项目")
    private IdObject parentProject;

    @ApiModelProperty(value = "上级 储备项目")
    private IdObject planProject;

    @ApiModelProperty(value = "项目负责人")
    private IdObject leader;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String county;

    @ApiModelProperty(value = "街道")
    private String street;

    @ApiModelProperty(value = "社区")
    private String community;

    @ApiModelProperty(value = "启动时间")
    private Timestamp startTime;

    @ApiModelProperty(value = "结束时间")
    private Timestamp endTime;

    @ApiModelProperty(value = "项目概述")
    private String overview;

    @ApiModelProperty(value = "需求分析")
    private String demand;

    @ApiModelProperty(value = "项目备注")
    private String remark;

    @ApiModelProperty(value = "项目书")
    private Set<IdObject> proposals;

    @ApiModelProperty(value = "项目合同")
    private Set<IdObject> contracts;

    @ApiModelProperty(value = "合同金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "配套资金")
    private BigDecimal counterpartFunding;
}
