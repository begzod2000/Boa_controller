package uz.setapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.setapp.controller.models.Response;
import uz.setapp.entity.Role;
import uz.setapp.entity.User;
import uz.setapp.entity.enums.RoleName;
import uz.setapp.payload.ReqSignUp;
import uz.setapp.payload.UserData;
import uz.setapp.repository.MessageRepository;
import uz.setapp.repository.RoleRepository;
import uz.setapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {
    public static final String ACCOUNT_SID = "ACf680a237a788f3a50845ba858f1c5b43";
    public static final String AUTH_TOKEN = "9ae18c44f224416031fa8c0ea7fe858c";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ObjectMapper objectMapper;
    private final MessageRepository messageRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ObjectMapper objectMapper, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.objectMapper = objectMapper;
        this.messageRepository = messageRepository;

    }


    public UserData getUsers(User user) {
        List<RoleName> collect = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        return new UserData(
                user.getId(),
                user.getPhone(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                collect
        );
    }

    public Response signUp(ReqSignUp reqSignUp) {
        Response response = new Response();
        Optional<User> exist = userRepository.findByPhone(reqSignUp.getPhone());
        Optional<User> byUsername = userRepository.findByUsername(reqSignUp.getUsername());
        Object data = null;
        if (!byUsername.isPresent()) {
            if (exist.isPresent()) {
                response.setMessage(messageRepository.findByCode(1012));
            } else {
                User save = userRepository.save(new User(reqSignUp.getUsername(), passwordEncoder.encode(reqSignUp.getPassword()), reqSignUp.getEmail(), reqSignUp.getFirstName(), reqSignUp.getLastName(), reqSignUp.getPhone(), roleRepository.findAllByName(RoleName.valueOf(reqSignUp.getRoles()))));
                data = save.getId();
                response.setData(data);
                response.setMessage(messageRepository.findByCode(0));
            }
        } else {
            response.setMessage(messageRepository.findByCode(1012));
        }

        return response;
    }

    public Response updateUser(ReqSignUp reqSignUp, Long id) {
        Response response = new Response();
//        User user = new User();
        Optional<User> user1 = userRepository.findById(id);
        Optional<User> byUsernameAndActive = userRepository.findByUsername(reqSignUp.getUsername());
        if (!byUsernameAndActive.isPresent() || byUsernameAndActive.get().getUsername().equals(user1.get().getUsername())) {
            if (user1.isPresent()) {
                User user = user1.get();
                user.setUsername(reqSignUp.getUsername());
                user.setFirstName(reqSignUp.getFirstName());
                user.setLastName(reqSignUp.getLastName());
                user.setPassword(passwordEncoder.encode(reqSignUp.getPassword()));
                user.setPhone(reqSignUp.getPhone());
                user.setEmail(reqSignUp.getEmail());
                user.setRoles(roleRepository.findAllByName(RoleName.valueOf(reqSignUp.getRoles())));
                User save = userRepository.save(user);
                response.setData(save.getId());
                response.setMessage(messageRepository.findByCode(0));
            } else {
                response.setMessage(messageRepository.findByCode(1005));
            }
        } else {
            response.setMessage(messageRepository.findByCode(1012));
        }
        return response;
    }
}
