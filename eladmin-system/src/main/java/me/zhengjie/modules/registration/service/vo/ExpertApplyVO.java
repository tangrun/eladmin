package me.zhengjie.modules.registration.service.vo;

import lombok.Data;
import me.zhengjie.service.dto.LocalStorageDto;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 专家注册申请
 *
 * @author Rain
 * @date 2023/11/1 15:47
 */
@Data
public class ExpertApplyVO {

    private Long id;

    /**
     * 姓名
     */

    private String name;
    /**
     * 性别
     * 1男
     * 2女
     */

    private Integer sex;
    /**
     * 单位
     */

    private String company;
    /**
     * 资质
     */

    private String qualification;
    /**
     * 政治面貌
     */

    private String political;
    /**
     * 身份证
     */

    private String identity;
    /**
     * 电话
     */

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

    private LocalStorageDto identityFrontCopy;
    /**
     * 身份证复印件背面
     */

    private LocalStorageDto identityBackCopy;
    /**
     * 资质扫描件
     */

    private LocalStorageDto qualificationCopy;

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
