package me.zhengjie.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.util.List;

/**
 * @author Rain
 * @date 2023/12/1 17:55
 */

@Data
@Accessors(chain = true)
public class Base {
    private String title;
    private String path;
    private String componentName;
    private String componentPath;
    private String permission;
    private int type ;

    private int sort = 999;

    private String icon;

    private boolean cache = false;

    private boolean hidden = false;

    private List<Object> children;
}
