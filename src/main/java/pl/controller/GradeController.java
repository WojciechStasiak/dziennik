package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.model.Grade;
import pl.model.GradeEnum;
import pl.model.Student;
import pl.repository.GradeRepository;
import pl.repository.StudentRepository;

@Controller
public class GradeController {

    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(path = {"/showAddGrade/{id}"})
    public String showAddGrade(@PathVariable("id") Integer id,Model model) {
        model.addAttribute("student", studentRepository.findById(id));
        model.addAttribute("grade", new Grade());
        return "addGrade";
    }

    @PostMapping(path = {"/addGrade"})
    public String addGrade(@ModelAttribute Grade grade, Model model, @ModelAttribute Student student) {
        grade.setStudent(student);
        gradeRepository.save(grade);
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("student", new Student());
        return "studentPage";
    }

    @RequestMapping(path = {"/showGrades{id}"})
    public String showGrades(@PathVariable("id") Integer id, Model model){
        Student student = new Student();
        student.setId(id);
        model.addAttribute("grades",gradeRepository.findByStudent(student));
        model.addAttribute("student",studentRepository.findById(id));
        return "showGrades";
    }

}
