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
import me.zhengjie.modules.project.domain.ProjectApplication;
import me.zhengjie.modules.project.service.dto.ProjectApplicationForm;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectApplicationRepository;
import me.zhengjie.modules.project.service.ProjectApplicationService;
import me.zhengjie.modules.project.service.dto.ProjectApplicationDto;
import me.zhengjie.modules.project.service.dto.ProjectApplicationQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectApplicationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
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
public class ProjectProjectApplicationServiceImpl implements ProjectApplicationService {

    private final ProjectApplicationRepository projectApplicationRepository;
    private final ProjectApplicationMapper projectApplicationMapper;

    @Override
    public PageResult<ProjectApplicationDto> queryAll(ProjectApplicationQueryCriteria criteria, Pageable pageable){
        Page<ProjectApplication> page = projectApplicationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectApplicationMapper::toDto));
    }

    @Override
    public List<ProjectApplicationDto> queryAll(ProjectApplicationQueryCriteria criteria){
        return projectApplicationMapper.toDto(projectApplicationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectApplicationDto findById(Long projectId) {
        ProjectApplication projectApplication = projectApplicationRepository.findById(projectId).orElseGet(ProjectApplication::new);
        ValidationUtil.isNull(projectApplication.getId(),"Application","projectId",projectId);
        return projectApplicationMapper.toDto(projectApplication);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectApplicationForm resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setId(snowflake.nextId());
        ProjectApplication projectApplication = BeanUtil.copyProperties(resources, ProjectApplication.class);
        projectApplicationRepository.save(projectApplication);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectApplicationForm resources) {
        ProjectApplication projectApplication = projectApplicationRepository.findById(resources.getId()).orElseGet(ProjectApplication::new);
        ValidationUtil.isNull( projectApplication.getId(),"Application","id",resources.getId());
        BeanUtil.copyProperties(resources,projectApplication);
        projectApplicationRepository.save(projectApplication);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long projectId : ids) {
            projectApplicationRepository.deleteById(projectId);
        }
    }

    @Override
    public void download(List<ProjectApplicationDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectApplicationDto application : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("机构ID", application.getDept().getId());
            map.put("项目名称", application.getProjectName());
            map.put("项目编号", application.getProjectCode());
            map.put("项目类别", application.getCategory());
            map.put("上级项目", application.getParentProject());
            map.put("项目计划及资助方", application.getPlanProject());
            map.put("项目负责人", application.getLeader());
            map.put("项目类型", application.getProjectType());
            map.put("省", application.getProvince());
            map.put("市", application.getCity());
            map.put("区", application.getCounty());
            map.put("街道", application.getStreet());
            map.put("社区", application.getCommunity());
            map.put("启动时间", application.getStartTime());
            map.put("结束时间", application.getEndTime());
            map.put("项目概述", application.getOverview());
            map.put("需求分析", application.getDemand());
            map.put("项目备注", application.getRemark());
            map.put("项目状态：0、未开始；1、执行中；2、已完成", application.getProjectStatus());
            map.put("审批状态：0、未审批；1、审批通过；2、审批不通过", application.getApprovalStatus());
            map.put("项目书", application.getProposals());
            map.put("项目合同", application.getContracts());
            map.put("合同金额", application.getAmount());
            map.put("配套资金", application.getCounterpartFunding());
            map.put("创建时间", application.getCreateTime());
            map.put("创建人", application.getCreateBy());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
