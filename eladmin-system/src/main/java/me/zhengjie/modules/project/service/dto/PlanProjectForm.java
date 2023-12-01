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
import me.zhengjie.base.BaseEntity;
import me.zhengjie.commons.pojo.IdObject;
import me.zhengjie.modules.system.service.dto.UserIdBean;
import me.zhengjie.service.dto.LocalStorageIdBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
* @website https://eladmin.vip
* @description /
* @author hb
* @date 2023-11-15
**/
@Data
public class PlanProjectForm implements Serializable {


    @ApiModelProperty("ID" )
    @Null(groups = BaseEntity.Create.class)
    @NotNull(groups = BaseEntity.Update.class)
    private Long id;

    @ApiModelProperty(value = "上级项目",notes = "拆分项目时必须传上级项目id")
    private IdObject parent;

    @ApiModelProperty("项目名称")
    private String planName;

    @ApiModelProperty("项目类别")
    private String category;

    @ApiModelProperty("资金来源")
    private String sourceFunds;

    @ApiModelProperty("项目概述")
    private String overview;

    @ApiModelProperty("项目备注")
    private String remark;

    @ApiModelProperty("项目公告")
    private String notice;

    @ApiModelProperty("项目负责人")
    private UserIdBean leader;

    @ApiModelProperty("项目书")
    private List<LocalStorageIdBean> proposals;

    @ApiModelProperty("项目合同")
    private List<LocalStorageIdBean> contracts;

    @ApiModelProperty("启动时间")
    private Timestamp startTime;

    @ApiModelProperty("结束时间")
    private Timestamp endTime;

    @ApiModelProperty("联系人")
    private String contacts;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("项目投稿邮箱")
    private String email;

    @ApiModelProperty("联系地址")
    private String address;

    @ApiModelProperty("投稿截止时间")
    private Timestamp deadline;

    @ApiModelProperty("省")
    private String province;
    @ApiModelProperty("市")
    private String city;
    @ApiModelProperty("区/县")
    private String county;
    @ApiModelProperty("街道")
    private String street;
    @ApiModelProperty("社区")
    private String community;

}
