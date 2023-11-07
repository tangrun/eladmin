package me.zhengjie.modules.registration.service.vo;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * @author Rain
 * @date 2023/11/7 9:17
 */
public class CompetentOrganizationApplyVO {


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
    private Integer administrativeLevel;
    private String province;
    private String city;
    private String county;
    private String street;
    private String community;

    /**
     * 机构名称
     */
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
