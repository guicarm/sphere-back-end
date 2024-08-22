package br.com.fiap.apisphere.auth;

import br.com.fiap.apisphere.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials){

        // Verificar se o Email existe
        var user = userRepository.findByEmail(credentials.email())
                .orElseThrow( () -> new RuntimeException("Access Denied"));

        // Verificar se a Senha bate
        if(!passwordEncoder.matches(credentials.password(), user.getPassword()))
            throw new RuntimeException("Access Denied");

        Algorithm algorithm = Algorithm.HMAC256("RM98874");
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
        var token = JWT.create()
                .withSubject(credentials.email())
                .withClaim("role", "ADMIN")
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return new Token(token, credentials.email());
    }

}
