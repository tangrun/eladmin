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

import me.zhengjie.exception.EntityExistException;
import me.zhengjie.modules.project.domain.ProjectCategory;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectCategoryRepository;
import me.zhengjie.modules.project.service.ProjectCategoryService;
import me.zhengjie.modules.project.service.dto.ProjectCategoryDto;
import me.zhengjie.modules.project.service.dto.ProjectCategoryQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectCategoryMapper;
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
public class ProjectCategoryServiceImpl implements ProjectCategoryService {

    private final ProjectCategoryRepository projectCategoryRepository;
    private final ProjectCategoryMapper projectCategoryMapper;

    @Override
    public PageResult<ProjectCategoryDto> queryAll(ProjectCategoryQueryCriteria criteria, Pageable pageable){
        Page<ProjectCategory> page = projectCategoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectCategoryMapper::toDto));
    }

    @Override
    public List<ProjectCategoryDto> queryAll(ProjectCategoryQueryCriteria criteria){
        return projectCategoryMapper.toDto(projectCategoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectCategoryDto findById(Long categoryId) {
        ProjectCategory projectCategory = projectCategoryRepository.findById(categoryId).orElseGet(ProjectCategory::new);
        ValidationUtil.isNull(projectCategory.getCategoryId(),"ProjectCategory","categoryId",categoryId);
        return projectCategoryMapper.toDto(projectCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectCategory resources) {
        if(projectCategoryRepository.findByName(resources.getName()) != null){
            throw new EntityExistException(ProjectCategory.class,"name",resources.getName());
        }
        projectCategoryRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectCategory resources) {
        ProjectCategory projectCategory = projectCategoryRepository.findById(resources.getCategoryId()).orElseGet(ProjectCategory::new);
        ValidationUtil.isNull( projectCategory.getCategoryId(),"ProjectCategory","id",resources.getCategoryId());
        ProjectCategory projectCategory1 = projectCategoryRepository.findByName(resources.getName());
        if(projectCategory1 != null && !projectCategory1.getCategoryId().equals(projectCategory.getCategoryId())){
            throw new EntityExistException(ProjectCategory.class,"name",resources.getName());
        }
        projectCategory.copy(resources);
        projectCategoryRepository.save(projectCategory);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long categoryId : ids) {
            projectCategoryRepository.deleteById(categoryId);
        }
    }

    @Override
    public void download(List<ProjectCategoryDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectCategoryDto projectCategory : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("上级类别ID", projectCategory.getPid());
            map.put("类别名称", projectCategory.getName());
            map.put("类别状态", projectCategory.getEnabled());
            map.put("排序", projectCategory.getJobSort());
            map.put("创建者", projectCategory.getCreateBy());
            map.put("更新者", projectCategory.getUpdateBy());
            map.put("创建日期", projectCategory.getCreateTime());
            map.put("更新时间", projectCategory.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}