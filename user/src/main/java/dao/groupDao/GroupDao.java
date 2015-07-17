package dao.groupDao;

import model.GroupModel.Group;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by youjm on 2015/7/17.
 */
public interface GroupDao {
    /**
     *设置连接数据库的数据源
     */
    public void setDataSource(DataSource ds);


    /**
     * 创建组，即进行插入操作1、有上传头像，2、没有上传头像
     */
    public void create(String name, Integer boss_id,String icon);

    public void create(String name,Integer boss_id);


    /**
     * 根据group_boss_id查看这个用户创建了哪些组
     */
    public List<Group> getGroup(Integer boss_id);


    /**
     * 根据group_name查看这个组名是否存在，如果存在 ，提示更改组名
     */
    public boolean isExist(String name);
}
