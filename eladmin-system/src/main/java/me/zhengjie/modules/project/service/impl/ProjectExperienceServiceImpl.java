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

import me.zhengjie.modules.project.domain.ProjectExperience;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectExperienceRepository;
import me.zhengjie.modules.project.service.ProjectExperienceService;
import me.zhengjie.modules.project.service.dto.ProjectExperienceDto;
import me.zhengjie.modules.project.service.dto.ProjectExperienceQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectExperienceMapper;
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
* @date 2023-11-14
**/
@Service
@RequiredArgsConstructor
public class ProjectExperienceServiceImpl implements ProjectExperienceService {

    private final ProjectExperienceRepository projectExperienceRepository;
    private final ProjectExperienceMapper projectExperienceMapper;

    @Override
    public PageResult<ProjectExperienceDto> queryAll(ProjectExperienceQueryCriteria criteria, Pageable pageable){
        Page<ProjectExperience> page = projectExperienceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectExperienceMapper::toDto));
    }

    @Override
    public List<ProjectExperienceDto> queryAll(ProjectExperienceQueryCriteria criteria){
        return projectExperienceMapper.toDto(projectExperienceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectExperienceDto findById(Long experienceId) {
        ProjectExperience projectExperience = projectExperienceRepository.findById(experienceId).orElseGet(ProjectExperience::new);
        ValidationUtil.isNull(projectExperience.getExperienceId(),"ProjectExperience","experienceId",experienceId);
        return projectExperienceMapper.toDto(projectExperience);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectExperience resources) {
        projectExperienceRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectExperience resources) {
        ProjectExperience projectExperience = projectExperienceRepository.findById(resources.getExperienceId()).orElseGet(ProjectExperience::new);
        ValidationUtil.isNull( projectExperience.getExperienceId(),"ProjectExperience","id",resources.getExperienceId());
        projectExperience.copy(resources);
        projectExperienceRepository.save(projectExperience);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long experienceId : ids) {
            projectExperienceRepository.deleteById(experienceId);
        }
    }

    @Override
    public void download(List<ProjectExperienceDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectExperienceDto projectExperience : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("项目ID", projectExperience.getProjectId());
            map.put("项目名称", projectExperience.getName());
            map.put("开始日期", projectExperience.getStartTime());
            map.put("执行单位", projectExperience.getWorkUnit());
            map.put("项目状态：0、执行中；1、已结项", projectExperience.getStatus());
            map.put("项目佐证", projectExperience.getFiles());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
