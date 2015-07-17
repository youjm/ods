package dao.userDao;

import model.UserModel.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by youjm on 2015/7/12.
 */
public class UserJDBCTemplate implements UserDao {
    private  DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource ds){
        this.dataSource= ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public void create(String username, String password){
        String SQL = "insert into usertable (username,password) values (?,?)";
        jdbcTemplate.update(SQL, username, password);
        System.out.println("Created Record Name = " + username + " password = " + password);
        return;
    }
    public User getUser(Integer id){
        String SQL = "select * from usertable where id = ?";
        User user = jdbcTemplate.queryForObject(SQL, new Object[]{id}, new UserMapper());
        return user;
    }
    public List<User> listUsers(){
        String SQL = "select * from usertable";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper());
        return users;
    }
    public void delete(Integer id){
        String SQL = "delete from usertable where id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id);
        return;
    }
    public void update(String  username, String password){
        String SQL = "update usertable set password = ? where username = ?";
        jdbcTemplate.update(SQL, password, username);
        System.out.println("Updated Record with ID = " + username);
        return;
    }
    public  boolean judgeAccount(String username, String password) {
        String SQL = "select password from usertable where username=?";
        //获取该账号对应的密码
        String realPass = jdbcTemplate.query(SQL,new Object[]{username}, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    return rs.getString("password");
                }
                return null; //没有对应的账号
            }
        });
        if (realPass!=null && realPass.equals(password)) {
            return true;
        } else
            return false;
    }
    public boolean isExist(String username){
        String SQL = "select * from usertable where username = ?";
        List<User> users = jdbcTemplate.query(SQL, new Object[]{username}, new UserMapper());
        if(users.isEmpty()){
            return false;
        }else {
            return true;
        }
    }


    //根据用户名查找到用户ID
    public Integer selectByUsername(String username){
        String sql= "select * from usertable where username= ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserMapper());
        Integer uid = user.getId();
        return uid;
    }
}
