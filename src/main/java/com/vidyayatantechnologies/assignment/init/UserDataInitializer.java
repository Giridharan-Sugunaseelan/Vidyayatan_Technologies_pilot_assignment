package com.vidyayatantechnologies.assignment.init;

import com.vidyayatantechnologies.assignment.entities.Role;
import com.vidyayatantechnologies.assignment.entities.User;
import com.vidyayatantechnologies.assignment.repository.RoleRepository;
import com.vidyayatantechnologies.assignment.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(4)
@AllArgsConstructor
public class UserDataInitializer implements CommandLineRunner {

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        initializeUsers();
    }

    public void initializeUsers(){
        Role adminRole = this.roleRepository.findByName("Admin");
        Role librarianRole = this.roleRepository.findByName("Librarian");
        Role memberRole = this.roleRepository.findByName("Member");

        User admin = new User("Admin", "admin@gmail.com", passwordEncoder.encode("adminPassword"));
        User  librarian = new User("Librarian", "librarian@gmail.com", passwordEncoder.encode("librarianPassword"));
        User member = new User("Member", "member@gmail.com", passwordEncoder.encode("memberPassword"));

        admin.getRoles().add(adminRole);
        librarian.getRoles().add(librarianRole);
        member.getRoles().add(memberRole);

        createUserIfNotExists(admin);
        createUserIfNotExists(librarian);
        createUserIfNotExists(member);
    }

    @Transactional
    public void createUserIfNotExists(User user){
        if(!this.userRepository.existsByEmail(user.getEmail())){
            this.userRepository.save(user);
        }
    }
}
