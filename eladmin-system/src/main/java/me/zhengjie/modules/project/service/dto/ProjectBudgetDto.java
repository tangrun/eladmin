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
public class ProjectBudgetDto implements Serializable {

    /** ID */
    private Long budgetId;

    /** 项目ID */
    private Long projectId;

    /** 预算名称 */
    private String name;

    /** 预算值 */
    private BigDecimal budgetValue;

    /** 说明 */
    private String remark;

    /** 录入人 */
    private String createBy;

    /** 录入时间 */
    private Timestamp createTime;
}
