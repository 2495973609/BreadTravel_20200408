package com.breadTravel.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class SendCode {
//    public static Map<String,String> codeMap=new HashMap<>();
    public String code;

    public SendCode() {
    }

    public String  Verification(HttpServletRequest req, HttpServletResponse resp, String emailAddress) throws IOException, ServletException {
        String result = null;
        try {
            //设置参数
            Properties prop = new Properties();
            prop.setProperty("mail.smtp.host", "smtp.qq.com");
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");     //是否要验证用户
            prop.setProperty("mail.smtp.port", "465");
            prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.setProperty("mail.smtp.socketFactory.port", "465");
            //发送邮件的5个步骤
            //1. 获取session
            Session session1 = Session.getInstance(prop);
            session1.setDebug(true);
            //2.创建ts对象
            Transport ts = session1.getTransport();
            //3.连接发件服务器
            //通过第三方连接QQ邮箱服务器，需要邮箱地址、授权码
            ts.connect("smtp.qq.com", "2495973609@qq.com", "hihdizydrnirdihb");
            //4.创建邮件对象
            Message message = createMessage(session1, emailAddress);
            //5.发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
            result = code;
        } catch (Exception e) {
            e.printStackTrace();
            result = "F";
        }
        System.out.println(result);
        return code;
    }

    private Message createMessage(Session session1, String emailAddress) throws MessagingException {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session1);
        //声明发件人
        message.setFrom(new InternetAddress("2495973609@qq.com"));
        //声明收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
        //声明主题
        message.setSubject("面包旅行【验证码】");
        code=getRandomString();
        //声明内容
        message.setContent("您正在进行身份验证，验证码为"+code+"，一分钟内有效，如非本人操作，请忽略本信息。", "text/html;charset=utf-8");
        return message;
    }

    private String getRandomString() {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
