package br.com.fiap.apisphere.post.dto;

import java.time.LocalDateTime;

import br.com.fiap.apisphere.post.Post;
import br.com.fiap.apisphere.user.User;

public record PostRequest(String text) {
    
    public Post toModel(User user){
        return Post
                .builder()
                .text(text)
                .created_at(LocalDateTime.now())
                .user(user)
                .build();
    }
}
