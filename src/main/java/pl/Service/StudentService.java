package pl.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.model.Student;
import pl.repository.StudentRepository;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void save(Student student){
        studentRepository.save(student);
    }

    public List<Student> findAllStudents(){
        return (List<Student>) studentRepository.findAll();
    }

    public Student getOne(Long id){
        Optional<Student> student = studentRepository.findById(id);
        return student.get();
    }

    public Student findByName(String name){
        Optional<Student> student = studentRepository.findByName(name);
        return student.get();
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }
}
