package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.model.Grade;
import pl.model.Teacher;
import pl.service.GradeService;
import pl.service.GroupService;
import pl.service.StudentService;
import pl.model.Student;
import pl.service.TeacherService;



@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private GroupService groupService;

    @RequestMapping(path = {"/showStudents"})
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

    @RequestMapping(path = {"/showAddStudent/{id}"})
    public String showAddStudent(@PathVariable("id") Long id, Model model) {
        Student student = new Student();
        student.setGroup(groupService.findByTeacherAndIdGroup(id));
        model.addAttribute("student",student);
        model.addAttribute("group1", groupService.findByTeacherAndIdGroup(id));
        return "addStudent";
    }

    @PostMapping(path = {"/addStudent"})
    public String addStudent(@ModelAttribute Student student, Model model) {
        Teacher teacher = teacherService.getLoggedInTeacher();
        student.setTeacher(teacher);
        studentService.save(student);
        model.addAttribute("groups",groupService.findByTeacher(teacherService.getLoggedInTeacher()));
        return "showGroups";
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
