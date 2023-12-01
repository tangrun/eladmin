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
import me.zhengjie.modules.project.service.PlanProjectService;
import me.zhengjie.modules.project.service.dto.PlanProjectForm;
import me.zhengjie.modules.project.service.dto.PlanProjectPublishReviewForm;
import me.zhengjie.modules.project.service.dto.PlanProjectQueryCriteria;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.service.DeptService;
import me.zhengjie.modules.system.service.UserService;
import me.zhengjie.modules.system.service.dto.DeptDto;
import me.zhengjie.modules.system.service.dto.UserDto;
import me.zhengjie.modules.system.service.dto.UserQueryCriteria;
import me.zhengjie.modules.system.service.dto.UserSmallDto;
import me.zhengjie.modules.system.service.impl.DeptServiceImpl;
import me.zhengjie.modules.system.service.impl.UserServiceImpl;
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
import me.zhengjie.modules.project.service.dto.PlanProjectDto;
import org.springframework.web.multipart.MultipartFile;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "项目：储备项目")
@RequestMapping("/api/project/plan")
public class PlanProjectController {

    private final PlanProjectService planProjectService;
    private final LocalStorageService localStorageService;
    private final LocalStorageMapper localStorageMapper;
    private final DeptServiceImpl deptService;
    private final UserService userService;


    @ApiOperation("部门人员查询")
    @GetMapping("deptUsers")
    @PreAuthorize("@el.check('project:plan:add','project:plan:edit')")
    public ResponseEntity<PageResult<UserDto>> queryDeptUser(UserQueryCriteria criteria){
        UserDto userDto = userService.findById(SecurityUtils.getCurrentUserId());
        Dept dept = deptService.getDeptTop(userDto.getDept().getId());
        criteria.setDeptIds(new HashSet<>(deptService.getDeptChildren(Collections.singletonList(dept))));
        return new ResponseEntity<>(userService.queryAll(criteria,Pageable.unpaged()),HttpStatus.OK);
    }


    @Log("项目计划上传文件")
    @ApiOperation("上传")
    @PostMapping(value = "/upload")
    @PreAuthorize("@el.check('project:plan:add','project:plan:edit')")
    public ResponseEntity<LocalStorageDto> upload(@RequestBody MultipartFile file, HttpServletRequest request)throws Exception{
        LocalStorage localStorage = localStorageService.create("PlanProject", file);
        LocalStorageDto localStorageDto = localStorageMapper.toDto(localStorage);
        return new ResponseEntity<>(localStorageDto,HttpStatus.CREATED);
    }

    @Log("导出数据")
    @ApiOperation("导出")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('project:plan:list')")
    public void exportProjectPlan(HttpServletResponse response, PlanProjectQueryCriteria criteria) throws IOException {
        planProjectService.download(planProjectService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询储备项目")
    @ApiOperation("查询")
    @PreAuthorize("@el.check('project:plan:list')")
    public ResponseEntity<PageResult<PlanProjectDto>> queryProjectPlan(PlanProjectQueryCriteria criteria, Pageable pageable){
        // 不是管理机构的 不能看到草稿的
        if (!SecurityUtils.getCurrentUserPermission().contains("project:plan:list")) {
//            criteria.setDraft(false);
        }
        return new ResponseEntity<>(planProjectService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping("/save")
    @Log("储备项目保存草稿")
    @ApiOperation("保存")
    @PreAuthorize("@el.check('project:plan:add')")
    public ResponseEntity<Void> save(@RequestBody PlanProjectForm resources){
        planProjectService.saveDraft(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/publish")
    @Log("发布储备项目")
    @ApiOperation("发布")
    @PreAuthorize("@el.check('project:plan:add')")
    public ResponseEntity<Void> publish(@RequestBody PlanProjectForm resources){
        planProjectService.publish(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/review")
    @Log("审核储备项目")
    @ApiOperation("审核")
    @PreAuthorize("@el.check('project:plan:review')")
    public ResponseEntity<Void> review(@RequestBody PlanProjectPublishReviewForm resources){
        planProjectService.review(resources);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @Log("修改储备项目")
    @ApiOperation("修改")
    @PreAuthorize("@el.check('project:plan:edit')")
    public ResponseEntity<Void> updateProjectPlan( @RequestBody @Validated(BaseEntity.Update.class) PlanProjectForm resources){
        planProjectService.edit(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除储备项目")
    @ApiOperation("删除")
    @PreAuthorize("@el.check('project:plan:del')")
    public ResponseEntity<Void> deleteProjectPlan(@RequestBody Long[] ids) {
        planProjectService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
