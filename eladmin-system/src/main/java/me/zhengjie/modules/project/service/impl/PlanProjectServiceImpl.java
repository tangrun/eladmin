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
import cn.hutool.core.util.StrUtil;
import me.zhengjie.modules.project.domain.PlanProject;
import me.zhengjie.modules.project.enums.PlanStatus;
import me.zhengjie.modules.project.service.dto.PlanProjectDto;
import me.zhengjie.modules.project.service.dto.PlanProjectForm;
import me.zhengjie.modules.project.service.dto.PlanProjectPublishReviewForm;
import me.zhengjie.service.LocalStorageService;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.PlanProjectRepository;
import me.zhengjie.modules.project.service.PlanProjectService;
import me.zhengjie.modules.project.service.dto.PlanProjectQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.PlanProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;

import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import me.zhengjie.utils.PageResult;
import org.springframework.util.Assert;

/**
 * @author hb
 * @website https://eladmin.vip
 * @description 服务实现
 * @date 2023-11-15
 **/
@Service
@RequiredArgsConstructor
public class PlanProjectServiceImpl implements PlanProjectService {

    private final PlanProjectRepository planProjectRepository;
    private final PlanProjectMapper planProjectMapper;
    private final LocalStorageService localStorageService;

    @Override
    public PageResult<PlanProjectDto> queryAll(PlanProjectQueryCriteria criteria, Pageable pageable) {
        Page<PlanProject> page = planProjectRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(planProjectMapper::toDto));
    }

    @Override
    public List<PlanProjectDto> queryAll(PlanProjectQueryCriteria criteria) {
        return planProjectMapper.toDto(planProjectRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public PlanProjectDto findById(Long planId) {
        PlanProject planProject = planProjectRepository.findById(planId).orElseGet(PlanProject::new);
        ValidationUtil.isNull(planProject.getId(), "ProjectPlan", "planId", planId);
        return planProjectMapper.toDto(planProject);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDraft(PlanProjectForm resources) {
        PlanProject planProject = null;
        if (resources.getId() == null) {
            planProject = new PlanProject();
        } else {
            planProject = planProjectRepository.findById(resources.getId()).orElseGet(PlanProject::new);
            ValidationUtil.isNull(planProject.getId(), "ProjectPlan", "planId", resources.getId());
            Assert.isTrue(StrUtil.containsAny(planProject.getPlanStatus(), PlanStatus.DRAFT, PlanStatus.PUBLISH_FAIL), "当前状态不允许修改");
        }
        BeanUtil.copyProperties(resources, planProject);
        planProject.setPlanStatus(PlanStatus.DRAFT);
        planProjectRepository.save(planProject);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(PlanProjectForm resources) {
        PlanProject planProject = planProjectRepository.findById(resources.getId()).orElseGet(PlanProject::new);
        ValidationUtil.isNull(planProject.getId(), "ProjectPlan", "planId", resources.getId());
        BeanUtil.copyProperties(resources, planProject);
        planProjectRepository.save(planProject);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void review(PlanProjectPublishReviewForm resources) {
        PlanProject planProject = planProjectRepository.findById(resources.getId()).orElseGet(PlanProject::new);
        ValidationUtil.isNull(planProject.getId(), "ProjectPlan", "planId", resources.getId());
        Assert.isTrue(StrUtil.containsAny(planProject.getPlanStatus(), PlanStatus.PUBLISH_REVIEW), "当前状态不可审核");
        if (resources.getPass()) {
            planProjectRepository.modifyPlanStatus(resources.getId(), PlanStatus.PUBLISHED);
        } else {
            planProjectRepository.modifyPlanStatus(resources.getId(), PlanStatus.PUBLISH_FAIL);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publish(PlanProjectForm resources) {
        PlanProject planProject = null;
        if (resources.getId() == null) {
            planProject = new PlanProject();
        } else {
            planProject = planProjectRepository.findById(resources.getId()).orElseGet(PlanProject::new);
            ValidationUtil.isNull(planProject.getId(), "ProjectPlan", "planId", resources.getId());
            Assert.isTrue(StrUtil.containsAny(planProject.getPlanStatus(), PlanStatus.DRAFT, PlanStatus.PUBLISH_FAIL), "当前状态不允许修改");
        }
        BeanUtil.copyProperties(resources, planProject);
        planProject.setPlanStatus(PlanStatus.PUBLISH_REVIEW);
        planProjectRepository.save(planProject);
    }


    @Override
    public void deleteAll(Long[] ids) {
        for (Long planId : ids) {
            planProjectRepository.deleteById(planId);
        }
    }

    @Override
    public void download(List<PlanProjectDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PlanProjectDto projectPlan : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("上级项目", projectPlan.getParent());
            map.put("项目状态", projectPlan.getPlanStatus());
            map.put("项目名称", projectPlan.getPlanName());
            map.put("项目类别", projectPlan.getCategory());
            map.put("资金来源", projectPlan.getSourceFunds());
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
