package dao.groupDao;

import dao.userDao.UserJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by youjm on 2015/7/17.
 */
public class test_create {
        public static  void main(String args[]){
            ApplicationContext context =  new ClassPathXmlApplicationContext("spring-config.xml");
            GroupJDBCTemplate jdbcTemplate = (GroupJDBCTemplate) context.getBean("groupJDBCTemplate");
            String group_name = "group1";
            Integer group_boss_id =1;
            jdbcTemplate.create(group_name,group_boss_id);
            System.out.println("success");
        }
}
