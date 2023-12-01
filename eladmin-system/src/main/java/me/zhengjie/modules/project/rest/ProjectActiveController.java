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
import me.zhengjie.modules.project.domain.ProjectActive;
import me.zhengjie.modules.project.service.ProjectActiveService;
import me.zhengjie.modules.project.service.dto.ProjectActiveQueryCriteria;
import me.zhengjie.modules.project.service.dto.ProjectActiveDto;
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
* @date 2023-11-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "active管理")
@RequestMapping("/api/project/active")
public class ProjectActiveController {

    private final ProjectActiveService projectActiveService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('project:active:list')")
    public void exportActive(HttpServletResponse response, ProjectActiveQueryCriteria criteria) throws IOException {
        projectActiveService.download(projectActiveService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询active")
    @ApiOperation("查询active")
    @PreAuthorize("@el.check('project:active:list')")
    public ResponseEntity<PageResult<ProjectActiveDto>> queryActive(ProjectActiveQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(projectActiveService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增active")
    @ApiOperation("新增active")
    @PreAuthorize("@el.check('project:active:add')")
    public ResponseEntity<Object> createActive(@Validated @RequestBody ProjectActive resources){
        projectActiveService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改active")
    @ApiOperation("修改active")
    @PreAuthorize("@el.check('project:active:edit')")
    public ResponseEntity<Object> updateActive(@Validated @RequestBody ProjectActive resources){
        projectActiveService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除active")
    @ApiOperation("删除active")
    @PreAuthorize("@el.check('project:active:del')")
    public ResponseEntity<Object> deleteActive(@RequestBody Long[] ids) {
        projectActiveService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
