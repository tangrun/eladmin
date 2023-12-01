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
* @date 2023-11-15
**/
@Entity
@Data
@Table(name="project_application_team")
public class ProjectTeam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`member_id`")
    @ApiModelProperty(value = "ID")
    private Long memberId;

    @Column(name = "`name`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "姓名")
    private String name;

    @Column(name = "`sex`")
    @ApiModelProperty(value = "性别")
    private Integer sex;

    @Column(name = "`age`")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @Column(name = "`title`")
    @ApiModelProperty(value = "职称")
    private String title;

    @Column(name = "`duties`")
    @ApiModelProperty(value = "项目职务")
    private String duties;

    @Column(name = "`mobile`")
    @ApiModelProperty(value = "电话")
    private String mobile;

    @Column(name = "`email`")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Column(name = "`entry_time`")
    @ApiModelProperty(value = "任职开始时间")
    private Timestamp entryTime;

    @Column(name = "`education`")
    @ApiModelProperty(value = "学历")
    private String education;

    @Column(name = "`speciality`")
    @ApiModelProperty(value = "专业")
    private String speciality;

    @Column(name = "`remark`")
    @ApiModelProperty(value = "备注")
    private String remark;

    @Column(name = "`create_by`")
    @ApiModelProperty(value = "录入人")
    private String createBy;

    @Column(name = "`create_time`")
    @ApiModelProperty(value = "录入时间")
    private Timestamp createTime;

    public void copy(ProjectTeam source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
