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
@Table(name="project_active")
public class ProjectActive implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`active_id`")
    @ApiModelProperty(value = "活动ID")
    private Long activeId;

    @Column(name = "`project_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @Column(name = "`plan_id`")
    @ApiModelProperty(value = "项目目标ID")
    private Long planId;

    @Column(name = "`name`")
    @ApiModelProperty(value = "活动主题")
    private String name;

    @Column(name = "`content`")
    @ApiModelProperty(value = "活动内容")
    private String content;

    @Column(name = "`active_time`")
    @ApiModelProperty(value = "活动时间")
    private Timestamp activeTime;

    @Column(name = "`venue`")
    @ApiModelProperty(value = "活动地点")
    private String venue;

    @Column(name = "`num`")
    @ApiModelProperty(value = "参与活动人数")
    private Integer num;

    @Column(name = "`effect`")
    @ApiModelProperty(value = "活动成效")
    private String effect;

    @Column(name = "`fee`")
    @ApiModelProperty(value = "活动费用")
    private BigDecimal fee;

    @Column(name = "`ischecked`")
    @ApiModelProperty(value = "是否验收")
    private Integer ischecked;

    @Column(name = "`create_by`")
    @ApiModelProperty(value = "创建人")
    private String createBy;

    @Column(name = "`create_time`")
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    public void copy(ProjectActive source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
