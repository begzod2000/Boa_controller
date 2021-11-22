package uz.setapp.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.setapp.entity.User;
import uz.setapp.entity.enums.RoleName;
import uz.setapp.repository.RoleRepository;
import uz.setapp.repository.UserRepository;


@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            userRepository.save(new User("begzod",
                    passwordEncoder.encode("1234"),
                    "ismoilmiraliyev@gmail.com",
                    "ismoil",
                    "miraliyev",
                    "+998900447574",
                    roleRepository.findAllByName(RoleName.ROLE_ADMIN)));
//            userRepository.save(new User(
//                    "superAdmin",
//                    passwordEncoder.encode("root123"),
//                    "Super Administrator",
//                    roleRepository.findAll()));

//            userRepository.save(new User(
//                    "admin",
//                    passwordEncoder.encode("admin1"),
//                    "Administrator",
//                    roleRepository.findAllByName(RoleName.ROLE_ADMIN)));
//
//            userRepository.save(new User(
//                    "moderator",
//                    passwordEncoder.encode("moder123"),
//                    "Moderator",
//                    roleRepository.findAllByName(RoleName.ROLE_MODERATOR)));
//
//            userRepository.save(new User(
//                    "user",
//                    passwordEncoder.encode("123"),
//                    "Ismoil Miraliyev",
//                    roleRepository.findAllByName(RoleName.ROLE_USER)));
        }
    }
}
