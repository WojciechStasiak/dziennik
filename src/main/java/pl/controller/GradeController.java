package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.model.Teacher;
import pl.service.GradeService;
import pl.service.StudentService;
import pl.model.Grade;
import pl.model.Student;
import pl.service.TeacherService;


@Controller
public class GradeController {

    @Autowired
    GradeService gradeService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @RequestMapping(path = {"/showAddGrade/{id}"})
    public String showAddGrade(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getOne(id);
        Teacher teacher = teacherService.getLoggedInTeacher();
        if (!(student.getTeacher().getId_teacher().equals(teacher.getId_teacher()))) {
            model.addAttribute("message", "You don't have permission to see that page!");
            model.addAttribute("students", studentService.findByTeacher(teacher));
            model.addAttribute("student1", new Student());
            return "studentPage";
        }

        model.addAttribute("student", student);
        model.addAttribute("students", studentService.getOne(id));
        model.addAttribute("grade", new Grade());
        return "addGrade";
    }

    @PostMapping(path = {"/addGrade"})
    public String addGrade(@ModelAttribute Grade grade, Model model, @ModelAttribute Student student) {
        grade.setStudent(student);
        gradeService.save(grade);
        model.addAttribute("grades", gradeService.findByStudent(student));
        model.addAttribute("student", studentService.getOne(student.getId()));
        return "showGrades";
    }

    @RequestMapping(path = {"/showGrades{id}"})
    public String showGrades(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getOne(id);
        Teacher teacher = teacherService.getLoggedInTeacher();
        if (!(student.getTeacher().getId_teacher().equals(teacher.getId_teacher()))) {
            model.addAttribute("message", "You don't have permission to see that page!");
            model.addAttribute("students", studentService.findByTeacher(teacher));
            model.addAttribute("student1", new Student());
            return "studentPage";

        }

        model.addAttribute("grades", gradeService.findByStudent(student));
        model.addAttribute("student", studentService.getOne(id));
        return "showGrades";
    }

    @RequestMapping(path = {"/deleteGrade/{id}"})
    public String deleteGrade(@PathVariable("id") Long id, Model model) {
        Student student = gradeService.getStudentFromGrade(id);
        Teacher teacher = teacherService.getLoggedInTeacher();
        if ((student.getTeacher().getId_teacher()).equals(teacher.getId_teacher())) {
            gradeService.deleteOne(id);
        }
        else {
            model.addAttribute("message", "You cant't delete that grade!");
            model.addAttribute("students", studentService.findByTeacher(teacher));
            model.addAttribute("student1", new Student());
            return "studentPage";
        }
        model.addAttribute("grades", gradeService.findByStudent(student));
        model.addAttribute("student", student);
        return "showGrades";
    }


}
