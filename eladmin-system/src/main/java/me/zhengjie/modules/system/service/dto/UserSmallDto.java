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
package me.zhengjie.modules.system.service.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseDTO;
import me.zhengjie.modules.registration.domain.CompetentOrganization;
import me.zhengjie.modules.registration.domain.Expert;
import me.zhengjie.modules.registration.domain.SocialOrganization;
import me.zhengjie.modules.registration.service.dto.CompetentOrganizationDTO;
import me.zhengjie.modules.registration.service.dto.ExpertDTO;
import me.zhengjie.modules.registration.service.dto.SocialOrganizationDTO;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Getter
@Setter
public class UserSmallDto extends BaseDTO implements Serializable {

    private Long id;

    private String username;

    private String nickName;

    private String gender;

    private String avatarName;

    private String avatarPath;

    private Boolean enabled;

    private Integer userType;

    private CompetentOrganizationDTO competentOrganization;

    private SocialOrganizationDTO socialOrganization;

    private ExpertDTO expert;

}
