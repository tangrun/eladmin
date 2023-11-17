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

import me.zhengjie.modules.project.domain.ProjectSelf;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectSelfRepository;
import me.zhengjie.modules.project.service.ProjectSelfService;
import me.zhengjie.modules.project.service.dto.ProjectSelfDto;
import me.zhengjie.modules.project.service.dto.ProjectSelfQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectSelfMapper;
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
public class ProjectSelfServiceImpl implements ProjectSelfService {

    private final ProjectSelfRepository projectSelfRepository;
    private final ProjectSelfMapper projectSelfMapper;

    @Override
    public PageResult<ProjectSelfDto> queryAll(ProjectSelfQueryCriteria criteria, Pageable pageable){
        Page<ProjectSelf> page = projectSelfRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectSelfMapper::toDto));
    }

    @Override
    public List<ProjectSelfDto> queryAll(ProjectSelfQueryCriteria criteria){
        return projectSelfMapper.toDto(projectSelfRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectSelfDto findById(Long selfId) {
        ProjectSelf projectSelf = projectSelfRepository.findById(selfId).orElseGet(ProjectSelf::new);
        ValidationUtil.isNull(projectSelf.getSelfId(),"ProjectSelf","selfId",selfId);
        return projectSelfMapper.toDto(projectSelf);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectSelf resources) {
        projectSelfRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectSelf resources) {
        ProjectSelf projectSelf = projectSelfRepository.findById(resources.getSelfId()).orElseGet(ProjectSelf::new);
        ValidationUtil.isNull( projectSelf.getSelfId(),"ProjectSelf","id",resources.getSelfId());
        projectSelf.copy(resources);
        projectSelfRepository.save(projectSelf);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long selfId : ids) {
            projectSelfRepository.deleteById(selfId);
        }
    }

    @Override
    public void download(List<ProjectSelfDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectSelfDto projectSelf : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("项目ID", projectSelf.getProjectId());
            map.put("评估期", projectSelf.getInterim());
            map.put("项目执行总结", projectSelf.getSummary());
            map.put("下一步工作安排记建议", projectSelf.getNextRule());
            map.put("项目创新性", projectSelf.getInnovation());
            map.put("项目可持续性", projectSelf.getContinuity());
            map.put("项目特色、高点", projectSelf.getCharacteristic());
            map.put("项目反思", projectSelf.getReflect());
            map.put("创建者", projectSelf.getCreateBy());
            map.put("创建日期", projectSelf.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}