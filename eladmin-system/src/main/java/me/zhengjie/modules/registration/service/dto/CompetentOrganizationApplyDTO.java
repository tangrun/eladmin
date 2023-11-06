package me.zhengjie.modules.registration.service.dto;

import javax.validation.constraints.NotNull;

/**
 * @author Rain
 * @date 2023/10/31 13:22
 */
public class CompetentOrganizationApplyDTO {

    private Long id;

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
    private String name;
    /**
     * 机构备注 简介
     */
    private String description;
    /**
     * 机构电话
     */
    private String phone;
    /**
     * 机构邮箱地址
     */
    private String email;
    /**
     * 机构传真
     */
    private String fax;
    /**
     * 机构地址
     */
    private String address;


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
