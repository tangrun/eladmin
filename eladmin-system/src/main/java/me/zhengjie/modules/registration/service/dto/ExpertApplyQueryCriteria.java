package me.zhengjie.modules.registration.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Rain
 * @date 2023/11/6 9:39
 */
@Data
public class ExpertApplyQueryCriteria {
    @Query(blurry = "name,company")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;

    @Query
    private Integer state;
}
