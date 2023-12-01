package me.zhengjie.menu;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rain
 * @date 2023/12/1 17:39
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data()
@Accessors(chain = true)
public class Dir extends Base{
    public static Dir of(String title, String path, Object... children) {
        Dir dir = new Dir();
        dir.setTitle(title);
        dir.setPath(path);
        dir.setChildren(Arrays.asList(children));
        return dir;
    }

}
