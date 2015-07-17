package model.GroupModel;

/**
 * Created by youjm on 2015/7/17.
 */
public class Group {
    private  Integer id;
    private  String name;
    private Integer boss_id;
    private String icon;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBoss_id() {
        return boss_id;
    }

    public String getIcon() {
        return icon;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoss_id(Integer boss_id) {
        this.boss_id = boss_id;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
