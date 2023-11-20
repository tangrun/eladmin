package me.zhengjie.modules.registration.service.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 专家注册申请
 * @author Rain
 * @date 2023/11/1 15:47
 */
@Data
public class ExpertDTO {


    /**
     * 姓名
     */
    @NotBlank
    private String name;
    /**
     * 性别
     * 1男
     * 2女
     */
    @NotNull
    private Integer sex;
    /**
     * 单位
     */
    @NotBlank
    private String company;
    /**
     * 资质
     */
    @NotBlank
    private String qualification;
    /**
     * 政治面貌
     */
    @NotBlank
    private String political;
    /**
     * 身份证
     */
    @NotBlank
    private String identity;
    /**
     * 电话
     */
    @NotBlank
    private String mobile;
    /**
     * 银行账户
     */
    private String bankAccountNumber;
    /**
     * 开户银行
     */
    private String depositBank;
    /**
     * 个人简介
     */
    private String personalProfile;
    /**
     * 身份证复印件正面
     */
    @NotNull
    private MultipartFile identityFrontCopyFile;
    /**
     * 身份证复印件背面
     */
    @NotNull
    private MultipartFile identityBackCopyFile;
    /**
     * 资质扫描件
     */
    @NotNull
    private MultipartFile qualificationCopyFile;

}
