package uz.setapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.setapp.controller.models.Response;
import uz.setapp.entity.Message;
import uz.setapp.entity.User;
import uz.setapp.payload.ReqSignIn;
import uz.setapp.payload.ReqSignUp;
import uz.setapp.repository.MessageRepository;
import uz.setapp.repository.UserRepository;
import uz.setapp.security.JwtTokenProvider;
import uz.setapp.service.impl.UserServiceImpl;


import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;


    @Value("${app.jwtExpirationInMs}")
    private long accessTokenDate;

    @Autowired
    public AuthController(JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, ObjectMapper objectMapper, UserServiceImpl userService, UserRepository userRepository, MessageRepository messageRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }
    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody ReqSignIn reqSignIn){
        ObjectNode data = objectMapper.createObjectNode();
        Optional<User> optionalUser = userRepository.findByUsernameAndActive(reqSignIn.getUsername(), true);
        Message status;

        if (optionalUser.isPresent()){

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(reqSignIn.getUsername(), reqSignIn.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenProvider.generateToken(authentication);
            String refreshToken = jwtTokenProvider.refreshToken(authentication);
            data.put("accessToken", jwt);
            data.put("refreshToken", refreshToken);
            data.put("tokenType","Bearer ");
            data.put("expiryDate",accessTokenDate);
            status=messageRepository.findByCode(0);
        }
        else {
            status=messageRepository.findByCode(101);
        }

        return ResponseEntity.ok(new Response(data, status));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public HttpEntity<?> signUp(@RequestBody ReqSignUp reqSignUp){
//        if (bindingResult.hasErrors()){
//            List<FieldError> errors = bindingResult.getFieldErrors();
//            for (FieldError error : errors ) {
//                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
//            }
//        }
        return ResponseEntity.ok(userService.signUp(reqSignUp));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{id}")
    public HttpEntity<?> updateUser(@RequestBody ReqSignUp reqSignUp, @PathVariable Long id){
        Response response = userService.updateUser(reqSignUp, id);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("delete/{userId}")
    public HttpEntity<?> deleteUser(@PathVariable Long userId) {
        Response response = new Response();
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            userRepository.delete(byId.get());


            response.setMessage(messageRepository.findByCode(0));
        } else {
            response.setMessage(messageRepository.findByCode(1012));
        }
        return ResponseEntity.ok(response);
    }
}
