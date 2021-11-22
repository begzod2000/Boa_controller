package uz.setapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.setapp.entity.User;
import uz.setapp.repository.UserRepository;

import java.util.Optional;


@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameAndActive(username, true);
        return user.map(value -> (UserDetails) value).orElseGet(() -> (UserDetails) ResponseEntity.status(HttpStatus.CONFLICT).body(username + "is not found"));
    }

    public UserDetails loadUserById(Long userId) {
        return userRepository.findByIdAndActive(userId,true).orElseThrow(() -> new UsernameNotFoundException("User id not found: " +userId));
    }
}
