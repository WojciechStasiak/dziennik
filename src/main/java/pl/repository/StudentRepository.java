package pl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);
    List<Student> findById(Integer id);
}
