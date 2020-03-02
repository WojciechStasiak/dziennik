package pl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.model.Grade;
import pl.model.Group;
import pl.model.Student;
import pl.model.Teacher;
import pl.repository.GradeRepository;
import pl.repository.GroupRepository;

import java.util.List;

@Service
@Transactional
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TeacherService teacherService;
    @Autowired
    GradeService gradeService;
    @Autowired
    StudentService studentService;

    public List<Group> findByTeacher(Teacher teacher){
        List<Group> groups = groupRepository.findByTeacher(teacher);
        return groups;
    }

    public Group findByTeacherAndIdGroup(Long id_group){
        Teacher teacher = teacherService.getLoggedInTeacher();
        Group group = groupRepository.findByIdGroupAndTeacher(id_group,teacher);
        return group;
    }

    public void saveGroup(Group group){
        Teacher teacher = teacherService.getLoggedInTeacher();
        group.setTeacher(teacher);
        groupRepository.save(group);
    }
    public void deleteGroup(Long id){

        Group group = groupRepository.findByIdGroupAndTeacher(id, teacherService.getLoggedInTeacher());
        List<Student> students = studentService.findByGroup(group);

        for(Student student: students) {
            gradeService.deleteAllGradesByStudent(student);
            studentService.deleteStudent(student.getId());
        }
        groupRepository.delete(group);
    }
}
