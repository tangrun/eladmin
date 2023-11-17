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

import me.zhengjie.modules.project.domain.ProjectSupervise;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectSuperviseRepository;
import me.zhengjie.modules.project.service.ProjectSuperviseService;
import me.zhengjie.modules.project.service.dto.ProjectSuperviseDto;
import me.zhengjie.modules.project.service.dto.ProjectSuperviseQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectSuperviseMapper;
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
* @description 项目督导记录
* @author hb
* @date 2023-11-14
**/
@Service
@RequiredArgsConstructor
public class ProjectSuperviseServiceImpl implements ProjectSuperviseService {

    private final ProjectSuperviseRepository projectSuperviseRepository;
    private final ProjectSuperviseMapper projectSuperviseMapper;

    @Override
    public PageResult<ProjectSuperviseDto> queryAll(ProjectSuperviseQueryCriteria criteria, Pageable pageable){
        Page<ProjectSupervise> page = projectSuperviseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectSuperviseMapper::toDto));
    }

    @Override
    public List<ProjectSuperviseDto> queryAll(ProjectSuperviseQueryCriteria criteria){
        return projectSuperviseMapper.toDto(projectSuperviseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectSuperviseDto findById(Long superviseId) {
        ProjectSupervise projectSupervise = projectSuperviseRepository.findById(superviseId).orElseGet(ProjectSupervise::new);
        ValidationUtil.isNull(projectSupervise.getSuperviseId(),"ProjectSupervise","superviseId",superviseId);
        return projectSuperviseMapper.toDto(projectSupervise);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectSupervise resources) {
        projectSuperviseRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectSupervise resources) {
        ProjectSupervise projectSupervise = projectSuperviseRepository.findById(resources.getSuperviseId()).orElseGet(ProjectSupervise::new);
        ValidationUtil.isNull( projectSupervise.getSuperviseId(),"ProjectSupervise","id",resources.getSuperviseId());
        projectSupervise.copy(resources);
        projectSuperviseRepository.save(projectSupervise);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long superviseId : ids) {
            projectSuperviseRepository.deleteById(superviseId);
        }
    }

    @Override
    public void download(List<ProjectSuperviseDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectSuperviseDto projectSupervise : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("项目ID", projectSupervise.getProjectId());
            map.put("督导内容", projectSupervise.getContent());
            map.put("督导主题", projectSupervise.getTheme());
            map.put("督导评分", projectSupervise.getRating());
            map.put("督导建议", projectSupervise.getAdvise());
            map.put("图片", projectSupervise.getFiles());
            map.put("督导老师", projectSupervise.getCreateBy());
            map.put("督导时间", projectSupervise.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}