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

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.annotation.Query;

import java.util.List;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-15
**/
@Data
public class PlanProjectQueryCriteria {

    @Query(blurry = "planName")
    @ApiModelProperty("模糊查询 项目名")
    private String blurry;
    @Query()
    @ApiModelProperty("项目类别")
    private String category;
    @Query()
    @ApiModelProperty("省")
    private String province;
    @Query()
    @ApiModelProperty("市")
    private String city;
    @Query()
    @ApiModelProperty("区县")
    private String county;
    @Query()
    @ApiModelProperty("街道")
    private String street;

    @Query(type = Query.Type.IN)
    @ApiModelProperty(value = "街道",hidden = true)
    @JSONField(deserialize = false)
    private List<String> planStatus;

}
