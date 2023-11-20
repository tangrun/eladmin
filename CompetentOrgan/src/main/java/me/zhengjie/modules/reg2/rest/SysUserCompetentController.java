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
package me.zhengjie.modules.reg2.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.reg2.domain.SysUserCompetent;
import me.zhengjie.modules.reg2.service.SysUserCompetentService;
import me.zhengjie.modules.reg2.service.dto.SysUserCompetentQueryCriteria;
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
import me.zhengjie.modules.reg2.service.dto.SysUserCompetentDto;

/**
* @website https://eladmin.vip
* @author rain
* @date 2023-11-20
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "主管机构信息管理")
@RequestMapping("/api/sysUserCompetent")
public class SysUserCompetentController {

    private final SysUserCompetentService sysUserCompetentService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('sysUserCompetent:list')")
    public void exportSysUserCompetent(HttpServletResponse response, SysUserCompetentQueryCriteria criteria) throws IOException {
        sysUserCompetentService.download(sysUserCompetentService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询主管机构信息")
    @ApiOperation("查询主管机构信息")
    @PreAuthorize("@el.check('sysUserCompetent:list')")
    public ResponseEntity<PageResult<SysUserCompetentDto>> querySysUserCompetent(SysUserCompetentQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(sysUserCompetentService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增主管机构信息")
    @ApiOperation("新增主管机构信息")
    @PreAuthorize("@el.check('sysUserCompetent:add')")
    public ResponseEntity<Object> createSysUserCompetent(@Validated @RequestBody SysUserCompetent resources){
        sysUserCompetentService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改主管机构信息")
    @ApiOperation("修改主管机构信息")
    @PreAuthorize("@el.check('sysUserCompetent:edit')")
    public ResponseEntity<Object> updateSysUserCompetent(@Validated @RequestBody SysUserCompetent resources){
        sysUserCompetentService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除主管机构信息")
    @ApiOperation("删除主管机构信息")
    @PreAuthorize("@el.check('sysUserCompetent:del')")
    public ResponseEntity<Object> deleteSysUserCompetent(@RequestBody Long[] ids) {
        sysUserCompetentService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}