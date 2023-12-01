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
import me.zhengjie.modules.project.domain.ProjectExperience;
import me.zhengjie.modules.project.service.ProjectExperienceService;
import me.zhengjie.modules.project.service.dto.ProjectExperienceQueryCriteria;
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
import me.zhengjie.modules.project.service.dto.ProjectExperienceDto;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-14
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "项目经验管理")
@RequestMapping("/api/project/experience")
public class ProjectExperienceController {

    private final ProjectExperienceService projectExperienceService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('project:experience:list')")
    public void exportProjectExperience(HttpServletResponse response, ProjectExperienceQueryCriteria criteria) throws IOException {
        projectExperienceService.download(projectExperienceService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询项目经验")
    @ApiOperation("查询项目经验")
    @PreAuthorize("@el.check('project:experience:list')")
    public ResponseEntity<PageResult<ProjectExperienceDto>> queryProjectExperience(ProjectExperienceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(projectExperienceService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增项目经验")
    @ApiOperation("新增项目经验")
    @PreAuthorize("@el.check('project:experience:add')")
    public ResponseEntity<Object> createProjectExperience(@Validated @RequestBody ProjectExperience resources){
        projectExperienceService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改项目经验")
    @ApiOperation("修改项目经验")
    @PreAuthorize("@el.check('project:experience:edit')")
    public ResponseEntity<Object> updateProjectExperience(@Validated @RequestBody ProjectExperience resources){
        projectExperienceService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除项目经验")
    @ApiOperation("删除项目经验")
    @PreAuthorize("@el.check('project:experience:del')")
    public ResponseEntity<Object> deleteProjectExperience(@RequestBody Long[] ids) {
        projectExperienceService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
