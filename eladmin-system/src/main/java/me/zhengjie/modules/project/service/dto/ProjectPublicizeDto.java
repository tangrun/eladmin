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
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

/**
* @website https://eladmin.vip
* @description /
* @author hb
* @date 2023-11-15
**/
@Data
public class ProjectPublicizeDto implements Serializable {

    /** 宣传ID */
    /** 防止精度丢失 */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long publicizeId;

    /** 项目ID */
    private Long projectId;

    /** 媒体名称 */
    private String mediaName;

    /** 宣传主题 */
    private String theme;

    /** 媒体级别 */
    private String mediaLevel;

    /** 发布时间 */
    private Timestamp publishTime;

    /** 网站链接 */
    private String url;

    /** 图片 */
    private String files;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Timestamp createTime;
}
