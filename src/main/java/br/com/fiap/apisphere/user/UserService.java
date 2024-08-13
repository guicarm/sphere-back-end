package br.com.fiap.apisphere.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    // GET - FINDALL
    public List<User> findAll(){
        return repository.findAll();
    }

    // POST -   CREATE
    public User create(User user){
        return repository.save(user);
    }

}
