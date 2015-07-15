package dao.userDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by youjm on 2015/7/12.
 */
public class test_login {
    public static void main(String  argsp[]){
        ApplicationContext context =  new ClassPathXmlApplicationContext("spring-config.xml");
        UserJDBCTemplate jdbcTemplate = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
        String username = "you@qq.com";
        String password = "12344321";
        boolean judge1= jdbcTemplate.judgeAccount(username,password);
        boolean judge2 = jdbcTemplate.judgeAccount("you","12345");
        System.out.print(judge1 + " "+ judge2);


    }
}
