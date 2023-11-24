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

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import me.zhengjie.domain.LocalStorage;
import me.zhengjie.modules.project.domain.ProjectPlan;
import me.zhengjie.modules.project.service.dto.ProjectPlanCreateForm;
import me.zhengjie.service.LocalStorageService;
import me.zhengjie.service.dto.LocalStorageDto;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectPlanRepository;
import me.zhengjie.modules.project.service.ProjectPlanService;
import me.zhengjie.modules.project.service.dto.ProjectPlanDto;
import me.zhengjie.modules.project.service.dto.ProjectPlanQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectPlanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;

import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import me.zhengjie.utils.PageResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hb
 * @website https://eladmin.vip
 * @description 服务实现
 * @date 2023-11-15
 **/
@Service
@RequiredArgsConstructor
public class ProjectPlanServiceImpl implements ProjectPlanService {

    private final ProjectPlanRepository projectPlanRepository;
    private final ProjectPlanMapper projectPlanMapper;
    private final LocalStorageService localStorageService;

    @Override
    public PageResult<ProjectPlanDto> queryAll(ProjectPlanQueryCriteria criteria, Pageable pageable) {
        Page<ProjectPlan> page = projectPlanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(projectPlanMapper::toDto));
    }

    @Override
    public List<ProjectPlanDto> queryAll(ProjectPlanQueryCriteria criteria) {
        return projectPlanMapper.toDto(projectPlanRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectPlanDto findById(Long planId) {
        ProjectPlan projectPlan = projectPlanRepository.findById(planId).orElseGet(ProjectPlan::new);
        ValidationUtil.isNull(projectPlan.getPlanId(), "ProjectPlan", "planId", planId);
        return projectPlanMapper.toDto(projectPlan);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectPlanCreateForm resources) {
        ProjectPlan plan = new ProjectPlan();
        BeanUtil.copyProperties(resources, plan, CopyOptions.create().ignoreError());
        if (CollUtil.isNotEmpty(resources.getProposals())) {
            plan.setProposals(
                    resources.getProposals()
                            .stream()
                            .map(aLong -> {
                                LocalStorage localStorage = new LocalStorage();
                                localStorage.setId(aLong);
                                return localStorage;
                            })
                            .collect(Collectors.toSet())
            );
        }
        if (CollUtil.isNotEmpty(resources.getContracts())) {
            plan.setContracts(
                    resources.getContracts()
                            .stream()
                            .map(aLong -> {
                                LocalStorage localStorage = new LocalStorage();
                                localStorage.setId(aLong);
                                return localStorage;
                            })
                            .collect(Collectors.toSet())
            );
        }
        projectPlanRepository.save(plan);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectPlan resources) {
        ProjectPlan projectPlan = projectPlanRepository.findById(resources.getPlanId()).orElseGet(ProjectPlan::new);
        ValidationUtil.isNull(projectPlan.getPlanId(), "ProjectPlan", "id", resources.getPlanId());
        projectPlan.copy(resources);
        projectPlanRepository.save(projectPlan);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long planId : ids) {
            projectPlanRepository.deleteById(planId);
        }
    }

    @Override
    public void download(List<ProjectPlanDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectPlanDto projectPlan : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("上级项目", projectPlan.getParent());
            map.put("项目状态", projectPlan.getPlanStatus());
            map.put("项目名称", projectPlan.getPlanName());
            map.put("项目类别", projectPlan.getCategory());
            map.put("资金来源", projectPlan.getSourceFund());
            map.put("项目概述", projectPlan.getOverview());
            map.put("项目备注", projectPlan.getRemark());
            map.put("项目公告", projectPlan.getNotice());
            map.put("创建人", projectPlan.getCreateBy());
            map.put("创建时间", projectPlan.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
