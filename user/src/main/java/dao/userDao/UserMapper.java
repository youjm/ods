package dao.userDao;


import model.UserModel.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by youjm on 2015/7/12.
 */
public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws  SQLException{
            User user  =  new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setPassWord(rs.getString("password"));
            return user;
    }
}
