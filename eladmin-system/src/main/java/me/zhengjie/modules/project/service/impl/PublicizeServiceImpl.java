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

import me.zhengjie.modules.project.domain.Publicize;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.project.repository.PublicizeRepository;
import me.zhengjie.modules.project.service.PublicizeService;
import me.zhengjie.modules.project.service.dto.PublicizeDto;
import me.zhengjie.modules.project.service.dto.PublicizeQueryCriteria;
import me.zhengjie.modules.project.service.mapstruct.PublicizeMapper;
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
public class PublicizeServiceImpl implements PublicizeService {

    private final PublicizeRepository publicizeRepository;
    private final PublicizeMapper publicizeMapper;

    @Override
    public PageResult<PublicizeDto> queryAll(PublicizeQueryCriteria criteria, Pageable pageable){
        Page<Publicize> page = publicizeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(publicizeMapper::toDto));
    }

    @Override
    public List<PublicizeDto> queryAll(PublicizeQueryCriteria criteria){
        return publicizeMapper.toDto(publicizeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public PublicizeDto findById(Long publicizeId) {
        Publicize publicize = publicizeRepository.findById(publicizeId).orElseGet(Publicize::new);
        ValidationUtil.isNull(publicize.getPublicizeId(),"Publicize","publicizeId",publicizeId);
        return publicizeMapper.toDto(publicize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Publicize resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setPublicizeId(snowflake.nextId()); 
        publicizeRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Publicize resources) {
        Publicize publicize = publicizeRepository.findById(resources.getPublicizeId()).orElseGet(Publicize::new);
        ValidationUtil.isNull( publicize.getPublicizeId(),"Publicize","id",resources.getPublicizeId());
        publicize.copy(resources);
        publicizeRepository.save(publicize);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long publicizeId : ids) {
            publicizeRepository.deleteById(publicizeId);
        }
    }

    @Override
    public void download(List<PublicizeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PublicizeDto publicize : all) {
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