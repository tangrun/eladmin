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
package me.zhengjie.modules.reg2.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author rain
* @date 2023-11-20
**/
@Data
public class SysUserCompetentDto implements Serializable {

    /** ID */
    private Long id;

    /** 行政等级 */
    private Integer administrativeLevel;

    /** 省 */
    private String province;

    /** 市 */
    private String city;

    /** 县、区 */
    private String county;

    /** 街道 */
    private String street;

    /** 社区 */
    private String community;

    /** 机构名称 */
    private String name;

    /** 机构简介 */
    private String description;

    /** 机构电话 */
    private String phone;

    /** 机构邮箱 */
    private String email;

    /** 机构传真 */
    private String fax;

    /** 机构地址 */
    private String address;

    /** 管理员名字 */
    private String managerName;

    /** 管理员电话 */
    private String managerPhone;

    /** 管理员邮箱 */
    private String managerEmail;

    /** 创建者 */
    private String createBy;

    /** 更新者 */
    private String updateBy;

    /** 创建日期 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;
}