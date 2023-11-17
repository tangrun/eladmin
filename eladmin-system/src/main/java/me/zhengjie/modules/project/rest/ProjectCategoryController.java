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
import me.zhengjie.modules.project.domain.ProjectCategory;
import me.zhengjie.modules.project.service.ProjectCategoryService;
import me.zhengjie.modules.project.service.dto.ProjectCategoryQueryCriteria;
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
import me.zhengjie.modules.project.service.dto.ProjectCategoryDto;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "category管理")
@RequestMapping("/api/projectCategory")
public class ProjectCategoryController {

    private final ProjectCategoryService projectCategoryService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('projectCategory:list')")
    public void exportProjectCategory(HttpServletResponse response, ProjectCategoryQueryCriteria criteria) throws IOException {
        projectCategoryService.download(projectCategoryService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询category")
    @ApiOperation("查询category")
    @PreAuthorize("@el.check('projectCategory:list')")
    public ResponseEntity<PageResult<ProjectCategoryDto>> queryProjectCategory(ProjectCategoryQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(projectCategoryService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增category")
    @ApiOperation("新增category")
    @PreAuthorize("@el.check('projectCategory:add')")
    public ResponseEntity<Object> createProjectCategory(@Validated @RequestBody ProjectCategory resources){
        projectCategoryService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改category")
    @ApiOperation("修改category")
    @PreAuthorize("@el.check('projectCategory:edit')")
    public ResponseEntity<Object> updateProjectCategory(@Validated @RequestBody ProjectCategory resources){
        projectCategoryService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除category")
    @ApiOperation("删除category")
    @PreAuthorize("@el.check('projectCategory:del')")
    public ResponseEntity<Object> deleteProjectCategory(@RequestBody Long[] ids) {
        projectCategoryService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}