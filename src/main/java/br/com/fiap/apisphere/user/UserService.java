package br.com.fiap.apisphere.user;

import br.com.fiap.apisphere.user.dto.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public UserProfileResponse getUserProfile(String email) {
        return repository.findByEmail(email)
                .map(UserProfileResponse::new)
                .orElseThrow( () -> new UsernameNotFoundException("User not found"));
    }

    public void uploadAvatar(String email, MultipartFile file) {
        if (file.isEmpty()){
            throw new RuntimeException("Empty file");
        }

        try (InputStream in = file.getInputStream()) {
            Path destinationDirectory = Path.of("src/main/resources/static/avatars");
            Path destinationFile = destinationDirectory
                    .resolve(  System.currentTimeMillis() + file.getOriginalFilename() )
                    .normalize()
                    .toAbsolutePath();

            Files.copy(in, destinationFile);

            System.out.println("Arquivo copiado");

            var user = repository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User not found") );
            var avatarPath = "http://localhost:8082/avatars/" + destinationFile.getFileName();
            user.setAvatar(avatarPath);
            repository.save(user);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
