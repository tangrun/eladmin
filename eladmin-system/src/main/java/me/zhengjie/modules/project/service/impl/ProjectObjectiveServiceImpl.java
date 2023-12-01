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

import me.zhengjie.modules.project.domain.ProjectObjective;
import me.zhengjie.modules.project.service.dto.ProjectObjectiveDto;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectObjectiveRepository;
import me.zhengjie.modules.project.service.ProjectObjectiveService;
import me.zhengjie.modules.project.service.dto.ProjectObjectiveQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectObjectiveMapper;
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
public class ProjectObjectiveServiceImpl implements ProjectObjectiveService {

    private final ProjectObjectiveRepository projectObjectiveRepository;
    private final ProjectObjectiveMapper projectObjectiveMapper;

    @Override
    public PageResult<ProjectObjectiveDto> queryAll(ProjectObjectiveQueryCriteria criteria, Pageable pageable){
        Page<ProjectObjective> page = projectObjectiveRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectObjectiveMapper::toDto));
    }

    @Override
    public List<ProjectObjectiveDto> queryAll(ProjectObjectiveQueryCriteria criteria){
        return projectObjectiveMapper.toDto(projectObjectiveRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectObjectiveDto findById(Long planId) {
        ProjectObjective projectObjective = projectObjectiveRepository.findById(planId).orElseGet(ProjectObjective::new);
        ValidationUtil.isNull(projectObjective.getPlanId(),"ProjectApplicationPlan","planId",planId);
        return projectObjectiveMapper.toDto(projectObjective);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectObjective resources) {
        projectObjectiveRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectObjective resources) {
        ProjectObjective projectObjective = projectObjectiveRepository.findById(resources.getPlanId()).orElseGet(ProjectObjective::new);
        ValidationUtil.isNull( projectObjective.getPlanId(),"ProjectApplicationPlan","id",resources.getPlanId());
        projectObjective.copy(resources);
        projectObjectiveRepository.save(projectObjective);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long planId : ids) {
            projectObjectiveRepository.deleteById(planId);
        }
    }

    @Override
    public void download(List<ProjectObjectiveDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectObjectiveDto projectApplicationPlan : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("项目ID", projectApplicationPlan.getProjectId());
            map.put("目标名称", projectApplicationPlan.getName());
            map.put("目标阶段：投入、产出、影响、成效", projectApplicationPlan.getTargetType());
            map.put("目标值", projectApplicationPlan.getTargetNum());
            map.put("权重", projectApplicationPlan.getWeight());
            map.put("开始时间", projectApplicationPlan.getStartTime());
            map.put("结束时间", projectApplicationPlan.getEndTime());
            map.put("备注", projectApplicationPlan.getRemark());
            map.put("录入人", projectApplicationPlan.getCreateBy());
            map.put("录入时间", projectApplicationPlan.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
