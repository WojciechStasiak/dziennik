package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.model.Teacher;
import pl.repository.TeacherRepository;
import pl.service.TeacherService;

import javax.validation.Valid;

@Controller
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping(value = {"/login"})
    public String loginPage(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Wrong password or login");

        if (logout != null)
            model.addAttribute("message", "Logged out");
        return "login";
    }

    @GetMapping(value = {"/getRegister"})
    public String getRegister(Model model) {
        model.addAttribute(new Teacher());
        return "register";
    }

    @PostMapping(value = {"/register"})
    public String register(@Valid Teacher teacher, BindingResult bindingResult, Model model) {
       if (bindingResult.hasErrors())
           return "register";
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacherRepository.save(teacher);
        model.addAttribute("registerSuccess","You have successfully registered!");
        return "/login";
    }

}
