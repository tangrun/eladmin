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
import me.zhengjie.modules.project.domain.Change;
import me.zhengjie.modules.project.service.ChangeService;
import me.zhengjie.modules.project.service.dto.ChangeQueryCriteria;
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
import me.zhengjie.modules.project.service.dto.ChangeDto;

/**
* @website https://eladmin.vip
* @author hb
* @date 2023-11-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "change管理")
@RequestMapping("/api/change")
public class ChangeController {

    private final ChangeService changeService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('change:list')")
    public void exportChange(HttpServletResponse response, ChangeQueryCriteria criteria) throws IOException {
        changeService.download(changeService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询change")
    @ApiOperation("查询change")
    @PreAuthorize("@el.check('change:list')")
    public ResponseEntity<PageResult<ChangeDto>> queryChange(ChangeQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(changeService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增change")
    @ApiOperation("新增change")
    @PreAuthorize("@el.check('change:add')")
    public ResponseEntity<Object> createChange(@Validated @RequestBody Change resources){
        changeService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改change")
    @ApiOperation("修改change")
    @PreAuthorize("@el.check('change:edit')")
    public ResponseEntity<Object> updateChange(@Validated @RequestBody Change resources){
        changeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除change")
    @ApiOperation("删除change")
    @PreAuthorize("@el.check('change:del')")
    public ResponseEntity<Object> deleteChange(@RequestBody Long[] ids) {
        changeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}