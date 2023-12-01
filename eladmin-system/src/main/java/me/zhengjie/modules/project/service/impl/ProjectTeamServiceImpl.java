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

import me.zhengjie.modules.project.domain.ProjectTeam;
import me.zhengjie.modules.project.service.dto.ProjectTeamDto;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectTeamRepository;
import me.zhengjie.modules.project.service.ProjectTeamService;
import me.zhengjie.modules.project.service.dto.ProjectTeamQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectTeamMapper;
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
public class ProjectTeamServiceImpl implements ProjectTeamService {

    private final ProjectTeamRepository projectTeamRepository;
    private final ProjectTeamMapper projectTeamMapper;

    @Override
    public PageResult<ProjectTeamDto> queryAll(ProjectTeamQueryCriteria criteria, Pageable pageable){
        Page<ProjectTeam> page = projectTeamRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectTeamMapper::toDto));
    }

    @Override
    public List<ProjectTeamDto> queryAll(ProjectTeamQueryCriteria criteria){
        return projectTeamMapper.toDto(projectTeamRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectTeamDto findById(Long memberId) {
        ProjectTeam projectTeam = projectTeamRepository.findById(memberId).orElseGet(ProjectTeam::new);
        ValidationUtil.isNull(projectTeam.getMemberId(),"ApplicationTeam","memberId",memberId);
        return projectTeamMapper.toDto(projectTeam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectTeam resources) {
        projectTeamRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectTeam resources) {
        ProjectTeam projectTeam = projectTeamRepository.findById(resources.getMemberId()).orElseGet(ProjectTeam::new);
        ValidationUtil.isNull( projectTeam.getMemberId(),"ApplicationTeam","id",resources.getMemberId());
        projectTeam.copy(resources);
        projectTeamRepository.save(projectTeam);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long memberId : ids) {
            projectTeamRepository.deleteById(memberId);
        }
    }

    @Override
    public void download(List<ProjectTeamDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectTeamDto applicationTeam : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("姓名", applicationTeam.getName());
            map.put("性别", applicationTeam.getSex());
            map.put("年龄", applicationTeam.getAge());
            map.put("职称", applicationTeam.getTitle());
            map.put("项目职务", applicationTeam.getDuties());
            map.put("电话", applicationTeam.getMobile());
            map.put("邮箱", applicationTeam.getEmail());
            map.put("任职开始时间", applicationTeam.getEntryTime());
            map.put("学历", applicationTeam.getEducation());
            map.put("专业", applicationTeam.getSpeciality());
            map.put("备注", applicationTeam.getRemark());
            map.put("录入人", applicationTeam.getCreateBy());
            map.put("录入时间", applicationTeam.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
