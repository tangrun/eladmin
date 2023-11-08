package me.zhengjie.modules.registration.domain;

import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;
import me.zhengjie.domain.LocalStorage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 主管机构注册申请
 *
 * @author Rain
 * @date 2023/10/30 17:06
 */
@Entity
@Getter
@Setter
@Table(name = "sys_user_social")
public class SocialOrganization extends BaseEntity {

    @Id
    @Column(name = "id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "administrative_level")
    private Integer administrativeLevel;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;
    @Column(name = "county")
    private String county;
    @Column(name = "street")
    private String street;
    @Column(name = "community")
    private String community;


    /**
     * 机构名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 机构备注 简介
     */
    @Column(name = "description")
    private String description;
    /**
     * 机构社会信用代码
     */
    @Column(name = "credit_code")
    private String creditCode;


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

    /**
     * 资质扫描件
     */
    @OneToOne()
    @JoinColumn(name = "business_license_id", referencedColumnName = "storage_id")
    private LocalStorage businessLicenseCopy;
}
