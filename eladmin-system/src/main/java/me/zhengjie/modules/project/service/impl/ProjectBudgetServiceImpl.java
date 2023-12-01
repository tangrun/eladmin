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

import me.zhengjie.modules.project.domain.ProjectBudget;
import me.zhengjie.modules.project.service.dto.ProjectBudgetDto;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectBudgetRepository;
import me.zhengjie.modules.project.service.ProjectBudgetService;
import me.zhengjie.modules.project.service.dto.ProjectBudgetQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectBudgetMapper;
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
public class ProjectBudgetServiceImpl implements ProjectBudgetService {

    private final ProjectBudgetRepository projectBudgetRepository;
    private final ProjectBudgetMapper projectBudgetMapper;

    @Override
    public PageResult<ProjectBudgetDto> queryAll(ProjectBudgetQueryCriteria criteria, Pageable pageable){
        Page<ProjectBudget> page = projectBudgetRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectBudgetMapper::toDto));
    }

    @Override
    public List<ProjectBudgetDto> queryAll(ProjectBudgetQueryCriteria criteria){
        return projectBudgetMapper.toDto(projectBudgetRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectBudgetDto findById(Long budgetId) {
        ProjectBudget projectBudget = projectBudgetRepository.findById(budgetId).orElseGet(ProjectBudget::new);
        ValidationUtil.isNull(projectBudget.getBudgetId(),"ApplicationBudget","budgetId",budgetId);
        return projectBudgetMapper.toDto(projectBudget);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectBudget resources) {
        projectBudgetRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectBudget resources) {
        ProjectBudget projectBudget = projectBudgetRepository.findById(resources.getBudgetId()).orElseGet(ProjectBudget::new);
        ValidationUtil.isNull( projectBudget.getBudgetId(),"ApplicationBudget","id",resources.getBudgetId());
        projectBudget.copy(resources);
        projectBudgetRepository.save(projectBudget);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long budgetId : ids) {
            projectBudgetRepository.deleteById(budgetId);
        }
    }

    @Override
    public void download(List<ProjectBudgetDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectBudgetDto applicationBudget : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("项目ID", applicationBudget.getProjectId());
            map.put("预算名称", applicationBudget.getName());
            map.put("预算值", applicationBudget.getBudgetValue());
            map.put("说明", applicationBudget.getRemark());
            map.put("录入人", applicationBudget.getCreateBy());
            map.put("录入时间", applicationBudget.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
