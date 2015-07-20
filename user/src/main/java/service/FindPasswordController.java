package service;

import dao.userDao.UserJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import util.MD5Util;
import util.SendEmail.SendMail;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

/**
 * Created by youjm on 2015/7/13.
 */

@Controller
public class FindPasswordController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    UserJDBCTemplate jdbcTemplate = (UserJDBCTemplate) context.getBean("userJDBCTemplate");

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    public ModelAndView returnFindPassword() {
        return new ModelAndView("findPassword");
    }

    @RequestMapping(value = "/findPassword", method = RequestMethod.POST)
    public String findPassword(@RequestParam String username, ModelMap model) throws MessagingException {
        System.out.println(username);
        //判断用户是否存在
        if (jdbcTemplate.isExist(username)) {
            System.out.println("用户存在");
            //发送邮件
            //设置过期时间
            long currentTime = System.currentTimeMillis() + 120000;
            Date time = new Date(currentTime);
            Timestamp ts = new Timestamp(time.getTime());
            //设置validkey
            Random random = new Random();
            String key = username + "|" + ts + "|" + random.nextInt();
            String signature = MD5Util.MD5(key);

            //发送邮件
                    jdbcTemplate.insertInfor(username, ts, signature);
                    SendMail sendmail = new SendMail();
                    String url = "http://localhost:8080/resetPassword" + "?username=" + username + "&amp;validkey=" + signature;
                    sendmail.send(username, url);


            model.addAttribute("check", "请点击邮箱连接认证身份");
            return "findPassword";
        } else {
            System.out.println("用户不存在");
            model.addAttribute("check", "用户不存在，请先注册！");
            return "findPassword";
        }
    }

    //关于找回密码的页面   ：输入两次新密码
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public ModelAndView resetPassword(@RequestParam(value = "username", required = false) String username,
                                      @RequestParam(value = "validkey", required = false) String validkey,
                                      HttpServletRequest request) {

        System.out.println(username);
        System.out.println(validkey);
        //判断是否有权限修改密码
        if (!jdbcTemplate.isChangePass(username, validkey)){
            //   如果不可以的话，发挥到resetpassword页面，并显示相应的错误
            request.setAttribute("errormsg","修改密码失败，请再次输入邮箱。");
            //不管有没有成功最后都要将插入到findpasswordtable上面的数据删除，才能进行第二次修改 ，
            jdbcTemplate.delete(username);
            return new ModelAndView("findPassword");
        }else{
            request.setAttribute("username",username);
            return new ModelAndView("resetPassword");
        }
    }


    //判断是否有权利修改密码
    //新密码表单提交
    @RequestMapping(value = "/resetPasswordJudge", method = RequestMethod.POST)
    public String resetPasswordJudge(@RequestParam String password1,@RequestParam String username,
                                     HttpServletRequest request) {
            System.out.println("********" + username + "**********");
            System.out.println("********" + password1 + "**********");
            jdbcTemplate.update(username,password1);
            //不管有没有成功最后都要将插入到findpasswordtable上面的数据删除，才能进行第二次修改 ，
            // 即:在任何时刻，一个账号在findpasswordtable最多只有一条记录
            jdbcTemplate.delete(username);
            request.setAttribute("message","修改密码成功，请登录！");
            return  "login";

    }
}
