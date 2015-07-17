package dao.groupDao;

import model.GroupModel.Group;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


/**
 * Created by youjm on 2015/7/17.
 */
public class GroupJDBCTemplate implements GroupDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String name, Integer boss_id, String icon) {
        String sql = "insert into grouptable(group_name,group_boss_id,group_icon) values(?,?,?)";
        jdbcTemplate.update(sql,name,boss_id,icon);
        System.out.println("create success");
        return ;
    }


    @Override
    public void create(String name, Integer boss_id) {
        System.out.println("**************************");
        String sql = "insert into grouptable (group_name,group_boss_id) values (?,?)";
        jdbcTemplate.update(sql,name,boss_id);
        System.out.println("create success");
        return ;
    }

    @Override
    public List<Group> getGroup(Integer boss_id) {
        String sql = "select * from grouptable where group_boss_id=?";
        List<Group> groups= jdbcTemplate.query(sql,new Object[]{boss_id},new GroupMapper());
        System.out.println("select success");
        return groups;
    }

    @Override
    public boolean isExist(String name) {
        String sql = "select * from grouptable where group_name = ?";
        List<Group> groups=  jdbcTemplate.query(sql, new Object[]{name}, new GroupMapper());
        if(groups.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}
