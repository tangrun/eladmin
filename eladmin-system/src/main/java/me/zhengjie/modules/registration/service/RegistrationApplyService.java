package me.zhengjie.modules.registration.service;

import cn.hutool.core.bean.BeanUtil;
import me.zhengjie.domain.LocalStorage;
import me.zhengjie.exception.EntityExistException;
import me.zhengjie.modules.registration.domain.CompetentOrganizationApply;
import me.zhengjie.modules.registration.domain.ExpertApply;
import me.zhengjie.modules.registration.domain.SocialOrganizationApply;
import me.zhengjie.modules.registration.repository.CompetentOrganizationApplyRepository;
import me.zhengjie.modules.registration.repository.ExpertApplyRepository;
import me.zhengjie.modules.registration.repository.SocialOrganizationApplyRepository;
import me.zhengjie.modules.registration.service.dto.*;
import me.zhengjie.modules.registration.service.mapstruct.CompetentOrganizationApplyMapper;
import me.zhengjie.modules.registration.service.mapstruct.ExpertApplyMapper;
import me.zhengjie.modules.registration.service.mapstruct.SocialOrganizationApplyMapper;
import me.zhengjie.service.LocalStorageService;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
    private ExpertApplyMapper expertApplyMapper;
    @Resource
    private SocialOrganizationApplyMapper socialOrganizationApplyMapper;
    @Resource
    private CompetentOrganizationApplyMapper competentOrganizationApplyMapper;
    @Resource
    private LocalStorageService localStorageService;

    public void socialApply(SocialOrganizationApplyDTO request) {
        SocialOrganizationApply apply = new SocialOrganizationApply();
        BeanUtil.copyProperties(request, apply, "id");

        if (request.getBusinessLicenseCopy() != null) {
            LocalStorage localStorage = localStorageService.create(null, request.getBusinessLicenseCopy());
            apply.setBusinessLicenseCopy(localStorage);
        }

        socialOrganizationApplyRepository.save(apply);
    }

    public PageResult<SocialOrganizationApplyDTO> socialList(SocialOrganizationQueryCriteria criteria, Pageable pageable) {
        Page<SocialOrganizationApply> page = socialOrganizationApplyRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(apply -> socialOrganizationApplyMapper.toDto(apply)));
    }

    public void expertApply(ExpertApplyDTO request) {
        ExpertApply apply = expertApplyMapper.toEntity(request);
        apply.setId(null);

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

    public PageResult<ExpertApplyDTO> expertList(ExpertApplyQueryCriteria criteria, Pageable pageable) {
        Page<ExpertApply> page = expertApplyRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(apply -> expertApplyMapper.toDto(apply)));
    }

    public PageResult<ExpertApplyDTO> expertHandle(ApplyHandleDTO handleDTO) {
        ExpertApply expertApply = expertApplyRepository.findById(handleDTO.getId()).orElseGet(ExpertApply::new);
        ValidationUtil.isNull(expertApply.getId(), "ExpertApply", "id", handleDTO.getId());

        expertApplyRepository.updateStateAndRemark(handleDTO.getPass() ? 1 : 2, handleDTO.getRemark(), handleDTO.getId());
    }

    public void competentApply(CompetentOrganizationApplyDTO request) {
        CompetentOrganizationApply apply = new CompetentOrganizationApply();
        BeanUtil.copyProperties(request, apply, "id");
        competentOrganizationApplyRepository.save(apply);
    }


    public PageResult<CompetentOrganizationApplyDTO> competentList(CompetentOrganizationQueryCriteria criteria, Pageable pageable) {
        Page<CompetentOrganizationApply> page = competentOrganizationApplyRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(apply -> competentOrganizationApplyMapper.toDto(apply)));
    }
}
