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

import me.zhengjie.modules.project.domain.ProjectChange;
import me.zhengjie.modules.project.service.dto.ProjectChangeDto;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ProjectChangeRepository;
import me.zhengjie.modules.project.service.ProjectChangeService;
import me.zhengjie.modules.project.service.dto.ProjectChangeQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ProjectChangeMapper;
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
public class ProjectProjectChangeServiceImpl implements ProjectChangeService {

    private final ProjectChangeRepository projectChangeRepository;
    private final ProjectChangeMapper projectChangeMapper;

    @Override
    public PageResult<ProjectChangeDto> queryAll(ProjectChangeQueryCriteria criteria, Pageable pageable){
        Page<ProjectChange> page = projectChangeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(projectChangeMapper::toDto));
    }

    @Override
    public List<ProjectChangeDto> queryAll(ProjectChangeQueryCriteria criteria){
        return projectChangeMapper.toDto(projectChangeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ProjectChangeDto findById(Long changeId) {
        ProjectChange projectChange = projectChangeRepository.findById(changeId).orElseGet(ProjectChange::new);
        ValidationUtil.isNull(projectChange.getChangeId(),"Change","changeId",changeId);
        return projectChangeMapper.toDto(projectChange);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectChange resources) {
        projectChangeRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectChange resources) {
        ProjectChange projectChange = projectChangeRepository.findById(resources.getChangeId()).orElseGet(ProjectChange::new);
        ValidationUtil.isNull( projectChange.getChangeId(),"Change","id",resources.getChangeId());
        projectChange.copy(resources);
        projectChangeRepository.save(projectChange);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long changeId : ids) {
            projectChangeRepository.deleteById(changeId);
        }
    }

    @Override
    public void download(List<ProjectChangeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ProjectChangeDto change : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("项目ID", change.getProjectId());
            map.put("变更类型：项目内容变更、预算变更", change.getChangeType());
            map.put("变更时间", change.getChangeTime());
            map.put("变更内容", change.getChangeContent());
            map.put("变更文件", change.getChangeFiles());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
