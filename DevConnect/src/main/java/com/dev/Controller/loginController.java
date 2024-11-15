package com.dev.Controller;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.Dao.UserDao;
import com.dev.Model.User;

import jakarta.websocket.server.PathParam;

@Controller
public class loginController {
	
	String maXacThuc="";
	@Autowired
	UserDao userDao;
	User userLogin=new User();
	
	
	//Đăng nhập
	@GetMapping("/login")
	public String login(Model model)
	{
//		User user=new User();
//		model.addAttribute("user",user);
		List<User> dsnv=userDao.findAll();
		System.out.println(dsnv.toString());
		return "Check/Login";
	}
	
	@PostMapping("/login")
	public String checkLogin(Model model,@RequestParam("username")String username,@RequestParam("password")String pass)
	{
		List<User> dsnv=userDao.findAll();
		Boolean checkUser=false;
		String messErr="";
		for(User u:dsnv)
		{
			if(u.getUsername().equals(username))
			{
				checkUser=true;
				if(u.getPassword().equals(pass))
				{
					System.out.println("Đăng nhập thành công");
					
				}
				else
				{
					messErr="Sai mật khẩu vui lòng nhập lại";
					break;
				}
			}
		}
		if(!checkUser)
		{
			model.addAttribute("mess", "Tài khoản không tồn tại");
			return "";
		}
		else
		{
			model.addAttribute("mess", messErr);
			return "";
		}
		
	}
	
	//Đăng Ký
	@GetMapping("/signup")
	public String SignUp(Model model)
	{
		
		return "Check/SignUp";
	}
	
	@PostMapping("/signup")
	public String signup(Model model)
	{
		
		return "";
	}
	
	//Quên mật khẩu
	@GetMapping("/forgotpass")
	public String ForgotPass(Model model)
	{
		
		return "";
	}
	
	@PostMapping("/forgotpass")
	public String checkForgotPass(Model model)
	{
		
		return "";
	}
	
	//Đổi mật khẩu
	@GetMapping("/changePass")
	public String ChangePass(Model model)
	{
		
		return "";
	}
	
	@PostMapping("/changePass")
	public String CheckChangePass(Model model)
	{
		
		return "";
	}
	
	public Boolean sendMail(String email,String name)
	{
		try {
			Properties properties = new Properties();
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "587");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
		
	        final String myEmail = "phongpvps36848@fpt.edu.vn";
	        final String password = "hghm ugqp puja zqpk";
	        
	        Session session = Session.getInstance(properties, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(myEmail, password);
	            }
	        });
	        
	        StringBuilder ranNum=new StringBuilder("");
	        for(int i=0;i<6;i++)
	        {
	        	Double rand = (Math.random()*9)+1;
	        	ranNum.append(rand.toString());
	        }
	        System.out.println(ranNum);
	        maXacThuc=ranNum.toString();
	        
	        
	        String messageText = "Kính gửi "+name+",\n\n" +
	        	    "Chúng tôi đã nhận được yêu cầu xác nhận tài khoản của bạn. " +
	        	    "Vui lòng sử dụng mã xác thực dưới đây để hoàn tất quá trình xác minh:\n\n" +
	        	    "Mã xác thực: "+ranNum+"\n\n" +
	        	    "Mã này có hiệu lực trong vòng 10 phút. Nếu bạn không yêu cầu xác nhận, vui lòng bỏ qua email này. " +
	        	    "Nếu có bất kỳ thắc mắc nào, xin vui lòng liên hệ với chúng tôi qua email hỗ trợ.\n\n" +
	        	    "Trân trọng,\n" +
	        	    "Đội ngũ hỗ trợ: DevConnect \n" +
	        	    "DevConnect. Kết nối tri thức, dẫn lối đam mê";

	        
	        System.out.println(messageText);
	        Message message = prepareMessage(session, myEmail,email, "Mail Xác Thực", messageText);
	        
	        
	        Transport.send(message);
	        System.out.println("Email đã được gửi thành công!");
	        maXacThuc="";
	        
	        return true;
		} catch (Exception e) {
			System.out.println("Email đã được gửi Thất Bại!"+e.toString());
			return false;
		}
	}
	
	private static Message prepareMessage(Session session, String myEmail, String recipient, String subject, String messageText) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(messageText);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Lỗi ở dòng 117 của LoginController");
        return null;
    }
	

	
}
