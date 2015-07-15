package dao.userDao;

import model.UserModel.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by youjm on 2015/7/12.
 */
public class test_register {
    public static void main(String  argsp[]){
        ApplicationContext context =  new ClassPathXmlApplicationContext("spring-config.xml");
        UserJDBCTemplate jdbcTemplate = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
        String username = "you@test.com";
        String password = "123456";

        List<User> list = jdbcTemplate.listUsers();
        System.out.println(list.size());
        for(User user:list){
            System.out.println(user.getUserName());
        }
    }
}
