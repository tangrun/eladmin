package me.zhengjie.modules.a.repository;

import me.zhengjie.modules.a.domain.SocialOrganizationApply;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * @author Rain
 * @date 2023/10/31 13:14
 */
@Repository
public interface SocialOrganizationApplyRepository extends JpaRepositoryImplementation<SocialOrganizationApply,Long> {
}
