package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.service.GradeService;
import pl.service.StudentService;
import pl.model.Grade;
import pl.model.Student;


@Controller
public class GradeController {

    @Autowired
    GradeService gradeService;
    @Autowired
    StudentService studentService;

    @RequestMapping(path = {"/showAddGrade/{id}"})
    public String showAddGrade(@PathVariable("id") Long id, Model model) {
        Student student = new Student();
        student.setId(id);
        model.addAttribute("student", student);
        model.addAttribute("students",studentService.getOne(id));
        model.addAttribute("grade", new Grade());
        return "addGrade";
    }

    @PostMapping(path = {"/addGrade"})
    public String addGrade(@ModelAttribute Grade grade, Model model, @ModelAttribute Student student) {
        grade.setStudent(student);
        gradeService.save(grade);
        model.addAttribute("grades",gradeService.findByStudent(student));
        model.addAttribute("student",student);
        return "showGrades";
    }

    @RequestMapping(path = {"/showGrades{id}"})
    public String showGrades(@PathVariable("id") Long id, Model model){
        Student student = new Student();
        student.setId(id);
        model.addAttribute("grades",gradeService.findByStudent(student));
        model.addAttribute("student",studentService.getOne(id));
        return "showGrades";
    }

    @RequestMapping(path = {"/deleteGrade/{id}"})
    public String deleteGrade(@PathVariable("id") Long id, Model model){
        Student student = gradeService.getStudentFromGrade(id);
        gradeService.deleteOne(id);
        model.addAttribute("grades",gradeService.findByStudent(student));
        model.addAttribute("student", student);
        return "showGrades";
    }


}
