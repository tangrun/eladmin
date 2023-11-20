package me.zhengjie.modules.registration.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.registration.domain.CompetentOrganization;
import me.zhengjie.modules.registration.service.dto.CompetentOrganizationApplyDTO;
import me.zhengjie.modules.registration.service.dto.CompetentOrganizationDTO;
import me.zhengjie.modules.system.domain.Job;
import me.zhengjie.modules.system.service.dto.JobDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Rain
 * @date 2023/11/20 10:45
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetentOrganizationMapper extends BaseMapper<CompetentOrganizationDTO, CompetentOrganization> {
}
