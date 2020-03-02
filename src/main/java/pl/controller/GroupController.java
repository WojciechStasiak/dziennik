package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.model.Group;
import pl.model.Student;
import pl.service.GroupService;
import pl.service.StudentService;
import pl.service.TeacherService;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @GetMapping(path = {"/showAddGroup"})
    public String showAddGroup(Model model){
        model.addAttribute("group",new Group());
        return "addGroup";
    }

    @PostMapping(path = {"/addGroup"})
    public String addGroup(@ModelAttribute Group group, Model model){
        groupService.saveGroup(group);
        model.addAttribute("groups",groupService.findByTeacher(teacherService.getLoggedInTeacher()));
        return "showGroups";
    }

    @GetMapping(path = {"/showGroups","/",""})
    public String showGroups(Model model){
        model.addAttribute("groups",groupService.findByTeacher(teacherService.getLoggedInTeacher()));
        return "showGroups";
    }

    @GetMapping(path = {"/showStudentsInTheGroup/{id}"})
    public String showStudentsInTheGroup(@PathVariable("id") Long id, Model model){
        model.addAttribute("students", studentService.findByGroup(groupService.findByTeacherAndIdGroup(id)));
        return "studentPage";
    }

    @RequestMapping(path = {"/deleteGroup/{id}"})
    public String deleteGroup(@PathVariable("id")Long id , Model model){
        groupService.deleteGroup(id);
        model.addAttribute("groups",groupService.findByTeacher(teacherService.getLoggedInTeacher()));
        return "showGroups";
    }

}
