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

import me.zhengjie.modules.project.domain.ApplicationBudget;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ApplicationBudgetRepository;
import me.zhengjie.modules.project.service.ApplicationBudgetService;
import me.zhengjie.modules.project.service.dto.ApplicationBudgetDto;
import me.zhengjie.modules.project.service.dto.ApplicationBudgetQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ApplicationBudgetMapper;
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
public class ApplicationBudgetServiceImpl implements ApplicationBudgetService {

    private final ApplicationBudgetRepository applicationBudgetRepository;
    private final ApplicationBudgetMapper applicationBudgetMapper;

    @Override
    public PageResult<ApplicationBudgetDto> queryAll(ApplicationBudgetQueryCriteria criteria, Pageable pageable){
        Page<ApplicationBudget> page = applicationBudgetRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(applicationBudgetMapper::toDto));
    }

    @Override
    public List<ApplicationBudgetDto> queryAll(ApplicationBudgetQueryCriteria criteria){
        return applicationBudgetMapper.toDto(applicationBudgetRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ApplicationBudgetDto findById(Long budgetId) {
        ApplicationBudget applicationBudget = applicationBudgetRepository.findById(budgetId).orElseGet(ApplicationBudget::new);
        ValidationUtil.isNull(applicationBudget.getBudgetId(),"ApplicationBudget","budgetId",budgetId);
        return applicationBudgetMapper.toDto(applicationBudget);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ApplicationBudget resources) {
        applicationBudgetRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ApplicationBudget resources) {
        ApplicationBudget applicationBudget = applicationBudgetRepository.findById(resources.getBudgetId()).orElseGet(ApplicationBudget::new);
        ValidationUtil.isNull( applicationBudget.getBudgetId(),"ApplicationBudget","id",resources.getBudgetId());
        applicationBudget.copy(resources);
        applicationBudgetRepository.save(applicationBudget);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long budgetId : ids) {
            applicationBudgetRepository.deleteById(budgetId);
        }
    }

    @Override
    public void download(List<ApplicationBudgetDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ApplicationBudgetDto applicationBudget : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("项目ID", applicationBudget.getProjectId());
            map.put("预算名称", applicationBudget.getName());
            map.put("预算值", applicationBudget.getBudgetValue());
            map.put("说明", applicationBudget.getRemark());
            map.put("录入人", applicationBudget.getCreateBy());
            map.put("录入时间", applicationBudget.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}