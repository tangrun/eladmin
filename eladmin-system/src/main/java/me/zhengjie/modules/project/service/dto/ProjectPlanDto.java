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

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author hb
* @date 2023-11-15
**/
@Data
public class ProjectPlanDto implements Serializable {

    /** 计划ID */
    private Long planId;

    /** 上级项目 */
    private Long parentId;

    /** 项目状态：0、储备；1、立项 */
    private String planStatus;

    /** 项目名称 */
    private String planName;

    /** 项目类别 */
    private String categoryId;

    /** 资金来源：专项资金、配套资金、支持资金 */
    private String source;

    /** 项目概述 */
    private String overview;

    /** 项目备注 */
    private String remark;

    /** 项目公告 */
    private String notice;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Timestamp createTime;

    /** 项目负责人 */
    private Integer leaderId;

    /** 项目书 */
    private String proposal;

    /** 项目合同 */
    private String contract;

    /** 启动时间 */
    private Timestamp startTime;

    /** 结束时间 */
    private Timestamp endTime;

    /** 联系人 */
    private String contacts;

    /** 联系电话 */
    private String phone;

    /** 项目投稿邮箱 */
    private String email;

    /** 联系地址 */
    private String address;

    /** 投稿截止时间 */
    private Timestamp deadline;

    /** 落地地区 */
    private String landingArea;
}