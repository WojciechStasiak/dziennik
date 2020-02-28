package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.model.Teacher;
import pl.service.GradeService;
import pl.service.StudentService;
import pl.model.Student;
import pl.service.TeacherService;

import java.security.Principal;


@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(path = {"/showStudents", "", "/"})
    public String goHomePage(Model model) {
        Teacher teacher = teacherService.getLoggedInTeacher();
        model.addAttribute("students", studentService.findByTeacher(teacher));
        model.addAttribute("student1", new Student());
        return "studentPage";
    }

    @RequestMapping(path = {"/search"})
    public String showSearch(Model model) {
        model.addAttribute("student", new Student());
        return "search";
    }

    @RequestMapping(path = {"/searchStudent{name}"})
    public String searchStudentByName(@RequestParam(name = "name", defaultValue = "", required = false) String name, Model model) {
        Teacher teacher = teacherService.getLoggedInTeacher();
        model.addAttribute("students", studentService.findByNameAndTeacher(name, teacher));
        model.addAttribute("student", new Student());
        return "studentPage";
    }

    @RequestMapping(path = {"/showAddStudent"})
    public String showAddStudent(Model model) {
        model.addAttribute(new Student());
        return "addStudent";
    }

    @PostMapping(path = {"/addStudent"})
    public String addStudent(@ModelAttribute Student student, Model model) {
        Teacher teacher = teacherService.getLoggedInTeacher();
        student.setTeacher(teacher);
        studentService.save(student);
        model.addAttribute("students", studentService.findByTeacher(teacher));
        model.addAttribute("student", new Student());
        return "studentPage";
    }

    @RequestMapping(path = {"/deleteStudent/{id}"})
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
        Teacher teacher = teacherService.getLoggedInTeacher();
        Student student = studentService.getOne(id);
        if (student.getTeacher().getId_teacher().equals(teacher.getId_teacher())) {
            gradeService.deleteAllGradesByStudent(student);
            studentService.deleteStudent(id);
        }else
        {
            model.addAttribute("message", "You don't have permission to delete that user!");
        }
        model.addAttribute("students", studentService.findByTeacher(teacher));
        model.addAttribute("student1", new Student());
        return "studentPage";
    }

}
