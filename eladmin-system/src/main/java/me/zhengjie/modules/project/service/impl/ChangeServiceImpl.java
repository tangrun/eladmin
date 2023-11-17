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

import me.zhengjie.modules.project.domain.Change;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.ChangeRepository;
import me.zhengjie.modules.project.service.ChangeService;
import me.zhengjie.modules.project.service.dto.ChangeDto;
import me.zhengjie.modules.project.service.dto.ChangeQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.ChangeMapper;
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
public class ChangeServiceImpl implements ChangeService {

    private final ChangeRepository changeRepository;
    private final ChangeMapper changeMapper;

    @Override
    public PageResult<ChangeDto> queryAll(ChangeQueryCriteria criteria, Pageable pageable){
        Page<Change> page = changeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(changeMapper::toDto));
    }

    @Override
    public List<ChangeDto> queryAll(ChangeQueryCriteria criteria){
        return changeMapper.toDto(changeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ChangeDto findById(Long changeId) {
        Change change = changeRepository.findById(changeId).orElseGet(Change::new);
        ValidationUtil.isNull(change.getChangeId(),"Change","changeId",changeId);
        return changeMapper.toDto(change);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Change resources) {
        changeRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Change resources) {
        Change change = changeRepository.findById(resources.getChangeId()).orElseGet(Change::new);
        ValidationUtil.isNull( change.getChangeId(),"Change","id",resources.getChangeId());
        change.copy(resources);
        changeRepository.save(change);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long changeId : ids) {
            changeRepository.deleteById(changeId);
        }
    }

    @Override
    public void download(List<ChangeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ChangeDto change : all) {
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