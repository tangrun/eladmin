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
import me.zhengjie.modules.project.domain.ProjectPublicize;
import me.zhengjie.modules.project.service.ProjectPublicizeService;
import me.zhengjie.modules.project.service.dto.ProjectPublicizeQueryCriteria;
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
import me.zhengjie.modules.project.service.dto.ProjectPublicizeDto;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "publicize管理")
@RequestMapping("/api/project/publicize")
public class ProjectPublicizeController {

    private final ProjectPublicizeService projectPublicizeService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('project:publicize:list')")
    public void exportPublicize(HttpServletResponse response, ProjectPublicizeQueryCriteria criteria) throws IOException {
        projectPublicizeService.download(projectPublicizeService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询publicize")
    @ApiOperation("查询publicize")
    @PreAuthorize("@el.check('project:publicize:list')")
    public ResponseEntity<PageResult<ProjectPublicizeDto>> queryPublicize(ProjectPublicizeQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(projectPublicizeService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增publicize")
    @ApiOperation("新增publicize")
    @PreAuthorize("@el.check('project:publicize:add')")
    public ResponseEntity<Object> createPublicize(@Validated @RequestBody ProjectPublicize resources){
        projectPublicizeService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改publicize")
    @ApiOperation("修改publicize")
    @PreAuthorize("@el.check('project:publicize:edit')")
    public ResponseEntity<Object> updatePublicize(@Validated @RequestBody ProjectPublicize resources){
        projectPublicizeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除publicize")
    @ApiOperation("删除publicize")
    @PreAuthorize("@el.check('project:publicize:del')")
    public ResponseEntity<Object> deletePublicize(@RequestBody Long[] ids) {
        projectPublicizeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
