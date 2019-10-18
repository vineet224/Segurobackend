package api.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.entity.Student;
import api.entity.UserDetail;

@RestController
@RequestMapping("/SignUp")
public class SignUpController {

	@GetMapping("/AddUser")
	public String saveuser(UserDetail user)
	{
		System.out.println(user.getEmail()+"  "+user.getPhoneno()+""); 
		String useremail=user.getEmail();
		int userphoneno=user.getPhoneno();
		Date userdob=user.getDob();
		System.out.println(userdob+" ");
		Calendar c = Calendar.getInstance(); 
		c.setTime(userdob); 
		c.add(Calendar.DATE, 1);
		userdob = c.getTime();
		user.setDob(userdob);
		System.out.println(userdob+" ");
		SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserDetail.class).buildSessionFactory();
		Session session=factory.getCurrentSession();
		try {
			session.beginTransaction();
			Query check_email=session.createQuery("from UserDetail where email='"+useremail+"'");
			List<UserDetail> emailusers=check_email.list();
			Query check_phoneno=session.createQuery("from UserDetail where phoneno='"+userphoneno+"'");
			List<UserDetail> phonenousers=check_phoneno.list();
			if(emailusers.size()!=0)
			{
				session.getTransaction().commit();
				return "Retry another email ";
			}
			else if(phonenousers.size()!=0)
			{
				session.getTransaction().commit();
				return "Retry another phoneno";
			}
			else
			{
				System.out.println("I am saving");
				session.save(user);
				session.getTransaction().commit();
				return "succesfullly saved";
			}
			
			
		}
		finally {
			factory.close();
			System.out.println("All done");
		}
		
	}
	
}
