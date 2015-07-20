package util.SendEmail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.ArrayList;

public class SendMail {
    public void send(String email,String url) throws AddressException, MessagingException
    {
//输入你自己准备发送邮件的账号和密码
        SimpleMailSender sms = new SimpleMailSender("18860116743@163.com","ming123456");
        String recipients = email;
        sms.send(recipients, "ods找回密码","尊敬的ods用户，为了找回您的密码，请在两分钟之内点击以下链接:"+url+"   如果不是您本人操作，请忽略此消息。");
    }

    //Mian 函数测试
    public static void main(String[] args) throws MessagingException{
        SimpleMailSender sms = new SimpleMailSender("18860116743@163.com","ming123456");
        ArrayList<String> recipients = new ArrayList<String>();
        recipients.add("916742994@qq.com");
        for(String recipient:recipients){
            sms.send(recipient, "test  for  java mail","hello test.");
        }
    }
}