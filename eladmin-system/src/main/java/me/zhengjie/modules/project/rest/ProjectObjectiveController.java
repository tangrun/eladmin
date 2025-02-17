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
import me.zhengjie.modules.project.domain.ProjectObjective;
import me.zhengjie.modules.project.service.ProjectObjectiveService;
import me.zhengjie.modules.project.service.dto.ProjectObjectiveQueryCriteria;
import me.zhengjie.modules.project.service.dto.ProjectObjectiveDto;
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

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-14
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "项目目标管理")
@RequestMapping("/api/project/objective")
public class ProjectObjectiveController {

    private final ProjectObjectiveService projectObjectiveService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('project:objective:list')")
    public void exportProjectApplicationPlan(HttpServletResponse response, ProjectObjectiveQueryCriteria criteria) throws IOException {
        projectObjectiveService.download(projectObjectiveService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询项目目标")
    @ApiOperation("查询项目目标")
    @PreAuthorize("@el.check('project:objective:list')")
    public ResponseEntity<PageResult<ProjectObjectiveDto>> queryProjectApplicationPlan(ProjectObjectiveQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(projectObjectiveService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增项目目标")
    @ApiOperation("新增项目目标")
    @PreAuthorize("@el.check('project:objective:add')")
    public ResponseEntity<Object> createProjectApplicationPlan(@Validated @RequestBody ProjectObjective resources){
        projectObjectiveService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改项目目标")
    @ApiOperation("修改项目目标")
    @PreAuthorize("@el.check('project:objective:edit')")
    public ResponseEntity<Object> updateProjectApplicationPlan(@Validated @RequestBody ProjectObjective resources){
        projectObjectiveService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除项目目标")
    @ApiOperation("删除项目目标")
    @PreAuthorize("@el.check('project:objective:del')")
    public ResponseEntity<Object> deleteProjectApplicationPlan(@RequestBody Long[] ids) {
        projectObjectiveService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
