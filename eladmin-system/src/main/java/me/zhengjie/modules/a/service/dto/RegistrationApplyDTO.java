package me.zhengjie.modules.a.service.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * @author Rain
 * @date 2023/10/31 13:22
 */
public class RegistrationApplyDTO {

    @NotNull
    private Integer type;

    /**
     * 行政等级
     * 1 国家
     * 2 省
     * 3 市
     * 4 县
     * 5 街道
     * 6 社区
     */
    @NotNull
    private Integer administrativeLevel;
    private String province;
    private String city;
    private String county;
    private String street;
    private String community;

    /**
     * 机构名称
     */
    @NotNull
    private String associationName;
    /**
     * 机构备注 简介
     */
    private String associationDescription;
    /**
     * 机构电话
     */
    private String associationPhone;
    /**
     * 机构邮箱地址
     */
    private String associationEmail;
    /**
     * 机构社会信用代码
     */
    private String associationSocialCreditCode;
    /**
     * 机构传真
     */
    private String associationFax;
    /**
     * 机构地址
     */
    private String associationAddress;
    /**
     * 机构法人
     */
    private String associationCorporation;


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

}
