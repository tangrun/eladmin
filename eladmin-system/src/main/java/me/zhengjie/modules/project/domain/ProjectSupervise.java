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
@Table(name="project_supervise")
public class ProjectSupervise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`supervise_id`")
    @ApiModelProperty(value = "督导ID")
    private Long superviseId;

    @Column(name = "`project_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @Column(name = "`supervise_type`")
    @ApiModelProperty(value = "督导方式")
    private String superviseType;

    @Column(name = "`content`")
    @ApiModelProperty(value = "督导内容")
    private String content;

    @Column(name = "`theme`")
    @ApiModelProperty(value = "督导主题")
    private String theme;

    @Column(name = "`rating`")
    @ApiModelProperty(value = "督导评分")
    private BigDecimal rating;

    @Column(name = "`advise`")
    @ApiModelProperty(value = "督导建议")
    private String advise;

    @Column(name = "`files`")
    @ApiModelProperty(value = "图片")
    private String files;

    @Column(name = "`create_by`")
    @ApiModelProperty(value = "督导老师")
    private String createBy;

    @Column(name = "`create_time`")
    @ApiModelProperty(value = "督导时间")
    private Timestamp createTime;

    @Column(name = "`address`")
    @ApiModelProperty(value = "督导地点")
    private String address;

    @Column(name = "`supervised_person`")
    @ApiModelProperty(value = "被督导人")
    private String supervisedPerson;

    public void copy(ProjectSupervise source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
