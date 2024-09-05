package br.com.fiap.apisphere.post;

import br.com.fiap.apisphere.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table( name = "posts")
public class Post {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String text;
    LocalDateTime created_at;

    @ManyToOne
    User user;
}
