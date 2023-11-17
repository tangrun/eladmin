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
import me.zhengjie.modules.project.domain.ProjectSupervise;
import me.zhengjie.modules.project.service.ProjectSuperviseService;
import me.zhengjie.modules.project.service.dto.ProjectSuperviseQueryCriteria;
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
import me.zhengjie.modules.project.service.dto.ProjectSuperviseDto;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-14
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "project管理")
@RequestMapping("/api/projectSupervise")
public class ProjectSuperviseController {

    private final ProjectSuperviseService projectSuperviseService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('projectSupervise:list')")
    public void exportProjectSupervise(HttpServletResponse response, ProjectSuperviseQueryCriteria criteria) throws IOException {
        projectSuperviseService.download(projectSuperviseService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询project")
    @ApiOperation("查询project")
    @PreAuthorize("@el.check('projectSupervise:list')")
    public ResponseEntity<PageResult<ProjectSuperviseDto>> queryProjectSupervise(ProjectSuperviseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(projectSuperviseService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增project")
    @ApiOperation("新增project")
    @PreAuthorize("@el.check('projectSupervise:add')")
    public ResponseEntity<Object> createProjectSupervise(@Validated @RequestBody ProjectSupervise resources){
        projectSuperviseService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改project")
    @ApiOperation("修改project")
    @PreAuthorize("@el.check('projectSupervise:edit')")
    public ResponseEntity<Object> updateProjectSupervise(@Validated @RequestBody ProjectSupervise resources){
        projectSuperviseService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除project")
    @ApiOperation("删除project")
    @PreAuthorize("@el.check('projectSupervise:del')")
    public ResponseEntity<Object> deleteProjectSupervise(@RequestBody Long[] ids) {
        projectSuperviseService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}