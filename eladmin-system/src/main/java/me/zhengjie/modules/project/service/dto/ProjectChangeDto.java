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
public class ProjectChangeDto implements Serializable {

    /** 变更ID */
    private Long changeId;

    /** 项目ID */
    private Long projectId;

    /** 变更类型：项目内容变更、预算变更 */
    private String changeType;

    /** 变更时间 */
    private Timestamp changeTime;

    /** 变更内容 */
    private String changeContent;

    /** 变更文件 */
    private String changeFiles;
}
