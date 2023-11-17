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
package me.zhengjie.modules.project.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.project.domain.ProjectApplicationPlan;
import me.zhengjie.modules.project.service.ProjectApplicationPlanService;
import me.zhengjie.modules.project.service.dto.ProjectApplicationPlanQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import me.zhengjie.utils.PageResult;
import me.zhengjie.modules.project.service.dto.ProjectApplicationPlanDto;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-14
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "项目目标管理")
@RequestMapping("/api/projectApplicationPlan")
public class ProjectApplicationPlanController {

    private final ProjectApplicationPlanService projectApplicationPlanService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('projectApplicationPlan:list')")
    public void exportProjectApplicationPlan(HttpServletResponse response, ProjectApplicationPlanQueryCriteria criteria) throws IOException {
        projectApplicationPlanService.download(projectApplicationPlanService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询项目目标")
    @ApiOperation("查询项目目标")
    @PreAuthorize("@el.check('projectApplicationPlan:list')")
    public ResponseEntity<PageResult<ProjectApplicationPlanDto>> queryProjectApplicationPlan(ProjectApplicationPlanQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(projectApplicationPlanService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增项目目标")
    @ApiOperation("新增项目目标")
    @PreAuthorize("@el.check('projectApplicationPlan:add')")
    public ResponseEntity<Object> createProjectApplicationPlan(@Validated @RequestBody ProjectApplicationPlan resources){
        projectApplicationPlanService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改项目目标")
    @ApiOperation("修改项目目标")
    @PreAuthorize("@el.check('projectApplicationPlan:edit')")
    public ResponseEntity<Object> updateProjectApplicationPlan(@Validated @RequestBody ProjectApplicationPlan resources){
        projectApplicationPlanService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除项目目标")
    @ApiOperation("删除项目目标")
    @PreAuthorize("@el.check('projectApplicationPlan:del')")
    public ResponseEntity<Object> deleteProjectApplicationPlan(@RequestBody Long[] ids) {
        projectApplicationPlanService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}