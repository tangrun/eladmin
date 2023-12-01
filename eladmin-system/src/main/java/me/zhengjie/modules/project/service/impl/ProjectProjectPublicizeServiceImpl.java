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

import me.zhengjie.modules.project.domain.ProjectPublicize;
import me.zhengjie.modules.project.service.dto.ProjectPublicizeDto;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectPublicizeRepository;
import me.zhengjie.modules.project.service.ProjectPublicizeService;
import me.zhengjie.modules.project.service.dto.ProjectPublicizeQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectPublicizeMapper;
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
public class ProjectProjectPublicizeServiceImpl implements ProjectPublicizeService {

    private final ProjectPublicizeRepository projectPublicizeRepository;
    private final ProjectPublicizeMapper projectPublicizeMapper;

    @Override
    public PageResult<ProjectPublicizeDto> queryAll(ProjectPublicizeQueryCriteria criteria, Pageable pageable){
        Page<ProjectPublicize> page = projectPublicizeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectPublicizeMapper::toDto));
    }

    @Override
    public List<ProjectPublicizeDto> queryAll(ProjectPublicizeQueryCriteria criteria){
        return projectPublicizeMapper.toDto(projectPublicizeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectPublicizeDto findById(Long publicizeId) {
        ProjectPublicize projectPublicize = projectPublicizeRepository.findById(publicizeId).orElseGet(ProjectPublicize::new);
        ValidationUtil.isNull(projectPublicize.getPublicizeId(),"Publicize","publicizeId",publicizeId);
        return projectPublicizeMapper.toDto(projectPublicize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectPublicize resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setPublicizeId(snowflake.nextId());
        projectPublicizeRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectPublicize resources) {
        ProjectPublicize projectPublicize = projectPublicizeRepository.findById(resources.getPublicizeId()).orElseGet(ProjectPublicize::new);
        ValidationUtil.isNull( projectPublicize.getPublicizeId(),"Publicize","id",resources.getPublicizeId());
        projectPublicize.copy(resources);
        projectPublicizeRepository.save(projectPublicize);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long publicizeId : ids) {
            projectPublicizeRepository.deleteById(publicizeId);
        }
    }

    @Override
    public void download(List<ProjectPublicizeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectPublicizeDto publicize : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("项目ID", publicize.getProjectId());
            map.put("媒体名称", publicize.getMediaName());
            map.put("宣传主题", publicize.getTheme());
            map.put("媒体级别", publicize.getMediaLevel());
            map.put("发布时间", publicize.getPublishTime());
            map.put("网站链接", publicize.getUrl());
            map.put("图片", publicize.getFiles());
            map.put("创建人", publicize.getCreateBy());
            map.put("创建时间", publicize.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
