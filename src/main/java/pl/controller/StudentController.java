package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.Service.GradeService;
import pl.Service.StudentService;
import pl.model.Student;
import pl.repository.StudentRepository;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;
    @RequestMapping(path = {"/showStudents",""})
    public String goHomePage(Model model) {
        model.addAttribute("students", studentService.findAllStudents());
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
        model.addAttribute("students", studentService.findByName(name));
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
        studentService.save(student);
        model.addAttribute("students",studentService.findAllStudents());
        model.addAttribute("student", new Student());
        return "studentPage";
    }

    @RequestMapping(path = {"/deleteStudent/{id}"})
    public String deleteStudent(@PathVariable("id") Long id, Model model){
        Student student = studentService.getOne(id);
        gradeService.deleteAllGradesByStudentId(student);
        studentService.deleteStudent(id);
        model.addAttribute("students", studentService.findAllStudents());
        model.addAttribute("student1", new Student());
        return "studentPage";
    }

}
