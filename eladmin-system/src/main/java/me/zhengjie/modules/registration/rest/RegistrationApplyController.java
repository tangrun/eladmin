package me.zhengjie.modules.registration.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.rest.AnonymousPostMapping;
import me.zhengjie.modules.registration.service.RegistrationApplyService;
import me.zhengjie.modules.registration.service.dto.*;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Rain
 * @date 2023/10/30 18:00
 */
@Api(tags = "注册")
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationApplyController {
    @Resource
    private RegistrationApplyService registrationService;

    @ApiOperation("【专家】注册申请")
    @AnonymousPostMapping(value = "/expert/apply")
    public ResponseEntity<Void> expertApply(@Valid ExpertApplyDTO request){
        registrationService.expertApply(request);
        return ResponseEntity.ok(null);
    }


    @ApiOperation("【专家】注册申请列表")
    @PreAuthorize("@el.check('registration:list')")
    @GetMapping(value = "/expert/list")
    public ResponseEntity<PageResult<ExpertApplyDTO>> expertList(ExpertApplyQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(registrationService.expertList(criteria, pageable),HttpStatus.OK);
    }

    @ApiOperation("【专家】注册申请审核")
    @PreAuthorize("@el.check('registration:handle')")
    @GetMapping(value = "/handle")
    public ResponseEntity<PageResult<ExpertApplyDTO>> expertHandle(ExpertApplyQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(registrationService.expertList(criteria, pageable),HttpStatus.OK);
    }



    @ApiOperation("【主管机构】注册申请")
    @AnonymousPostMapping(value = "/competent/apply")
    public ResponseEntity<Void> competentApply(@Valid CompetentOrganizationApplyDTO request){
        registrationService.competentApply(request);
        return ResponseEntity.ok(null);
    }


    @ApiOperation("【主管机构】注册申请列表")
    @PreAuthorize("@el.check('registration:list')")
    @GetMapping(value = "/competent/list")
    public ResponseEntity<PageResult<CompetentOrganizationApplyDTO>> competentList(CompetentOrganizationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(registrationService.competentList(criteria,pageable), HttpStatus.OK);
    }

    @ApiOperation("【社会组织】注册申请")
    @AnonymousPostMapping(value = "/social/apply")
    public ResponseEntity<Void> socialApply(@Valid SocialOrganizationApplyDTO request){
        registrationService.socialApply(request);
        return ResponseEntity.ok(null);
    }


    @ApiOperation("【社会组织】注册申请列表")
    @PreAuthorize("@el.check('registration:list')")
    @GetMapping(value = "/social/list")
    public ResponseEntity<PageResult<SocialOrganizationApplyDTO>> socialList(SocialOrganizationQueryCriteria criteria,Pageable pageable){
        return new ResponseEntity<>(registrationService.socialList(criteria,pageable), HttpStatus.OK);
    }



}
