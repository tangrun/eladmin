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
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author hb
* @date 2023-11-14
**/
@Entity
@Data
@Table(name="project_self")
public class ProjectSelf implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`self_id`")
    @ApiModelProperty(value = "自评ID")
    private Long selfId;

    @Column(name = "`project_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @Column(name = "`interim`")
    @ApiModelProperty(value = "评估期")
    private String interim;

    @Column(name = "`summary`")
    @ApiModelProperty(value = "项目执行总结")
    private String summary;

    @Column(name = "`next_rule`")
    @ApiModelProperty(value = "下一步工作安排记建议")
    private String nextRule;

    @Column(name = "`innovation`")
    @ApiModelProperty(value = "项目创新性")
    private String innovation;

    @Column(name = "`continuity`")
    @ApiModelProperty(value = "项目可持续性")
    private String continuity;

    @Column(name = "`characteristic`")
    @ApiModelProperty(value = "项目特色、高点")
    private String characteristic;

    @Column(name = "`reflect`")
    @ApiModelProperty(value = "项目反思")
    private String reflect;

    @Column(name = "`create_by`")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @Column(name = "`create_time`")
    @CreationTimestamp
    @ApiModelProperty(value = "创建日期")
    private Timestamp createTime;

    public void copy(ProjectSelf source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
