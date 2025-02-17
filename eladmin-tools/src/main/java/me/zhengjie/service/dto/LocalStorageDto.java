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
package me.zhengjie.service.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseDTO;
import java.io.Serializable;

/**
* @author Zheng Jie
* @date 2019-09-05
*/
@Getter
@Setter
public class LocalStorageDto extends BaseDTO implements Serializable {

    @ApiModelProperty(name = "上传文件ID")
    private Long id;

    @ApiModelProperty(hidden = true)
    @JSONField(deserialize = false)
    private String realName;

    @ApiModelProperty(hidden = true)
    @JSONField(deserialize = false)
    private String name;

    @ApiModelProperty(hidden = true)
    @JSONField(deserialize = false)
    private String suffix;

    @ApiModelProperty(hidden = true)
    @JSONField(deserialize = false)
    private String fileType;

    @ApiModelProperty(hidden = true)
    @JSONField(deserialize = false)
    private String type;

    @ApiModelProperty(hidden = true)
    @JSONField(deserialize = false)
    private String size;
}
