package br.com.fiap.apisphere.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.apisphere.post.dto.PostRequest;
import br.com.fiap.apisphere.user.UserRepository;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    UserRepository userRepository;

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Page<Post> findAll(@PageableDefault(size = 3) Pageable pageable) {
        return postService.findAll(pageable);

    }


    @PostMapping
    public Post create(@RequestBody PostRequest postRequest){
        var email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        
        var user = userRepository.findByEmail(email).orElseThrow(
            () -> new UsernameNotFoundException("User not found.")
        );

        Post post = postRequest.toModel(user);
        return postService.create(post);
    }

}
