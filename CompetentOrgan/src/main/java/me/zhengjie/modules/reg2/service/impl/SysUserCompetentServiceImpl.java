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
package me.zhengjie.modules.reg2.service.impl;

import me.zhengjie.modules.reg2.domain.SysUserCompetent;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.reg2.repository.SysUserCompetentRepository;
import me.zhengjie.modules.reg2.service.SysUserCompetentService;
import me.zhengjie.modules.reg2.service.dto.SysUserCompetentDto;
import me.zhengjie.modules.reg2.service.dto.SysUserCompetentQueryCriteria;
import me.zhengjie.modules.reg2.service.mapstruct.SysUserCompetentMapper;
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
* @author rain
* @date 2023-11-20
**/
@Service
@RequiredArgsConstructor
public class SysUserCompetentServiceImpl implements SysUserCompetentService {

    private final SysUserCompetentRepository sysUserCompetentRepository;
    private final SysUserCompetentMapper sysUserCompetentMapper;

    @Override
    public PageResult<SysUserCompetentDto> queryAll(SysUserCompetentQueryCriteria criteria, Pageable pageable){
        Page<SysUserCompetent> page = sysUserCompetentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(sysUserCompetentMapper::toDto));
    }

    @Override
    public List<SysUserCompetentDto> queryAll(SysUserCompetentQueryCriteria criteria){
        return sysUserCompetentMapper.toDto(sysUserCompetentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public SysUserCompetentDto findById(Long id) {
        SysUserCompetent sysUserCompetent = sysUserCompetentRepository.findById(id).orElseGet(SysUserCompetent::new);
        ValidationUtil.isNull(sysUserCompetent.getId(),"SysUserCompetent","id",id);
        return sysUserCompetentMapper.toDto(sysUserCompetent);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysUserCompetent resources) {
        sysUserCompetentRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUserCompetent resources) {
        SysUserCompetent sysUserCompetent = sysUserCompetentRepository.findById(resources.getId()).orElseGet(SysUserCompetent::new);
        ValidationUtil.isNull( sysUserCompetent.getId(),"SysUserCompetent","id",resources.getId());
        sysUserCompetent.copy(resources);
        sysUserCompetentRepository.save(sysUserCompetent);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            sysUserCompetentRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<SysUserCompetentDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SysUserCompetentDto sysUserCompetent : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("行政等级", sysUserCompetent.getAdministrativeLevel());
            map.put("省", sysUserCompetent.getProvince());
            map.put("市", sysUserCompetent.getCity());
            map.put("县、区", sysUserCompetent.getCounty());
            map.put("街道", sysUserCompetent.getStreet());
            map.put("社区", sysUserCompetent.getCommunity());
            map.put("机构名称", sysUserCompetent.getName());
            map.put("机构简介", sysUserCompetent.getDescription());
            map.put("机构电话", sysUserCompetent.getPhone());
            map.put("机构邮箱", sysUserCompetent.getEmail());
            map.put("机构传真", sysUserCompetent.getFax());
            map.put("机构地址", sysUserCompetent.getAddress());
            map.put("管理员名字", sysUserCompetent.getManagerName());
            map.put("管理员电话", sysUserCompetent.getManagerPhone());
            map.put("管理员邮箱", sysUserCompetent.getManagerEmail());
            map.put("创建者", sysUserCompetent.getCreateBy());
            map.put("更新者", sysUserCompetent.getUpdateBy());
            map.put("创建日期", sysUserCompetent.getCreateTime());
            map.put("更新时间", sysUserCompetent.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}