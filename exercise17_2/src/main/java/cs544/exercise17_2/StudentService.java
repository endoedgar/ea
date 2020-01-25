package cs544.exercise17_2;

import org.springframework.transaction.annotation.Transactional;

public class StudentService {
	private StudentDAO studentdao;

	public StudentService(StudentDAO studentdao) {
		this.studentdao = studentdao;
		this.init();
	}

	@Transactional
	public void init() {
		studentdao.init();
	}

	@Transactional
	public Student getStudent(long studentid) {
		return studentdao.load(studentid);
	}
}
