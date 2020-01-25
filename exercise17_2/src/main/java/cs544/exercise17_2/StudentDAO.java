package cs544.exercise17_2;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class StudentDAO {
	private SessionFactory sessionFactory;


	public StudentDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void init() {
		Student student = new Student(11334, "Frank", "Brown");
		Course course1 = new Course(1101, "Java", "A");
		Course course2 = new Course(1102, "Math", "B-");
		student.addCourse(course1);
		student.addCourse(course2);
		sessionFactory.getCurrentSession().persist(student);
	}

	public Student load(long studentid) {
		return sessionFactory.getCurrentSession().load(Student.class, studentid);
		/*for (Student student : studentlist) {
			if (student.getStudentid() == studentid) {
				return student;
			}
		}
		return null;*/
	}
}
