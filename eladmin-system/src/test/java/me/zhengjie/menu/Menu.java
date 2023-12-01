package me.zhengjie.menu;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Rain
 * @date 2023/12/1 17:39
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@Accessors(chain = true)
public class Menu extends Base {

    public static Menu of(String title, String path, String componentName, String componentPath, String permission, Object... children) {
        Menu menu = new Menu();
        menu.setTitle(title);
        menu.setPath(path);
        menu.setComponentName(componentName);
        menu.setComponentPath(componentPath);
        menu.setPermission(permission);
        menu.setChildren(Arrays.asList(children));
        return menu;
    }

}
