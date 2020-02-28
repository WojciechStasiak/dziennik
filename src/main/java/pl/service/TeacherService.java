package pl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    public Teacher getLoggedInTeacher(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String teacherEmail = ((UserDetails)principal).getUsername();
        Teacher teacher = teacherRepository.findByEmail(teacherEmail);
        return teacher;
    }

}
