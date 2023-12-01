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

import cn.hutool.core.collection.CollUtil;
import me.zhengjie.annotation.Log;
import me.zhengjie.domain.LocalStorage;
import me.zhengjie.modules.project.enums.PlanStatus;
import me.zhengjie.modules.project.service.PlanProjectService;
import me.zhengjie.modules.project.service.ProjectApplicationService;
import me.zhengjie.modules.project.service.dto.*;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.service.DeptService;
import me.zhengjie.modules.system.service.UserService;
import me.zhengjie.modules.system.service.dto.DeptDto;
import me.zhengjie.modules.system.service.dto.UserDto;
import me.zhengjie.modules.system.service.dto.UserQueryCriteria;
import me.zhengjie.modules.system.service.impl.DeptServiceImpl;
import me.zhengjie.service.LocalStorageService;
import me.zhengjie.service.dto.LocalStorageDto;
import me.zhengjie.service.mapstruct.LocalStorageMapper;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zhengjie.utils.PageResult;
import org.springframework.web.multipart.MultipartFile;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "项目：招标项目")
@RequestMapping("/api/project/application")
public class ProjectApplicationController {

    private final ProjectApplicationService projectApplicationService;
    private final PlanProjectService planProjectService;
    private final LocalStorageService localStorageService;
    private final LocalStorageMapper localStorageMapper;

    private final DeptServiceImpl deptService;
    private final UserService userService;

    @ApiOperation("部门人员查询")
    @GetMapping("deptUsers")
    @PreAuthorize("@el.check('project:application:add','project:application:edit')")
    public ResponseEntity<PageResult<UserDto>> queryDeptUser(UserQueryCriteria criteria){
        UserDto userDto = userService.findById(SecurityUtils.getCurrentUserId());
        Dept dept = deptService.getDeptTop(userDto.getDept().getId());
        criteria.setDeptIds(new HashSet<>(deptService.getDeptChildren(Collections.singletonList(dept))));
        return new ResponseEntity<>(userService.queryAll(criteria,Pageable.unpaged()),HttpStatus.OK);
    }


    @Log("申报项目上传文件")
    @ApiOperation("上传")
    @PostMapping(value = "/upload")
    @PreAuthorize("@el.check('project:application:add','project:application:edit')")
    public ResponseEntity<LocalStorageDto> upload(@RequestBody MultipartFile file, HttpServletRequest request)throws Exception{
        LocalStorage localStorage = localStorageService.create("ProjectApplication", file);
        LocalStorageDto localStorageDto = localStorageMapper.toDto(localStorage);
        return new ResponseEntity<>(localStorageDto,HttpStatus.CREATED);
    }

    @Log("获取可用的上级项目")
    @ApiOperation("储备项目列表")
    @GetMapping(value = "/queryAllAvailablePlanProject")
    @PreAuthorize("@el.check('project:application:add')")
    public ResponseEntity<PageResult<PlanProjectDto>> queryAllAvailablePlanProject(){
        PlanProjectQueryCriteria criteria = new PlanProjectQueryCriteria();
        criteria.setPlanStatus(CollUtil.newArrayList(PlanStatus.PUBLISHED));
        return ResponseEntity.ok(planProjectService.queryAll(criteria,Pageable.unpaged()));
    }

    @Log("导出申报项目数据")
    @ApiOperation("导出")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('project:application:list')")
    public void exportApplication(HttpServletResponse response, ProjectApplicationQueryCriteria criteria) throws IOException {
        projectApplicationService.download(projectApplicationService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询申报项目")
    @ApiOperation("查询")
    @PreAuthorize("@el.check('project:application:list')")
    public ResponseEntity<PageResult<ProjectApplicationDto>> queryApplication(ProjectApplicationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(projectApplicationService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增申报项目")
    @ApiOperation("新增")
    @PreAuthorize("@el.check('project:application:add')")
    public ResponseEntity<Object> createApplication(@Validated @RequestBody ProjectApplicationForm resources){
        projectApplicationService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改申报项目")
    @ApiOperation("修改")
    @PreAuthorize("@el.check('project:application:edit')")
    public ResponseEntity<Object> updateApplication(@Validated @RequestBody ProjectApplicationForm resources){
        projectApplicationService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除申报项目")
    @ApiOperation("删除")
    @PreAuthorize("@el.check('project:application:del')")
    public ResponseEntity<Object> deleteApplication(@RequestBody Long[] ids) {
        projectApplicationService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
