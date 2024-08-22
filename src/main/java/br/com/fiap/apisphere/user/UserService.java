package br.com.fiap.apisphere.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // GET - FINDALL
    public List<User> findAll(){
        return repository.findAll();
    }

    // POST -   CREATE
    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

}
