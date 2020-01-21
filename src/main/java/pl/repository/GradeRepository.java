package pl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.model.Grade;
import pl.model.Student;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(Student student);
}
