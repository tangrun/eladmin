package me.zhengjie.modules.registration.domain;

import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;
import me.zhengjie.domain.LocalStorage;

import javax.persistence.*;

/**
 * 专家注册申请
 * @author Rain
 * @date 2023/10/30 17:06
 */
@Entity
@Getter
@Setter
@Table(name = "reg_expert_apply")
public class ExpertApply  extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;
    /**
     * 性别
     * 1男
     * 2女
     */
    @Column(name = "sex")
    private Integer sex;
    /**
     * 单位
     */
    @Column(name = "company")
    private String company;
    /**
     * 资质
     */
    @Column(name = "qualification")
    private String qualification;
    /**
     * 政治面貌
     */
    @Column(name = "political")
    private String political;
    /**
     * 身份证
     */
    @Column(name = "identity")
    private String identity;
    /**
     * 电话
     */
    @Column(name = "mobile")
    private String mobile;
    /**
     * 银行账户
     */
    @Column(name = "bank_account_number")
    private String bankAccountNumber;
    /**
     * 开户银行
     */
    @Column(name = "deposit_bank")
    private String depositBank;
    /**
     * 个人简介
     */
    @Column(name = "personal_profile")
    private String personalProfile;
    /**
     * 身份证复印件正面
     */
    @OneToOne()
    @JoinColumn(name = "identity_front_id",referencedColumnName = "storage_id")
    private LocalStorage identityFrontCopy;
    /**
     * 身份证复印件背面
     */
    @OneToOne()
    @JoinColumn(name = "identity_back_id",referencedColumnName = "storage_id")
    private LocalStorage identityBackCopy;
    /**
     * 资质扫描件
     */
    @OneToOne()
    @JoinColumn(name = "qualification_id",referencedColumnName = "storage_id")
    private LocalStorage qualificationCopy;

    @Column(name = "state")
    private Integer state;

    @Column(name = "remark")
    private String remark;

}
