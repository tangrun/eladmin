package me.zhengjie;

import cn.hutool.core.lang.Dict;
import me.zhengjie.menu.Button;
import me.zhengjie.menu.Dir;
import me.zhengjie.menu.Menu;
import me.zhengjie.modules.system.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EladminSystemProjectApplicationTests {

    @Resource
    private MenuService menuService;

    @Test
    public void contextLoads() {

    }

    private void addMenu() {
        List<Dir> list = Arrays.asList(
                getProjectMenu()
        );


    }

    private Dir getProjectMenu() {
        return
                Dir.of("项目管理", "project",
                        Menu.of("活动记录", "active", "ProjectListForActive", "project/application/index", "project:application:list"),
                        Menu.of("活动记录", "active/:id", "ProjectActiveList", "project/active/index", "project:active:list",
                                Button.of("添加活动记录", "project:active:add"),
                                Button.of("删除活动记录", "project:active:del"),
                                Button.of("编辑活动记录", "project:active:edit")
                        ),
                        Menu.of("项目预算", "budget", "ProjectListForBudget", "project/application/index", "project:application:list"),
                        Menu.of("项目预算", "budget/:id", "ProjectBudgetList", "project/budget/index", "project:budget:list",
                                Button.of("添加项目预算", "project:budget:add"),
                                Button.of("删除项目预算", "project:budget:del"),
                                Button.of("编辑项目预算", "project:budget:edit")
                        ),
                        Menu.of("变更记录", "change", "ProjectListForChange", "project/application/index", "project:application:list"),
                        Menu.of("变更记录", "change/:id", "ProjectChangeList", "project/change/index", "project:change:list",
                                Button.of("添加变更记录", "project:change:add"),
                                Button.of("删除变更记录", "project:change:del"),
                                Button.of("编辑变更记录", "project:change:edit")
                        ),
                        Menu.of("项目经验", "experience", "ProjectListForExperience", "project/application/index", "project:application:list"),
                        Menu.of("项目经验", "experience/:id", "ProjectExperienceList", "project/experience/index", "project:experience:list",
                                Button.of("添加项目经验", "project:experience:add"),
                                Button.of("删除项目经验", "project:experience:del"),
                                Button.of("编辑项目经验", "project:experience:edit")
                        ),
                        Menu.of("项目目标", "objective", "ProjectListForObjective", "project/application/index", "project:application:list"),
                        Menu.of("项目目标", "objective/:id", "ProjectObjectiveList", "project/objective/index", "project:objective:list",
                                Button.of("添加项目目标", "project:objective:add"),
                                Button.of("删除项目目标", "project:objective:del"),
                                Button.of("编辑项目目标", "project:objective:edit")
                        ),
                        Menu.of("宣传记录", "publicize", "ProjectListForPublicize", "project/application/index", "project:application:list"),
                        Menu.of("宣传记录", "publicize/:id", "ProjectPublicizeList", "project/publicize/index", "project:publicize:list",
                                Button.of("添加宣传记录", "project:publicize:add"),
                                Button.of("删除宣传记录", "project:publicize:del"),
                                Button.of("编辑宣传记录", "project:publicize:edit")
                        ),
                        Menu.of("自评记录", "self", "ProjectListForSelf", "project/application/index", "project:application:list"),
                        Menu.of("自评记录", "self/:id", "ProjectSelfList", "project/self/index", "project:self:list",
                                Button.of("添加自评记录", "project:self:add"),
                                Button.of("删除自评记录", "project:self:del"),
                                Button.of("编辑自评记录", "project:self:edit")
                        ),
                        Menu.of("督导记录", "supervise", "ProjectListForSupervise", "project/application/index", "project:application:list"),
                        Menu.of("督导记录", "supervise/:id", "ProjectSuperviseList", "project/supervise/index", "project:supervise:list",
                                Button.of("添加督导记录", "project:supervise:add"),
                                Button.of("删除督导记录", "project:supervise:del"),
                                Button.of("编辑督导记录", "project:supervise:edit")
                        ),
                        Menu.of("团队成员", "team", "ProjectListForTeam", "project/application/index", "project:application:list"),
                        Menu.of("团队成员", "team/:id", "ProjectTeamList", "project/team/index", "project:team:list",
                                Button.of("添加团队成员", "project:team:add"),
                                Button.of("删除团队成员", "project:team:del"),
                                Button.of("编辑团队成员", "project:team:edit")
                        )
                );
    }

    public static void main(String[] args) {
        createProjectMenuCode();
    }


    private static void createProjectMenuCode() {

        String template = "                        Menu.of(\"$1\", \"$2\", \"ProjectListFor$3\", \"project/application/index\", \"project:application:list\"),\n" +
                "                        Menu.of(\"$1\", \"$2/:id\", \"Project$3List\", \"project/$2/index\", \"project:$2:list\",\n" +
                "                                Button.of(\"添加$1\", \"project:$2:add\"),\n" +
                "                                Button.of(\"删除$1\", \"project:$2:del\"),\n" +
                "                                Button.of(\"编辑$1\", \"project:$2:edit\")\n" +
                "                        )";

        StringBuilder stringBuilder = new StringBuilder();
        for (List<String> list : Arrays.asList(
                Arrays.asList("活动记录", "active", "Active"),
                Arrays.asList("项目预算", "budget", "Budget"),
                Arrays.asList("变更记录", "change", "Change"),
                Arrays.asList("项目经验", "experience", "Experience"),
                Arrays.asList("项目目标", "objective", "Objective"),
                Arrays.asList("宣传记录", "publicize", "Publicize"),
                Arrays.asList("自评记录", "self", "Self"),
                Arrays.asList("督导记录", "supervise", "Supervise"),
                Arrays.asList("团队成员", "team", "Team")
        )) {
            String s = template.replaceAll("\\$1", list.get(0));
            s = s.replaceAll("\\$2", list.get(1));
            s = s.replaceAll("\\$3", list.get(2));
            stringBuilder.append(s).append(",\n");
        }
        System.out.println(stringBuilder);
    }
}

