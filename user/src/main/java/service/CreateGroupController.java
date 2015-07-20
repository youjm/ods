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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

/**
 * Created by youjm on 2015/7/17.
 */
@Controller
public class CreateGroupController {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    UserJDBCTemplate jdbcTemplate1 = (UserJDBCTemplate)context.getBean("userJDBCTemplate");
    GroupJDBCTemplate jdbcTemplate2 = (GroupJDBCTemplate)context.getBean("groupJDBCTemplate");
    @RequestMapping(value = "/createGroup" ,method = RequestMethod.GET)
    public ModelAndView createGroup(){
        return  new ModelAndView("createGroupInput");
    }


    @RequestMapping(value = "/createGroupInputJudge",method = RequestMethod.POST)
    public String createGroupInputJudge(ModelMap model, HttpServletRequest request,
                                        HttpServletResponse response,
                                        HttpSession httpSession)
                                        throws  Exception{
        //enctype="multipart/form-data"方式提交表单，获得参数的方法
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile icon = multipartRequest.getFile("icon");
        /**获取文件的后缀**/
        String suffix =icon.getOriginalFilename().substring
                (icon.getOriginalFilename().lastIndexOf("."));
        String name  =multipartRequest.getParameter("name");

        //首先，判断grouptable表中是否有输入的组名了，
        if(jdbcTemplate2.isExist(name)){
            //有的话，就直接提示要重新修改组名
            model.addAttribute("message","对不起，组名已经存在，请输入新的组名");
            return "createGroupInput";
        }else{
            //没有的话，就要通过session传过来的username，通过usertable查找到对应的id。
            //即获得登录用户对应的id
            User user =(User) httpSession.getAttribute("curUser");
            Integer uid = jdbcTemplate1.selectByUsername(user.getUserName());
            System.out.println(uid);
//            判断是否有没有上传头像，写一个上传文件的工具类，将它存在image-icon
            //如果没有上传图片，直接将group_name，group_boss_id写入数据库
            if(icon.isEmpty()){
                System.out.print("______________这是没有上传头像_____________");
                System.out.println("defaulticon.jgp");
                jdbcTemplate2.create(name,uid);
                System.out.println("插入成功");
            }else {
                //如果有上传头像，将头像存在服务器中，并重命名，然后将group_name，group_boss_id，group_icon写入数据库
                System.out.print("___________________________");
                System.out.println(suffix);
                //限制上传的类型
                if(!(suffix.equals(".jpg")||suffix.equals(".png")||suffix.equals(".gif"))){
                    model.addAttribute("message","请上传jpg、png、gif格式的图片");
                    return "createGroupInput";
                }else {
                    //开始上传文件
                    /**得到图片保存目录的真实路径**/
                    System.out.println();
                    String logoRealPathDir = request.getSession().getServletContext().getRealPath("/image/icon");
                    System.out.println();
                    System.out.println(logoRealPathDir);
                    /**根据真实路径创建目录,如果不存在，创建它**/
                    File logoSaveFile = new File(logoRealPathDir);
                    if (!logoSaveFile.exists()) {
                        logoSaveFile.mkdir();
                    }
                    System.out.print("******##########4");
                    //        /**使用UUID生成文件名称**/
                    String logImageName = UUID.randomUUID().toString() + suffix;//构建文件名称
                    /**拼成完整的文件保存路径加文件**/
                    String fileName = logoRealPathDir + File.separator + logImageName;
                    File file = new File(fileName);
                    icon.transferTo(file);
                    System.out.println(fileName);
                    System.out.println(logImageName);
                    //文件上传结束，写入数据库
                    jdbcTemplate2.create(name, uid, logImageName);
                    System.out.println("插入成功");
                }
            }
            model.addAttribute("message","组创建成功，耶");
            return "createGroupInput";
        }
    }
}
