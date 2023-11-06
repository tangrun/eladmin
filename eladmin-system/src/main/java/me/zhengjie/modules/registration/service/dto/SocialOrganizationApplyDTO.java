package me.zhengjie.modules.registration.service.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author Rain
 * @date 2023/10/31 13:22
 */
@Data
public class SocialOrganizationApplyDTO {

    private Long id;

    /**
     * 机构名称
     */
    @NotNull
    private String name;
    /**
     * 机构备注 简介
     */
    private String description;
    /**
     * 机构社会信用代码
     */
    private String creditCode;


    /**
     * 管理员名字
     */
    @NotNull
    private String managerName;
    /**
     * 管理员电话
     */
    @NotNull
    private String managerPhone;
    /**
     * 管理员邮箱
     */
    @NotNull
    private String managerEmail;


    /**
     * 营业执照复印件
     */
    @NotNull
    private MultipartFile businessLicenseCopy;

}
