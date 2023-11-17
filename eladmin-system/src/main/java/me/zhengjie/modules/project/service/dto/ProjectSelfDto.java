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
* @date 2023-11-14
**/
@Data
public class ProjectSelfDto implements Serializable {

    /** 自评ID */
    private Long selfId;

    /** 项目ID */
    private Long projectId;

    /** 评估期 */
    private String interim;

    /** 项目执行总结 */
    private String summary;

    /** 下一步工作安排记建议 */
    private String nextRule;

    /** 项目创新性 */
    private String innovation;

    /** 项目可持续性 */
    private String continuity;

    /** 项目特色、高点 */
    private String characteristic;

    /** 项目反思 */
    private String reflect;

    /** 创建者 */
    private String createBy;

    /** 创建日期 */
    private Timestamp createTime;
}