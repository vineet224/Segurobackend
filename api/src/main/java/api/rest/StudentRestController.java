package api.rest;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.luv2code.springdemo.entity.Student1;
import api.entity.Student;


@RestController
@RequestMapping("/abc")
public class StudentRestController {

	// define endpoint for "/students" - return list of students
	
	/*@GetMapping("/students")
	public List<Student> getStudents() {

		List<Student> theStudents = new ArrayList<Student>();
		
		System.out.println("hello i am here");
		theStudents.add(new Student("Poornima", "Patel"));
		theStudents.add(new Student("Mario", "Rossi"));
		theStudents.add(new Student("Mary", "Smith"));		
			
		return theStudents;
	}*/
	
	@GetMapping("/bakchodi")
	public Student getbc(Student student) {
		SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		Session session=factory.getCurrentSession();
		try {
			session.beginTransaction();
			System.out.println("I am saving");
			session.save(student);
			session.getTransaction().commit();
			return student;
			
			
		}
		finally {
			factory.close();
			System.out.println("All done");
		}
	}
	
}









