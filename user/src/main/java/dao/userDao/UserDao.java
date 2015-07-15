package dao.userDao;

/**
 * Created by youjm on 2015/7/12.
 */
import model.UserModel.User;
import javax.sql.DataSource;
import java.util.List;

public interface UserDao {
    /**
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
    public void setDataSource(DataSource ds);
    /**
     * This is the method to be used to create
     * a record in the userTable
     */
    public void create(String userName, String passWord);
    /**
     * This is the method to be used to list down
     * a record from the Student table corresponding
     * to a passed student id.
     */
    public User getUser(Integer id);
    /**
     * This is the method to be used to list down
     * all the records from the usertable.
     */
    public List<User> listUsers();
    /**
     * This is the method to be used to delete
     * a record from the userTable corresponding
     * to a passed User id.
     */
    public void delete(Integer id);
    /**
     * This is the method to be used to update
     * a record into the User table.
     */
    public void update(String username, String password);
    /**
     * This is the method to judge whether the password is
     * match for this account
     */
     public boolean judgeAccount(String username,String password);

    //根据邮箱判断用户是否存在
     public boolean isExist(String username);


}
