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

import me.zhengjie.modules.project.domain.Active;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ActiveRepository;
import me.zhengjie.modules.project.service.ActiveService;
import me.zhengjie.modules.project.service.dto.ActiveDto;
import me.zhengjie.modules.project.service.dto.ActiveQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ActiveMapper;
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
public class ActiveServiceImpl implements ActiveService {

    private final ActiveRepository activeRepository;
    private final ActiveMapper activeMapper;

    @Override
    public PageResult<ActiveDto> queryAll(ActiveQueryCriteria criteria, Pageable pageable){
        Page<Active> page = activeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(activeMapper::toDto));
    }

    @Override
    public List<ActiveDto> queryAll(ActiveQueryCriteria criteria){
        return activeMapper.toDto(activeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ActiveDto findById(Long activeId) {
        Active active = activeRepository.findById(activeId).orElseGet(Active::new);
        ValidationUtil.isNull(active.getActiveId(),"Active","activeId",activeId);
        return activeMapper.toDto(active);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Active resources) {
        activeRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Active resources) {
        Active active = activeRepository.findById(resources.getActiveId()).orElseGet(Active::new);
        ValidationUtil.isNull( active.getActiveId(),"Active","id",resources.getActiveId());
        active.copy(resources);
        activeRepository.save(active);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long activeId : ids) {
            activeRepository.deleteById(activeId);
        }
    }

    @Override
    public void download(List<ActiveDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ActiveDto active : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("项目ID", active.getProjectId());
            map.put("项目目标ID", active.getPlanId());
            map.put("活动主题", active.getName());
            map.put("活动内容", active.getContent());
            map.put("活动时间", active.getActiveTime());
            map.put("活动地点", active.getVenue());
            map.put("参与活动人数", active.getNum());
            map.put("活动成效", active.getEffect());
            map.put("活动费用", active.getFee());
            map.put("是否验收", active.getIschecked());
            map.put("创建人", active.getCreateBy());
            map.put("创建时间", active.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}