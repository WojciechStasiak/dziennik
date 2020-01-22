package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.model.Student;
import pl.repository.StudentRepository;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(path = {"/showStudents",""})
    public String goHomePage(Model model) {
        model.addAttribute("students", studentRepository.findAll());
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
        model.addAttribute("students", studentRepository.findByName(name));
        model.addAttribute("student", new Student());
        return "studentPage";
    }

    @RequestMapping(path = {"/showAddStudent"})
    public String showAddStudent(Model model) {
        model.addAttribute(new Student());
        return "addStudent";
    }

    @PostMapping(path = {"/addStudent"})
    public String searchStudentByName(@ModelAttribute Student student, Model model) {
        studentRepository.save(student);
        model.addAttribute("students",studentRepository.findAll());
        model.addAttribute("student", new Student());
        return "studentPage";
    }


}
