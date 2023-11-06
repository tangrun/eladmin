package me.zhengjie.modules.registration.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.registration.domain.CompetentOrganizationApply;
import me.zhengjie.modules.registration.service.dto.CompetentOrganizationApplyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Rain
 * @date 2023/11/6 14:22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetentOrganizationApplyMapper extends BaseMapper<CompetentOrganizationApplyDTO,CompetentOrganizationApply> {
}
