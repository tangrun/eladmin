package me.zhengjie.modules.registration.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.registration.domain.Expert;
import me.zhengjie.modules.registration.service.dto.ExpertDTO;
import me.zhengjie.modules.system.domain.Job;
import me.zhengjie.modules.system.service.dto.JobDto;
import me.zhengjie.modules.system.service.mapstruct.DeptMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Rain
 * @date 2023/11/20 10:45
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpertMapper extends BaseMapper<ExpertDTO, Expert> {
}
