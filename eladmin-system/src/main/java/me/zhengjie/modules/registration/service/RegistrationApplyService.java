package me.zhengjie.modules.registration.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.util.IdUtil;
import me.zhengjie.domain.LocalStorage;
import me.zhengjie.exception.EntityExistException;
import me.zhengjie.modules.registration.domain.CompetentOrganizationApply;
import me.zhengjie.modules.registration.domain.ExpertApply;
import me.zhengjie.modules.registration.domain.SocialOrganizationApply;
import me.zhengjie.modules.registration.repository.CompetentOrganizationApplyRepository;
import me.zhengjie.modules.registration.repository.ExpertApplyRepository;
import me.zhengjie.modules.registration.repository.SocialOrganizationApplyRepository;
import me.zhengjie.modules.registration.service.dto.*;
import me.zhengjie.modules.registration.service.vo.CompetentOrganizationApplyVO;
import me.zhengjie.modules.registration.service.vo.ExpertApplyVO;
import me.zhengjie.modules.registration.service.vo.SocialOrganizationApplyVO;
import me.zhengjie.modules.system.domain.Role;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.repository.RoleRepository;
import me.zhengjie.modules.system.repository.UserRepository;
import me.zhengjie.modules.system.service.RoleService;
import me.zhengjie.modules.system.service.UserService;
import me.zhengjie.service.LocalStorageService;
import me.zhengjie.service.dto.LocalStorageDto;
import me.zhengjie.service.mapstruct.LocalStorageMapperImpl;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.enums.DataScopeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Collections;
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
    private LocalStorageService localStorageService;

    @Resource
    private UserRepository userRepository;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private RoleService roleService;

    public RegistrationApplyService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void socialApply(SocialOrganizationApplyDTO request) {
        SocialOrganizationApply apply = new SocialOrganizationApply();
        BeanUtil.copyProperties(request, apply, "id");
        apply.setState(0);

        if (request.getBusinessLicenseCopyFile() != null) {
            LocalStorage localStorage = localStorageService.create(null, request.getBusinessLicenseCopyFile());
            apply.setBusinessLicenseCopy(localStorage);
        }

        socialOrganizationApplyRepository.save(apply);
    }

    public PageResult<SocialOrganizationApplyVO> socialList(SocialOrganizationQueryCriteria criteria, Pageable pageable) {
        Page<SocialOrganizationApply> page = socialOrganizationApplyRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(apply -> {
            SocialOrganizationApplyVO vo = new SocialOrganizationApplyVO();
            BeanUtil.copyProperties(apply, vo);
            return vo;
        }));
    }

    @Transactional(rollbackFor = Exception.class)
    public void socialHandle(ApplyHandleDTO handleDTO) {
        SocialOrganizationApply apply = socialOrganizationApplyRepository.findById(handleDTO.getId()).orElseGet(SocialOrganizationApply::new);
        ValidationUtil.isNull(apply.getId(), "SocialOrganizationApply", "id", handleDTO.getId());
        socialOrganizationApplyRepository.updateStateAndRemark(handleDTO.getPass() ? 1 : 2, handleDTO.getRemark(), handleDTO.getId());
    }

    public void expertApply(ExpertApplyDTO request) {
        ExpertApply apply = new ExpertApply();
        BeanUtil.copyProperties(request, apply, "id");
        apply.setState(0);

        if (request.getIdentityFrontCopyFile() != null) {
            LocalStorage localStorage = localStorageService.create(null, request.getIdentityFrontCopyFile());
            apply.setIdentityFrontCopy(localStorage);
        }
        if (request.getIdentityBackCopyFile() != null) {
            LocalStorage localStorage = localStorageService.create(null, request.getIdentityBackCopyFile());
            apply.setIdentityBackCopy(localStorage);
        }
        if (request.getQualificationCopyFile() != null) {
            LocalStorage localStorage = localStorageService.create(null, request.getQualificationCopyFile());
            apply.setQualificationCopy(localStorage);
        }

        expertApplyRepository.save(apply);
    }

    public PageResult<ExpertApplyVO> expertList(ExpertApplyQueryCriteria criteria, Pageable pageable) {

        Page<ExpertApply> page = expertApplyRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(apply -> {
            ExpertApplyVO vo = new ExpertApplyVO();
            BeanUtil.copyProperties(apply, vo);

            return vo;
        }));
    }

    @Transactional(rollbackFor = Exception.class)
    public void expertHandle(ApplyHandleDTO handleDTO) {
        ExpertApply apply = expertApplyRepository.findById(handleDTO.getId()).orElseGet(ExpertApply::new);
        ValidationUtil.isNull(apply.getId(), "ExpertApply", "id", handleDTO.getId());
        expertApplyRepository.updateStateAndRemark(handleDTO.getPass() ? 1 : 2, handleDTO.getRemark(), handleDTO.getId());
    }

    public void competentApply(CompetentOrganizationApplyDTO request) {
        User user = userRepository.findByEmail(request.getManagerEmail());
        Assert.isNull(user, "管理帐号已存在");
        user = userRepository.findByPhone(request.getManagerPhone());
        Assert.isNull(user, "管理帐号已存在");

        CompetentOrganizationApply apply = new CompetentOrganizationApply();
        BeanUtil.copyProperties(request, apply);
        apply.setState(0);
        competentOrganizationApplyRepository.save(apply);
    }


    public PageResult<CompetentOrganizationApplyVO> competentList(CompetentOrganizationQueryCriteria criteria, Pageable pageable) {
        Page<CompetentOrganizationApply> page = competentOrganizationApplyRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(apply -> {
            CompetentOrganizationApplyVO vo = new CompetentOrganizationApplyVO();
            BeanUtil.copyProperties(apply, vo);
            return vo;
        }));
    }

    @Transactional(rollbackFor = Exception.class)
    public void competentHandle(ApplyHandleDTO handleDTO) {
        CompetentOrganizationApply apply = competentOrganizationApplyRepository.findById(handleDTO.getId()).orElseGet(CompetentOrganizationApply::new);
        ValidationUtil.isNull(apply.getId(), "CompetentOrganizationApply", "id", handleDTO.getId());
        competentOrganizationApplyRepository.updateStateAndRemark(handleDTO.getPass() ? 1 : 2, handleDTO.getRemark(), handleDTO.getId());
        if (!handleDTO.getPass()) {
            return;
        }

        User user = new User();
        user.setNickName(apply.getManagerName());
        user.setPhone(apply.getManagerPhone());
        user.setEmail(apply.getManagerEmail());
        user.setPassword(passwordEncoder.encode("123456"));
        user.setUsername(IdUtil.nanoId());
        user.setEnabled(true);
        Role role = roleRepository.findByName("主管机构");
        if (role == null) {
            role = new Role();
            role.setName("主管机构");
            roleRepository.saveAndFlush(role);
        }
        user.setRoles(Collections.singleton(role));
    }
}
