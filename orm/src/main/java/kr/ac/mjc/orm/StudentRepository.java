package kr.ac.mjc.orm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {

    public Student save(Student student);
}
