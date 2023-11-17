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
import me.zhengjie.modules.project.domain.Application;
import me.zhengjie.modules.project.service.ApplicationService;
import me.zhengjie.modules.project.service.dto.ApplicationQueryCriteria;
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
import me.zhengjie.modules.project.service.dto.ApplicationDto;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "application管理")
@RequestMapping("/api/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('application:list')")
    public void exportApplication(HttpServletResponse response, ApplicationQueryCriteria criteria) throws IOException {
        applicationService.download(applicationService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询application")
    @ApiOperation("查询application")
    @PreAuthorize("@el.check('application:list')")
    public ResponseEntity<PageResult<ApplicationDto>> queryApplication(ApplicationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(applicationService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增application")
    @ApiOperation("新增application")
    @PreAuthorize("@el.check('application:add')")
    public ResponseEntity<Object> createApplication(@Validated @RequestBody Application resources){
        applicationService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改application")
    @ApiOperation("修改application")
    @PreAuthorize("@el.check('application:edit')")
    public ResponseEntity<Object> updateApplication(@Validated @RequestBody Application resources){
        applicationService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除application")
    @ApiOperation("删除application")
    @PreAuthorize("@el.check('application:del')")
    public ResponseEntity<Object> deleteApplication(@RequestBody Long[] ids) {
        applicationService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}