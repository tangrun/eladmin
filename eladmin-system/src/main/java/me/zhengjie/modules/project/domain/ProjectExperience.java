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
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author hb
* @date 2023-11-14
**/
@Entity
@Data
@Table(name="project_experience")
public class ProjectExperience implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`experience_id`")
    @ApiModelProperty(value = "项目经验ID")
    private Long experienceId;

    @Column(name = "`project_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @Column(name = "`name`")
    @ApiModelProperty(value = "项目名称")
    private String name;

    @Column(name = "`start_time`")
    @ApiModelProperty(value = "开始日期")
    private Timestamp startTime;

    @Column(name = "`work_unit`")
    @ApiModelProperty(value = "执行单位")
    private String workUnit;

    @Column(name = "`status`")
    @ApiModelProperty(value = "项目状态：0、执行中；1、已结项")
    private String status;

    @Column(name = "`files`")
    @ApiModelProperty(value = "项目佐证")
    private String files;

    public void copy(ProjectExperience source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
