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
public class ProjectExperienceDto implements Serializable {

    /** 项目经验ID */
    private Long experienceId;

    /** 项目ID */
    private Long projectId;

    /** 项目名称 */
    private String name;

    /** 开始日期 */
    private Timestamp startTime;

    /** 执行单位 */
    private String workUnit;

    /** 项目状态：0、执行中；1、已结项 */
    private String status;

    /** 项目佐证 */
    private String files;
}