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
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author hb
* @date 2023-11-15
**/
@Data
public class ActiveDto implements Serializable {

    /** 活动ID */
    private Long activeId;

    /** 项目ID */
    private Long projectId;

    /** 项目目标ID */
    private Long planId;

    /** 活动主题 */
    private String name;

    /** 活动内容 */
    private String content;

    /** 活动时间 */
    private Timestamp activeTime;

    /** 活动地点 */
    private String venue;

    /** 参与活动人数 */
    private Integer num;

    /** 活动成效 */
    private String effect;

    /** 活动费用 */
    private BigDecimal fee;

    /** 是否验收 */
    private Integer ischecked;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Timestamp createTime;
}