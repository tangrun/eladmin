package me.zhengjie.modules.a.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.rest.AnonymousPostMapping;
import me.zhengjie.modules.a.service.RegistrationApplyService;
import me.zhengjie.modules.a.service.dto.CompetentOrganizationApplyDTO;
import me.zhengjie.modules.a.service.dto.ExpertApplyDTO;
import me.zhengjie.modules.a.service.dto.SocialOrganizationApplyDTO;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Object> expertApply(@Valid ExpertApplyDTO request){
        registrationService.expertApply(request);
        return ResponseEntity.ok(null);
    }


    @ApiOperation("【专家】注册申请列表")
    @PreAuthorize("@el.check('registration:list')")
    @GetMapping(value = "/expert/list")
    public ResponseEntity<Object> expertList(Pageable pageable){
        registrationService.expertList(pageable);
        return null;
    }


    @ApiOperation("【主管机构】注册申请")
    @AnonymousPostMapping(value = "/competent/apply")
    public ResponseEntity<Object> competentApply(@Valid CompetentOrganizationApplyDTO request){
        registrationService.competentApply(request);
        return ResponseEntity.ok(null);
    }


    @ApiOperation("【主管机构】注册申请列表")
    @PreAuthorize("@el.check('registration:list')")
    @GetMapping(value = "/competent/list")
    public ResponseEntity<Object> competentList(Pageable pageable){

        return null;
    }

    @ApiOperation("【社会组织】注册申请")
    @AnonymousPostMapping(value = "/social/apply")
    public ResponseEntity<Object> socialApply(@Valid SocialOrganizationApplyDTO request){
        registrationService.socialApply(request);
        return ResponseEntity.ok(null);
    }


    @ApiOperation("【社会组织】注册申请列表")
    @PreAuthorize("@el.check('registration:list')")
    @GetMapping(value = "/social/list")
    public ResponseEntity<Object> socialList(Pageable pageable){

        return null;
    }



}
