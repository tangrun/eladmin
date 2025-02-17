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
import me.zhengjie.modules.project.domain.ProjectBudget;
import me.zhengjie.modules.project.service.ProjectBudgetService;
import me.zhengjie.modules.project.service.dto.ProjectBudgetQueryCriteria;
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
import me.zhengjie.modules.project.service.dto.ProjectBudgetDto;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "budget管理")
@RequestMapping("/api/project/budget")
public class ProjectBudgetController {

    private final ProjectBudgetService projectBudgetService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('project:budget:list')")
    public void exportApplicationBudget(HttpServletResponse response, ProjectBudgetQueryCriteria criteria) throws IOException {
        projectBudgetService.download(projectBudgetService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询budget")
    @ApiOperation("查询budget")
    @PreAuthorize("@el.check('project:budget:list')")
    public ResponseEntity<PageResult<ProjectBudgetDto>> queryApplicationBudget(ProjectBudgetQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(projectBudgetService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增budget")
    @ApiOperation("新增budget")
    @PreAuthorize("@el.check('project:budget:add')")
    public ResponseEntity<Object> createApplicationBudget(@Validated @RequestBody ProjectBudget resources){
        projectBudgetService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改budget")
    @ApiOperation("修改budget")
    @PreAuthorize("@el.check('project:budget:edit')")
    public ResponseEntity<Object> updateApplicationBudget(@Validated @RequestBody ProjectBudget resources){
        projectBudgetService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除budget")
    @ApiOperation("删除budget")
    @PreAuthorize("@el.check('project:budget:del')")
    public ResponseEntity<Object> deleteApplicationBudget(@RequestBody Long[] ids) {
        projectBudgetService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
