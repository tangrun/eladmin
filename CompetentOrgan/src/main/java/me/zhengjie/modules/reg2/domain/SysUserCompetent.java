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
package me.zhengjie.modules.reg2.domain;

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
* @author rain
* @date 2023-11-20
**/
@Entity
@Data
@Table(name="sys_user_competent")
public class SysUserCompetent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    @ApiModelProperty(value = "ID")
    private Long id;

    @Column(name = "`administrative_level`")
    @ApiModelProperty(value = "行政等级")
    private Integer administrativeLevel;

    @Column(name = "`province`")
    @ApiModelProperty(value = "省")
    private String province;

    @Column(name = "`city`")
    @ApiModelProperty(value = "市")
    private String city;

    @Column(name = "`county`")
    @ApiModelProperty(value = "县、区")
    private String county;

    @Column(name = "`street`")
    @ApiModelProperty(value = "街道")
    private String street;

    @Column(name = "`community`")
    @ApiModelProperty(value = "社区")
    private String community;

    @Column(name = "`name`")
    @ApiModelProperty(value = "机构名称")
    private String name;

    @Column(name = "`description`")
    @ApiModelProperty(value = "机构简介")
    private String description;

    @Column(name = "`phone`")
    @ApiModelProperty(value = "机构电话")
    private String phone;

    @Column(name = "`email`")
    @ApiModelProperty(value = "机构邮箱")
    private String email;

    @Column(name = "`fax`")
    @ApiModelProperty(value = "机构传真")
    private String fax;

    @Column(name = "`address`")
    @ApiModelProperty(value = "机构地址")
    private String address;

    @Column(name = "`manager_name`")
    @ApiModelProperty(value = "管理员名字")
    private String managerName;

    @Column(name = "`manager_phone`")
    @ApiModelProperty(value = "管理员电话")
    private String managerPhone;

    @Column(name = "`manager_email`")
    @ApiModelProperty(value = "管理员邮箱")
    private String managerEmail;

    @Column(name = "`create_by`")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @Column(name = "`update_by`")
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @Column(name = "`create_time`")
    @ApiModelProperty(value = "创建日期")
    private Timestamp createTime;

    @Column(name = "`update_time`")
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    public void copy(SysUserCompetent source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
