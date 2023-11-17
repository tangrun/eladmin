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
import me.zhengjie.modules.project.domain.ApplicationBudget;
import me.zhengjie.modules.project.service.ApplicationBudgetService;
import me.zhengjie.modules.project.service.dto.ApplicationBudgetQueryCriteria;
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
import me.zhengjie.modules.project.service.dto.ApplicationBudgetDto;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "budget管理")
@RequestMapping("/api/applicationBudget")
public class ApplicationBudgetController {

    private final ApplicationBudgetService applicationBudgetService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('applicationBudget:list')")
    public void exportApplicationBudget(HttpServletResponse response, ApplicationBudgetQueryCriteria criteria) throws IOException {
        applicationBudgetService.download(applicationBudgetService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询budget")
    @ApiOperation("查询budget")
    @PreAuthorize("@el.check('applicationBudget:list')")
    public ResponseEntity<PageResult<ApplicationBudgetDto>> queryApplicationBudget(ApplicationBudgetQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(applicationBudgetService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增budget")
    @ApiOperation("新增budget")
    @PreAuthorize("@el.check('applicationBudget:add')")
    public ResponseEntity<Object> createApplicationBudget(@Validated @RequestBody ApplicationBudget resources){
        applicationBudgetService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改budget")
    @ApiOperation("修改budget")
    @PreAuthorize("@el.check('applicationBudget:edit')")
    public ResponseEntity<Object> updateApplicationBudget(@Validated @RequestBody ApplicationBudget resources){
        applicationBudgetService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除budget")
    @ApiOperation("删除budget")
    @PreAuthorize("@el.check('applicationBudget:del')")
    public ResponseEntity<Object> deleteApplicationBudget(@RequestBody Long[] ids) {
        applicationBudgetService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}