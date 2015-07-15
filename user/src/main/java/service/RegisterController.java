package service;

import dao.userDao.UserJDBCTemplate;
import model.UserModel.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by youjm on 2015/7/12.
 */
@Controller
public class RegisterController {
    private ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    UserJDBCTemplate jdbcTemplate = (UserJDBCTemplate) context.getBean("userJDBCTemplate");

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView returnRegister(){
        return new ModelAndView("register","user",new User());
    }


    @RequestMapping(value="/inputRegister",method = RequestMethod.POST)
    public String isRegister(@ModelAttribute User user,ModelMap model){
        //判断用户名是否存在
        List<User> list = jdbcTemplate.listUsers();
      for(User obj : list){
          if(obj.getUserName().equals(user.getUserName())){
            model.addAttribute("registerError", "用户名已经存在");
              return  "register";
          }
      }
        //如果用户不存在，写入数据库  返回到页面
        jdbcTemplate.create(user.getUserName(),user.getPassWord());
        return "registerSuccess";
        //后期可以写发送邮件验证  邮箱 是否邮箱，然后点击链接注册成功
    }
}
