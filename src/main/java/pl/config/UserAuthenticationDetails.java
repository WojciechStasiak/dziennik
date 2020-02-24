package pl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.model.Teacher;
import pl.service.TeacherService;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationDetails implements UserDetailsService {

    @Autowired
    TeacherService teacherService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher = teacherService.findByEmail(email);
        if (teacher != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList< GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority("teacher"));
            return new
                    org.springframework.security.core.userdetails.User(teacher.getEmail(),
                    teacher.getPassword(), true,
                    true, true, true, grantedAuthorities);
        } else{
            throw new UsernameNotFoundException("Zły email lub hasło...");
        }
    }
}
