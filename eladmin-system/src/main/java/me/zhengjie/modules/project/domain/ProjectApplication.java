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
import me.zhengjie.domain.LocalStorage;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Set;

/**
* @website https://eladmin.vip
* @description /
* @author hb
* @date 2023-11-15
**/
@Entity
@Data
@Table(name="project_application")
public class ProjectApplication implements Serializable {

    @Id
    @Column(name = "project_id")
    @ApiModelProperty(value = "ID")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "dept_id",referencedColumnName = "dept_id")
    @ApiModelProperty(value = "机构部门")
    private Dept dept;

    @Column(name = "project_name")
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @Column(name = "project_code")
    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    @Column(name = "category")
    @ApiModelProperty(value = "项目类别")
    private String category;

    @Column(name = "project_type")
    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @OneToOne()
    @JoinColumn(name = "parent_id",referencedColumnName = "project_id")
    @ApiModelProperty(value = "上级项目")
    private ProjectApplication parentProject;

    @OneToOne()
    @JoinColumn(name = "plan_id",referencedColumnName = "plan_id")
    @ApiModelProperty(value = "项目计划及资助方")
    private PlanProject planProject;

    @OneToOne()
    @JoinColumn(name = "leader_id",referencedColumnName = "user_id")
    @ApiModelProperty(value = "项目负责人")
    private User leader;

    @Column(name = "province")
    @ApiModelProperty(value = "省")
    private String province;

    @Column(name = "city")
    @ApiModelProperty(value = "市")
    private String city;

    @Column(name = "county")
    @ApiModelProperty(value = "区")
    private String county;

    @Column(name = "street")
    @ApiModelProperty(value = "街道")
    private String street;

    @Column(name = "community")
    @ApiModelProperty(value = "社区")
    private String community;

    @Column(name = "start_time")
    @ApiModelProperty(value = "启动时间")
    private Timestamp startTime;

    @Column(name = "end_time")
    @ApiModelProperty(value = "结束时间")
    private Timestamp endTime;

    @Column(name = "overview")
    @ApiModelProperty(value = "项目概述")
    private String overview;

    @Column(name = "demand")
    @ApiModelProperty(value = "需求分析")
    private String demand;

    @Column(name = "remark")
    @ApiModelProperty(value = "项目备注")
    private String remark;

    @Column(name = "project_status")
    @ApiModelProperty(value = "项目状态：0、未开始；1、执行中；2、已完成")
    private String projectStatus;

    @Column(name = "approval_status")
    @ApiModelProperty(value = "审批状态：0、未审批；1、审批通过；2、审批不通过")
    private String approvalStatus;

    @ManyToMany()
    @JoinTable(
            joinColumns = {@JoinColumn(name = "plan_id",referencedColumnName = "plan_id")},
            inverseJoinColumns = {@JoinColumn(name = "storage_id",referencedColumnName = "storage_id")})
    @ApiModelProperty(value = "项目书")
    private Set<LocalStorage> proposals;

    @ManyToMany()
    @JoinTable(
            joinColumns = {@JoinColumn(name = "plan_id",referencedColumnName = "plan_id")},
            inverseJoinColumns = {@JoinColumn(name = "storage_id",referencedColumnName = "storage_id")})
    @ApiModelProperty(value = "项目合同")
    private Set<LocalStorage> contracts;

    @Column(name = "amount")
    @ApiModelProperty(value = "合同金额")
    private BigDecimal amount;

    @Column(name = "counterpart_funding")
    @ApiModelProperty(value = "配套资金")
    private BigDecimal counterpartFunding;


    public void copy(ProjectApplication source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
