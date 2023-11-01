package me.zhengjie.modules.a.service;

import cn.hutool.core.bean.BeanUtil;
import me.zhengjie.domain.LocalStorage;
import me.zhengjie.modules.a.domain.CompetentOrganizationApply;
import me.zhengjie.modules.a.domain.ExpertApply;
import me.zhengjie.modules.a.domain.SocialOrganizationApply;
import me.zhengjie.modules.a.repository.CompetentOrganizationApplyRepository;
import me.zhengjie.modules.a.repository.ExpertApplyRepository;
import me.zhengjie.modules.a.repository.SocialOrganizationApplyRepository;
import me.zhengjie.modules.a.service.dto.CompetentOrganizationApplyDTO;
import me.zhengjie.modules.a.service.dto.ExpertApplyDTO;
import me.zhengjie.modules.a.service.dto.SocialOrganizationApplyDTO;
import me.zhengjie.service.LocalStorageService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Rain
 * @date 2023/10/31 13:14
 */
@Service
public class RegistrationApplyService {
    @Resource
    private ExpertApplyRepository expertApplyRepository;
    @Resource
    private SocialOrganizationApplyRepository socialOrganizationApplyRepository;
    @Resource
    private CompetentOrganizationApplyRepository competentOrganizationApplyRepository;
    @Resource
    private LocalStorageService localStorageService;

    public void socialApply(SocialOrganizationApplyDTO request) {
        SocialOrganizationApply apply = new SocialOrganizationApply();
        BeanUtil.copyProperties(request,apply);

        if (request.getBusinessLicenseCopy() != null) {
            LocalStorage localStorage = localStorageService.create(null, request.getBusinessLicenseCopy());
            apply.setBusinessLicenseCopy(localStorage);
        }

        socialOrganizationApplyRepository.save(apply);
    }

    public void expertApply(ExpertApplyDTO request) {
        ExpertApply apply = new ExpertApply();
        BeanUtil.copyProperties(request,apply);

        if (request.getIdentityFrontCopy() != null) {
            LocalStorage localStorage = localStorageService.create(null, request.getIdentityFrontCopy());
            apply.setIdentityFrontCopy(localStorage);
        }
        if (request.getIdentityBackCopy() != null) {
            LocalStorage localStorage = localStorageService.create(null, request.getIdentityBackCopy());
            apply.setIdentityBackCopy(localStorage);
        }
        if (request.getQualificationCopy() != null) {
            LocalStorage localStorage = localStorageService.create(null, request.getQualificationCopy());
            apply.setQualificationCopy(localStorage);
        }

        expertApplyRepository.save(apply);
    }

    public void expertList(Pageable pageable) {

    }

    public void competentApply(CompetentOrganizationApplyDTO request) {
        CompetentOrganizationApply apply = new CompetentOrganizationApply();
        BeanUtil.copyProperties(request,apply);
        competentOrganizationApplyRepository.save(apply);
    }


}
