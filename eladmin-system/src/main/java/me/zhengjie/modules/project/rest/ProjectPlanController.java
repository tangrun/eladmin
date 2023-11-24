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
import me.zhengjie.base.BaseEntity;
import me.zhengjie.domain.LocalStorage;
import me.zhengjie.modules.project.domain.ProjectPlan;
import me.zhengjie.modules.project.service.ProjectPlanService;
import me.zhengjie.modules.project.service.dto.ProjectPlanCreateForm;
import me.zhengjie.modules.project.service.dto.ProjectPlanQueryCriteria;
import me.zhengjie.service.LocalStorageService;
import me.zhengjie.service.dto.LocalStorageDto;
import me.zhengjie.service.mapstruct.LocalStorageMapper;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zhengjie.utils.PageResult;
import me.zhengjie.modules.project.service.dto.ProjectPlanDto;
import org.springframework.web.multipart.MultipartFile;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "projectPlan管理")
@RequestMapping("/api/projectPlan")
public class ProjectPlanController {

    private final ProjectPlanService projectPlanService;
    private final LocalStorageService localStorageService;
    private final LocalStorageMapper localStorageMapper;

    @Log("项目计划上传文件")
    @ApiOperation("项目计划上传文件")
    @PostMapping(value = "/upload")
    @PreAuthorize("@el.check('projectPlan:add','projectPlan:edit')")
    public ResponseEntity<LocalStorageDto> upload(@RequestBody MultipartFile file, HttpServletRequest request)throws Exception{
        LocalStorage localStorage = localStorageService.create(null, file);
        LocalStorageDto localStorageDto = localStorageMapper.toDto(localStorage);
        return new ResponseEntity<>(localStorageDto,HttpStatus.CREATED);
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('projectPlan:list')")
    public void exportProjectPlan(HttpServletResponse response, ProjectPlanQueryCriteria criteria) throws IOException {
        projectPlanService.download(projectPlanService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询projectPlan")
    @ApiOperation("查询projectPlan")
    @PreAuthorize("@el.check('projectPlan:list')")
    public ResponseEntity<PageResult<ProjectPlanDto>> queryProjectPlan(ProjectPlanQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(projectPlanService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增projectPlan")
    @ApiOperation("新增projectPlan")
    @PreAuthorize("@el.check('projectPlan:add')")
    public ResponseEntity<Object> createProjectPlan(@RequestBody ProjectPlanCreateForm resources){
        projectPlanService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改projectPlan")
    @ApiOperation("修改projectPlan")
    @PreAuthorize("@el.check('projectPlan:edit')")
    public ResponseEntity<Object> updateProjectPlan( @RequestBody ProjectPlan resources){
        projectPlanService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除projectPlan")
    @ApiOperation("删除projectPlan")
    @PreAuthorize("@el.check('projectPlan:del')")
    public ResponseEntity<Object> deleteProjectPlan(@RequestBody Long[] ids) {
        projectPlanService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
