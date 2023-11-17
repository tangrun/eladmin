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

import me.zhengjie.modules.project.domain.ProjectApplicationPlan;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectApplicationPlanRepository;
import me.zhengjie.modules.project.service.ProjectApplicationPlanService;
import me.zhengjie.modules.project.service.dto.ProjectApplicationPlanDto;
import me.zhengjie.modules.project.service.dto.ProjectApplicationPlanQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectApplicationPlanMapper;
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
public class ProjectApplicationPlanServiceImpl implements ProjectApplicationPlanService {

    private final ProjectApplicationPlanRepository projectApplicationPlanRepository;
    private final ProjectApplicationPlanMapper projectApplicationPlanMapper;

    @Override
    public PageResult<ProjectApplicationPlanDto> queryAll(ProjectApplicationPlanQueryCriteria criteria, Pageable pageable){
        Page<ProjectApplicationPlan> page = projectApplicationPlanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectApplicationPlanMapper::toDto));
    }

    @Override
    public List<ProjectApplicationPlanDto> queryAll(ProjectApplicationPlanQueryCriteria criteria){
        return projectApplicationPlanMapper.toDto(projectApplicationPlanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectApplicationPlanDto findById(Long planId) {
        ProjectApplicationPlan projectApplicationPlan = projectApplicationPlanRepository.findById(planId).orElseGet(ProjectApplicationPlan::new);
        ValidationUtil.isNull(projectApplicationPlan.getPlanId(),"ProjectApplicationPlan","planId",planId);
        return projectApplicationPlanMapper.toDto(projectApplicationPlan);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectApplicationPlan resources) {
        projectApplicationPlanRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectApplicationPlan resources) {
        ProjectApplicationPlan projectApplicationPlan = projectApplicationPlanRepository.findById(resources.getPlanId()).orElseGet(ProjectApplicationPlan::new);
        ValidationUtil.isNull( projectApplicationPlan.getPlanId(),"ProjectApplicationPlan","id",resources.getPlanId());
        projectApplicationPlan.copy(resources);
        projectApplicationPlanRepository.save(projectApplicationPlan);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long planId : ids) {
            projectApplicationPlanRepository.deleteById(planId);
        }
    }

    @Override
    public void download(List<ProjectApplicationPlanDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectApplicationPlanDto projectApplicationPlan : all) {
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