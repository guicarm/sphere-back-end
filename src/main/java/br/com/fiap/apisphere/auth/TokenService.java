package br.com.fiap.apisphere.auth;

import br.com.fiap.apisphere.user.User;
import br.com.fiap.apisphere.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final UserRepository userRepository;
    Algorithm algorithm = Algorithm.HMAC256("assinatura");

    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Token create(Credentials credentials) {
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
        var token = JWT.create()
                .withSubject(credentials.email())
                .withClaim("role", "ADMIN")
                .withExpiresAt(expiresAt)
                .sign(algorithm);
        return new Token(token, credentials.email());
    }

    public User getUserFromToken(String token) {
        var email = JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();

        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }
}