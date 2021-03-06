package pl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.model.Grade;
import pl.model.Student;
import pl.repository.GradeRepository;
import pl.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GradeService {

    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    StudentRepository studentRepository;
    public List<Grade> findByStudent(Student student){
        return (List<Grade>) gradeRepository.findByStudent(student);
    }

    public void save(Grade grade){
        gradeRepository.save(grade);
    }

    public Optional<Grade> getOne(Long id){
        return gradeRepository.findById(id);
    }

    public Student getStudentFromGrade(Long id){
        Grade grade = gradeRepository.getOne(id);
        Student student = grade.getStudent();
        return student;
    }

    public void deleteOneGrade(Long id){
        gradeRepository.deleteById(id);
    }

    public void deleteAllGradesByStudent(Student student){
        List<Grade> grades = findByStudent(student);
        for(Grade grade: grades){
            gradeRepository.deleteById(grade.getId_grade());
        }
    }
}
