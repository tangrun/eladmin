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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.annotation.Query;

import javax.persistence.Column;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-15
**/
@Data
public class ProjectApplicationQueryCriteria {

    @ApiModelProperty("ID")
    @Query(propName = "id")
    private Long id;

    @Query(blurry = "projectName,projectCode")
    private Long blurry;

    @ApiModelProperty(value = "项目类别")
    @Query
    private String category;

    @ApiModelProperty(value = "项目类型")
    @Query
    private String projectType;

    @ApiModelProperty(value = "项目状态")
    @Query
    private String projectStatus;

    @ApiModelProperty(value = "审批状态")
    @Query
    private String approvalStatus;

    @ApiModelProperty(value = "省")
    @Query
    private String province;


    @ApiModelProperty(value = "市")
    @Query
    private String city;


    @ApiModelProperty(value = "区")
    @Query
    private String county;

    @ApiModelProperty(value = "街道")
    @Query
    private String street;

    @ApiModelProperty(value = "社区")
    @Query
    private String community;
}
