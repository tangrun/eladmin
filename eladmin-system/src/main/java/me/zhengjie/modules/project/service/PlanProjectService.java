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

import me.zhengjie.modules.project.service.dto.PlanProjectForm;
import me.zhengjie.modules.project.service.dto.PlanProjectDto;
import me.zhengjie.modules.project.service.dto.PlanProjectPublishReviewForm;
import me.zhengjie.modules.project.service.dto.PlanProjectQueryCriteria;
import org.springframework.data.domain.Pageable;

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
public interface PlanProjectService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    PageResult<PlanProjectDto> queryAll(PlanProjectQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ProjectPlanDto>
    */
    List<PlanProjectDto> queryAll(PlanProjectQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param planId ID
     * @return ProjectPlanDto
     */
    PlanProjectDto findById(Long planId);

    /**
    * 创建
    * @param resources /
    */
    void saveDraft(PlanProjectForm resources);
    void publish(PlanProjectForm resources);
    void review(PlanProjectPublishReviewForm resources);
    void edit(PlanProjectForm resources);

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
    void download(List<PlanProjectDto> all, HttpServletResponse response) throws IOException;
}
