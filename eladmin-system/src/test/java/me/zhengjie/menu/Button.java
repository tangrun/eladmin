package me.zhengjie.menu;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Rain
 * @date 2023/12/1 17:39
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data()
@Accessors(chain = true)
public class Button extends Base {
    public static Button of(String title, String permission) {
        Button button = new Button();
        button.setTitle(title);
        button.setPermission(permission);
        return button;
    }

}
