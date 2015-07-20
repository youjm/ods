package dao.userDao;

import model.FindPasswordModel.Findpassword;
import model.UserModel.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by youjm on 2015/7/12.
 */
public class UserJDBCTemplate implements UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(String username, String password) {
        String SQL = "insert into usertable (username,password) values (?,?)";
        jdbcTemplate.update(SQL, username, password);
        System.out.println("Created Record Name = " + username + " password = " + password);
        return;
    }

    public User getUser(Integer id) {
        String SQL = "select * from usertable where id = ?";
        User user = jdbcTemplate.queryForObject(SQL, new Object[]{id}, new UserMapper());
        return user;
    }

    public List<User> listUsers() {
        String SQL = "select * from usertable";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper());
        return users;
    }

    public void delete(Integer id) {
        String SQL = "delete from usertable where id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id);
        return;
    }

    public void update(String username, String password) {
        String SQL = "update usertable set password = ? where username = ?";
        jdbcTemplate.update(SQL, password, username);
        System.out.println("Updated usertable Record with username= " + username);
        return;
    }

    public boolean judgeAccount(String username, String password) {
        String SQL = "select password from usertable where username=?";
        //获取该账号对应的密码
        String realPass = jdbcTemplate.query(SQL, new Object[]{username}, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return rs.getString("password");
                }
                return null; //没有对应的账号
            }
        });
        if (realPass != null && realPass.equals(password)) {
            return true;
        } else
            return false;
    }

    public boolean isExist(String username) {
        String SQL = "select * from usertable where username = ?";
        List<User> users = jdbcTemplate.query(SQL, new Object[]{username}, new UserMapper());
        if (users.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }


    //根据用户名查找到用户ID
    public Integer selectByUsername(String username) {
        String sql = "select * from usertable where username= ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserMapper());
        Integer uid = user.getId();
        return uid;
    }

    //插入到数据库表findpassword  中
    public void insertInfor(String email, Timestamp date, String signature) {
        String sql = "insert into findpasswordtable(email,outdate,validkey) values(?,?,?)";
        jdbcTemplate.update(sql, email, date, signature);
        System.out.println("插入到数据库表findpassword中");
        return;
    }

    //判断是否有权限修改密码
    public boolean isChangePass(String email, String validkey){
        String sql = "select * from findpasswordtable where email = ?";
        System.out.println("into isChangePass");
        try {
            Findpassword findpassword = jdbcTemplate.queryForObject(sql, new Object[]{email}, new FindpasswordMapper());
            //判断验证码是否正确、判断是否超时
            System.out.println("have  email");
            String signature = findpassword.getValidkey();
            long current = System.currentTimeMillis();
            long time = findpassword.getOutdate().getTime();
            if(!validkey.equals(signature)){
                System.out.println("验证码出错");
                return false;
            }else if(current>time){
                System.out.println("超时");
                    return false;
            }else {
                System.out.println("成功");
                return true;
            }
        }catch (EmptyResultDataAccessException e){
            System.out.println("no  this  email");
            return false;
        }
    }
    //判断是否可以修改之后， 不管有没有修改都要将插入到passwordtable 的那条记录删除
    public void delete(String email) {
        String sql = "delete from findpasswordtable where email = ?";
        jdbcTemplate.update(sql, email);
        System.out.println("Deleted findpasswordtable Record with email = " + email);
        return;
    }
}
