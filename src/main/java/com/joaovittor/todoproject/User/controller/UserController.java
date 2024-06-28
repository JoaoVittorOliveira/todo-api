package com.joaovittor.todoproject.User.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaovittor.todoproject.User.model.User;
import com.joaovittor.todoproject.User.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    @Validated
    public ResponseEntity<Void> create( @Valid @RequestBody User user ){
        userService.create(user);

        // pegando o contexto do path(/user/), e colocando o id(user/{id}) 
        // do usuario inserido no path
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                  .path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update( @Valid @RequestBody User user, @PathVariable Long id){
        user.setId(id);
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id){
       userService.delete(id); 
       return ResponseEntity.noContent().build(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById( @PathVariable Long id){

        User user = userService.findById(id);

        return ResponseEntity.ok().body(user);
    }

}
