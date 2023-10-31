package me.zhengjie.modules.a.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.rest.AnonymousPostMapping;
import me.zhengjie.modules.a.service.RegistrationApplyService;
import me.zhengjie.modules.a.service.dto.RegistrationApplyDTO;
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
@Api(tags = "注册申请")
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationApplyController {
    @Resource
    private RegistrationApplyService registrationService;

    @ApiOperation("机构注册申请")
    @AnonymousPostMapping(value = "/apply")
    public ResponseEntity<Object> apply(@Valid RegistrationApplyDTO request){
        registrationService.apply(request);
        return ResponseEntity.ok(null);
    }

    @ApiOperation("机构注册申请列表")
    @PreAuthorize("@el.check('user:list')")
    @GetMapping(value = "/list")
    public ResponseEntity<Object> organizationApply(Pageable pageable){

        return null;
    }


}
