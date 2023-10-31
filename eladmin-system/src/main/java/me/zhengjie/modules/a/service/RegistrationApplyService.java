package me.zhengjie.modules.a.service;

import me.zhengjie.modules.a.repository.RegistrationRepository;
import me.zhengjie.modules.a.service.dto.RegistrationApplyDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Rain
 * @date 2023/10/31 13:14
 */
@Service
public class RegistrationApplyService {
    @Resource
    private RegistrationRepository registrationRepository;

    public void apply(RegistrationApplyDTO request){

    }

}
