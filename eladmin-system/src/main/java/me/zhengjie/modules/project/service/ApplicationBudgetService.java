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
package me.zhengjie.modules.project.service;

import me.zhengjie.modules.project.domain.ApplicationBudget;
import me.zhengjie.modules.project.service.dto.ApplicationBudgetDto;
import me.zhengjie.modules.project.service.dto.ApplicationBudgetQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import me.zhengjie.utils.PageResult;

/**
* @website https://eladmin.vip
* @description 服务接口
* @author hb
* @date 2023-11-15
**/
public interface ApplicationBudgetService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    PageResult<ApplicationBudgetDto> queryAll(ApplicationBudgetQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ApplicationBudgetDto>
    */
    List<ApplicationBudgetDto> queryAll(ApplicationBudgetQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param budgetId ID
     * @return ApplicationBudgetDto
     */
    ApplicationBudgetDto findById(Long budgetId);

    /**
    * 创建
    * @param resources /
    */
    void create(ApplicationBudget resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(ApplicationBudget resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ApplicationBudgetDto> all, HttpServletResponse response) throws IOException;
}