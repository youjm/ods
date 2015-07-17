package dao.groupDao;

import model.GroupModel.Group;
import model.UserModel.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by youjm on 2015/7/17.
 */
public class GroupMapper implements RowMapper<Group> {
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group  =  new Group();
        group.setId(rs.getInt("group_id"));
        group.setName(rs.getString("group_name"));
        group.setBoss_id(rs.getInt("group_boss_id"));
        group.setIcon(rs.getString("group_icon"));
        return group;
    }
}
