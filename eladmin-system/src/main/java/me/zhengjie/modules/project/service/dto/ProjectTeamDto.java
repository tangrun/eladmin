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
public class ProjectTeamDto implements Serializable {

    /** ID */
    private Long memberId;

    /** 姓名 */
    private String name;

    /** 性别 */
    private Integer sex;

    /** 年龄 */
    private Integer age;

    /** 职称 */
    private String title;

    /** 项目职务 */
    private String duties;

    /** 电话 */
    private String mobile;

    /** 邮箱 */
    private String email;

    /** 任职开始时间 */
    private Timestamp entryTime;

    /** 学历 */
    private String education;

    /** 专业 */
    private String speciality;

    /** 备注 */
    private String remark;

    /** 录入人 */
    private String createBy;

    /** 录入时间 */
    private Timestamp createTime;
}
