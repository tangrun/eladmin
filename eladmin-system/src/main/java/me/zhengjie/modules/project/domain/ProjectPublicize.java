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
* @date 2023-11-15
**/
@Entity
@Data
@Table(name="project_publicize")
public class ProjectPublicize implements Serializable {

    @Id
    @Column(name = "`publicize_id`")
    @ApiModelProperty(value = "宣传ID")
    private Long publicizeId;

    @Column(name = "`project_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @Column(name = "`media_name`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "媒体名称")
    private String mediaName;

    @Column(name = "`theme`")
    @ApiModelProperty(value = "宣传主题")
    private String theme;

    @Column(name = "`media_level`")
    @ApiModelProperty(value = "媒体级别")
    private String mediaLevel;

    @Column(name = "`publish_time`")
    @ApiModelProperty(value = "发布时间")
    private Timestamp publishTime;

    @Column(name = "`url`")
    @ApiModelProperty(value = "网站链接")
    private String url;

    @Column(name = "`files`")
    @ApiModelProperty(value = "图片")
    private String files;

    @Column(name = "`create_by`")
    @ApiModelProperty(value = "创建人")
    private String createBy;

    @Column(name = "`create_time`")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    public void copy(ProjectPublicize source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
