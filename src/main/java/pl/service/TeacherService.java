package pl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.model.Teacher;
import pl.repository.TeacherRepository;

@Service
@Transactional
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public void save(Teacher teacher){
        teacherRepository.save(teacher);
    }
    public Teacher findByEmail(String email){
        return teacherRepository.findByEmail(email);

    }

}
