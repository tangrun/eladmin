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
package me.zhengjie.modules.project.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.project.domain.PlanProject;
import me.zhengjie.modules.project.service.dto.PlanProjectDto;
import me.zhengjie.modules.system.service.mapstruct.UserSmallMapper;
import me.zhengjie.service.mapstruct.LocalStorageMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author hb
 * @website https://eladmin.vip
 * @date 2023-11-15
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        LocalStorageMapper.class,
        UserSmallMapper.class
})
public interface PlanProjectMapper extends BaseMapper<PlanProjectDto, PlanProject> {

}
