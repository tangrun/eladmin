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
public class ProjectSuperviseDto implements Serializable {

    /** 督导ID */
    private Long superviseId;

    /** 项目ID */
    private Long projectId;

    /** 督导内容 */
    private String content;

    /** 督导主题 */
    private String theme;

    /** 督导评分 */
    private BigDecimal rating;

    /** 督导建议 */
    private String advise;

    /** 图片 */
    private String files;

    /** 督导老师 */
    private String createBy;

    /** 督导时间 */
    private Timestamp createTime;
}