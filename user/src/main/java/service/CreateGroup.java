package service;

import dao.groupDao.GroupJDBCTemplate;
import dao.userDao.UserJDBCTemplate;
import model.UserModel.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by youjm on 2015/7/17.
 */
@Controller
public class CreateGroup {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    UserJDBCTemplate jdbcTemplate1 = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
    GroupJDBCTemplate jdbcTemplate2 = (GroupJDBCTemplate)context.getBean("groupJDBCTemplate");
    @RequestMapping(value = "/createGroup" ,method = RequestMethod.GET)
    public ModelAndView createGroup(){
        return  new ModelAndView("createGroupInput");
    }

    @RequestMapping(value = "/createGroupInputJudge",method = RequestMethod.POST)
    public String createGroupInputJudge(@RequestParam("name") String name,HttpSession httpSession,ModelMap model){
        //首先，判断grouptable表中是否有输入的组名了，
        if(jdbcTemplate2.isExist(name)){
            //有的话，就直接提示要重新修改组名
            model.addAttribute("message","对不起，组名已经存在，请输入新的组名");
            return "createGroupInput";
        }else{
            //没有的话，就要通过session传过来的username，通过usertable查找到对应的id。 即获得登录用户对应的id
            User user =(User) httpSession.getAttribute("curUser");
            System.out.println(user.getUserName());
            Integer uid = jdbcTemplate1.selectByUsername(user.getUserName());
            System.out.println(uid);
//            判断是否有没有上传头像，写一个上传文件的工具类，将它存在image-icon

            model.addAttribute("message","组创建成功，耶");
            return "createGroupInput";

        }
    }
}
