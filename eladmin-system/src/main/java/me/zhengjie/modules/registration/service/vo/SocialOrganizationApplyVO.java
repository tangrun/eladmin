package me.zhengjie.modules.registration.service.vo;

import lombok.Data;
import me.zhengjie.service.dto.LocalStorageDto;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author Rain
 * @date 2023/10/31 13:22
 */
@Data
public class SocialOrganizationApplyVO {

    private Long id;

    /**
     * 机构名称
     */

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

    private String managerName;
    /**
     * 管理员电话
     */

    private String managerPhone;
    /**
     * 管理员邮箱
     */

    private String managerEmail;


    /**
     * 营业执照复印件
     */

    private LocalStorageDto businessLicenseCopyFile;


    /**
     * 0 提交
     * 1 通过
     * 2 拒绝
     */
    private Integer state;
    /**
     * 审核备注
     */
    private String remark;
}
