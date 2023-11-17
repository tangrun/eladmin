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
* @date 2023-11-14
**/
@Data
public class ProjectApplicationPlanDto implements Serializable {

    /** ID */
    private Long planId;

    /** 项目ID */
    private Long projectId;

    /** 目标名称 */
    private String name;

    /** 目标阶段：投入、产出、影响、成效 */
    private String targetType;

    /** 目标值 */
    private Integer targetNum;

    /** 权重 */
    private BigDecimal weight;

    /** 开始时间 */
    private Timestamp startTime;

    /** 结束时间 */
    private Timestamp endTime;

    /** 备注 */
    private String remark;

    /** 录入人 */
    private String createBy;

    /** 录入时间 */
    private Timestamp createTime;
}