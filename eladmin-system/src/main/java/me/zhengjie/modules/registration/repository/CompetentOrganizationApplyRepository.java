package me.zhengjie.modules.registration.repository;

import me.zhengjie.modules.registration.domain.CompetentOrganizationApply;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * @author Rain
 * @date 2023/10/31 13:14
 */
@Repository
public interface CompetentOrganizationApplyRepository extends JpaRepositoryImplementation<CompetentOrganizationApply,Long> {
    @Modifying
    @Query(value = " update CompetentOrganizationApply set state = :state, remark = :remark where id = :id")
    void updateStateAndRemark(Integer state,String remark, Long id);
}
