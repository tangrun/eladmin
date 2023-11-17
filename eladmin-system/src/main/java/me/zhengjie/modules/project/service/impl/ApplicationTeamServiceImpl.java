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

import me.zhengjie.modules.project.domain.ApplicationTeam;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ApplicationTeamRepository;
import me.zhengjie.modules.project.service.ApplicationTeamService;
import me.zhengjie.modules.project.service.dto.ApplicationTeamDto;
import me.zhengjie.modules.project.service.dto.ApplicationTeamQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ApplicationTeamMapper;
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
public class ApplicationTeamServiceImpl implements ApplicationTeamService {

    private final ApplicationTeamRepository applicationTeamRepository;
    private final ApplicationTeamMapper applicationTeamMapper;

    @Override
    public PageResult<ApplicationTeamDto> queryAll(ApplicationTeamQueryCriteria criteria, Pageable pageable){
        Page<ApplicationTeam> page = applicationTeamRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(applicationTeamMapper::toDto));
    }

    @Override
    public List<ApplicationTeamDto> queryAll(ApplicationTeamQueryCriteria criteria){
        return applicationTeamMapper.toDto(applicationTeamRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ApplicationTeamDto findById(Long memberId) {
        ApplicationTeam applicationTeam = applicationTeamRepository.findById(memberId).orElseGet(ApplicationTeam::new);
        ValidationUtil.isNull(applicationTeam.getMemberId(),"ApplicationTeam","memberId",memberId);
        return applicationTeamMapper.toDto(applicationTeam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ApplicationTeam resources) {
        applicationTeamRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ApplicationTeam resources) {
        ApplicationTeam applicationTeam = applicationTeamRepository.findById(resources.getMemberId()).orElseGet(ApplicationTeam::new);
        ValidationUtil.isNull( applicationTeam.getMemberId(),"ApplicationTeam","id",resources.getMemberId());
        applicationTeam.copy(resources);
        applicationTeamRepository.save(applicationTeam);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long memberId : ids) {
            applicationTeamRepository.deleteById(memberId);
        }
    }

    @Override
    public void download(List<ApplicationTeamDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ApplicationTeamDto applicationTeam : all) {
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