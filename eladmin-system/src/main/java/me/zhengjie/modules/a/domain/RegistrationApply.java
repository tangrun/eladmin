package me.zhengjie.modules.a.domain;

import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 主管机构注册申请
 * @author Rain
 * @date 2023/10/30 17:06
 */
@Entity
@Getter
@Setter
@Table(name = "sys_registration_apply")
public class RegistrationApply {

    @Id
    @Column(name = "id")
    @NotNull(groups = BaseEntity.Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 1 主管机构
     * 2 社会组织
     */
    @Column(name = "type")
    @NotNull(groups = BaseEntity.Create.class)
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
    @Column(name = "administrative_level")
    private Integer administrativeLevel;
    @Column(name = "administrative_area_province")
    private String province;
    @Column(name = "administrative_area_city")
    private String city;
    @Column(name = "administrative_area_county")
    private String county;
    @Column(name = "administrative_area_street")
    private String street;
    @Column(name = "administrative_area_community")
    private String community;

    /**
     * 机构名称
     */
    @Column(name = "association_name")
    private String associationName;
    /**
     * 机构备注 简介
     */
    @Column(name = "association_description")
    private String associationDescription;
    /**
     * 机构电话
     */
    @Column(name = "association_phone")
    private String associationPhone;
    /**
     * 机构邮箱地址
     */
    @Column(name = "association_email")
    private String associationEmail;
    /**
     * 机构社会信用代码
     */
    @Column(name = "association_social_credit_code")
    private String associationSocialCreditCode;
    /**
     * 机构传真
     */
    @Column(name = "association_fax")
    private String associationFax;
    /**
     * 机构地址
     */
    @Column(name = "association_address")
    private String associationAddress;
    /**
     * 机构法人
     */
    @Column(name = "association_corporation")
    private String associationCorporation;


    /**
     * 管理员名字
     */
    @Column(name = "manager_name")
    @NotNull
    private String managerName;
    /**
     * 管理员电话
     */
    @Column(name = "manager_phone")
    @NotNull
    private String managerPhone;
    /**
     * 管理员邮箱
     */
    @Column(name = "manager_email")
    @NotNull
    private String managerEmail;

}
