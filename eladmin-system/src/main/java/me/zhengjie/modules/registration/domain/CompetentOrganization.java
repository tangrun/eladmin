package me.zhengjie.modules.registration.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;
import me.zhengjie.modules.system.domain.Dept;

import javax.persistence.*;

/**
 * 主管机构注册申请
 * @author Rain
 * @date 2023/10/30 17:06
 */
@Entity
@Getter
@Setter
@Table(name = "sys_user_competent")
public class CompetentOrganization extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "dept_id")
    @ApiModelProperty(value = "部门")
    private Dept dept;

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
     * 机构电话
     */
    @Column(name = "phone")
    private String phone;
    /**
     * 机构邮箱地址
     */
    @Column(name = "email")
    private String email;
    /**
     * 机构传真
     */
    @Column(name = "fax")
    private String fax;
    /**
     * 机构地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 管理员名字
     */
    @Column(name = "manager_name")
    private String managerName;
    /**
     * 管理员电话
     */
    @Column(name = "manager_phone")
    private String managerPhone;
    /**
     * 管理员邮箱
     */
    @Column(name = "manager_email")
    private String managerEmail;

}
