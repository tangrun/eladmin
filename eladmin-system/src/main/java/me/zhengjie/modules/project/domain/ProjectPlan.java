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

import me.zhengjie.base.BaseEntity;
import me.zhengjie.domain.LocalStorage;
import me.zhengjie.modules.system.domain.Dict;
import me.zhengjie.modules.system.domain.User;
import org.hibernate.annotations.*;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
* @website https://eladmin.vip
* @description /
* @author hb
* @date 2023-11-15
**/
@Entity
@Data
@Table(name="project_plan")
public class ProjectPlan extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`plan_id`")
    @ApiModelProperty(value = "计划ID")
    @NotNull(groups = Update.class)
    private Long planId;

    @OneToOne()
    @JoinColumn(name = "parent_id",referencedColumnName = "plan_id")
    @ApiModelProperty(value = "上级项目")
    private ProjectPlan parentId;

    @Column(name = "plan_status")
    @ApiModelProperty(value = "项目状态：0、储备；1、立项")
    private String planStatus;

    @Column(name = "`plan_name`")
    @ApiModelProperty(value = "项目名称")
    private String planName;

    @Column(name = "category")
    @ApiModelProperty(value = "项目类别")
    private String category;

    @Column(name = "source_funds")
    @ApiModelProperty(value = "资金来源：专项资金、配套资金、支持资金")
    private String sourceFunds;

    @Column(name = "`overview`")
    @ApiModelProperty(value = "项目概述")
    private String overview;

    @Column(name = "`remark`")
    @ApiModelProperty(value = "项目备注")
    private String remark;

    @Column(name = "`notice`")
    @ApiModelProperty(value = "项目公告")
    private String notice;

    @OneToOne()
    @JoinColumn(name = "leader_id",referencedColumnName = "user_id")
    @ApiModelProperty(value = "项目负责人")
    private User leader;

    @ManyToMany()
    @JoinColumn(name = "proposal_id",referencedColumnName = "storage_id")
    @ApiModelProperty(value = "项目书")
    private Set<LocalStorage> proposals;

    @ManyToMany()
    @JoinColumn(name = "contract_id",referencedColumnName = "storage_id")
    @ApiModelProperty(value = "项目合同")
    private Set<LocalStorage> contracts;

    @Column(name = "`start_time`")
    @ApiModelProperty(value = "启动时间")
    private Timestamp startTime;

    @Column(name = "`end_time`")
    @ApiModelProperty(value = "结束时间")
    private Timestamp endTime;

    @Column(name = "`contacts`")
    @ApiModelProperty(value = "联系人")
    private String contacts;

    @Column(name = "`phone`")
    @ApiModelProperty(value = "联系电话")
    private String phone;

    @Column(name = "`email`")
    @ApiModelProperty(value = "项目投稿邮箱")
    private String email;

    @Column(name = "`address`")
    @ApiModelProperty(value = "联系地址")
    private String address;

    @Column(name = "`deadline`")
    @ApiModelProperty(value = "投稿截止时间")
    private Timestamp deadline;

    @Column(name = "province")
    @ApiModelProperty(value = "省")
    private String province;
    @Column(name = "city")
    @ApiModelProperty(value = "市")
    private String city;
    @Column(name = "county")
    @ApiModelProperty(value = "区/县")
    private String county;
    @Column(name = "street")
    @ApiModelProperty(value = "街道")
    private String street;
    @Column(name = "community")
    @ApiModelProperty(value = "社区")
    private String community;


    public void copy(ProjectPlan source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
