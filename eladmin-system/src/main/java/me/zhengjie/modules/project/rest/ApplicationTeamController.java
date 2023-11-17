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
import me.zhengjie.modules.project.domain.ApplicationTeam;
import me.zhengjie.modules.project.service.ApplicationTeamService;
import me.zhengjie.modules.project.service.dto.ApplicationTeamQueryCriteria;
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
import me.zhengjie.modules.project.service.dto.ApplicationTeamDto;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-15
 *@desc 项目团队管理
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "team管理")
@RequestMapping("/api/applicationTeam")
public class ApplicationTeamController {

    private final ApplicationTeamService applicationTeamService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('applicationTeam:list')")
    public void exportApplicationTeam(HttpServletResponse response, ApplicationTeamQueryCriteria criteria) throws IOException {
        applicationTeamService.download(applicationTeamService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询team")
    @ApiOperation("查询team")
    @PreAuthorize("@el.check('applicationTeam:list')")
    public ResponseEntity<PageResult<ApplicationTeamDto>> queryApplicationTeam(ApplicationTeamQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(applicationTeamService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增team")
    @ApiOperation("新增team")
    @PreAuthorize("@el.check('applicationTeam:add')")
    public ResponseEntity<Object> createApplicationTeam(@Validated @RequestBody ApplicationTeam resources){
        applicationTeamService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改team")
    @ApiOperation("修改team")
    @PreAuthorize("@el.check('applicationTeam:edit')")
    public ResponseEntity<Object> updateApplicationTeam(@Validated @RequestBody ApplicationTeam resources){
        applicationTeamService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除team")
    @ApiOperation("删除team")
    @PreAuthorize("@el.check('applicationTeam:del')")
    public ResponseEntity<Object> deleteApplicationTeam(@RequestBody Long[] ids) {
        applicationTeamService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}