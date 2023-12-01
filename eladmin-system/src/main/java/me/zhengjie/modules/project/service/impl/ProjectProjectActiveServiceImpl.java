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
package me.zhengjie.modules.project.service.impl;

import me.zhengjie.modules.project.domain.ProjectActive;
import me.zhengjie.modules.project.service.dto.ProjectActiveDto;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectActiveRepository;
import me.zhengjie.modules.project.service.ProjectActiveService;
import me.zhengjie.modules.project.service.dto.ProjectActiveQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectActiveMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import me.zhengjie.utils.PageResult;

/**
* @website https://eladmin.vip
* @description 服务实现
* @author hb
* @date 2023-11-15
**/
@Service
@RequiredArgsConstructor
public class ProjectProjectActiveServiceImpl implements ProjectActiveService {

    private final ProjectActiveRepository projectActiveRepository;
    private final ProjectActiveMapper projectActiveMapper;

    @Override
    public PageResult<ProjectActiveDto> queryAll(ProjectActiveQueryCriteria criteria, Pageable pageable){
        Page<ProjectActive> page = projectActiveRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectActiveMapper::toDto));
    }

    @Override
    public List<ProjectActiveDto> queryAll(ProjectActiveQueryCriteria criteria){
        return projectActiveMapper.toDto(projectActiveRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectActiveDto findById(Long activeId) {
        ProjectActive projectActive = projectActiveRepository.findById(activeId).orElseGet(ProjectActive::new);
        ValidationUtil.isNull(projectActive.getActiveId(),"Active","activeId",activeId);
        return projectActiveMapper.toDto(projectActive);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectActive resources) {
        projectActiveRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectActive resources) {
        ProjectActive projectActive = projectActiveRepository.findById(resources.getActiveId()).orElseGet(ProjectActive::new);
        ValidationUtil.isNull( projectActive.getActiveId(),"Active","id",resources.getActiveId());
        projectActive.copy(resources);
        projectActiveRepository.save(projectActive);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long activeId : ids) {
            projectActiveRepository.deleteById(activeId);
        }
    }

    @Override
    public void download(List<ProjectActiveDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectActiveDto active : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("项目ID", active.getProjectId());
            map.put("项目目标ID", active.getPlanId());
            map.put("活动主题", active.getName());
            map.put("活动内容", active.getContent());
            map.put("活动时间", active.getActiveTime());
            map.put("活动地点", active.getVenue());
            map.put("参与活动人数", active.getNum());
            map.put("活动成效", active.getEffect());
            map.put("活动费用", active.getFee());
            map.put("是否验收", active.getIschecked());
            map.put("创建人", active.getCreateBy());
            map.put("创建时间", active.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
