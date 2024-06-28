package com.joaovittor.todoproject.User.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaovittor.todoproject.User.model.User;
import com.joaovittor.todoproject.User.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User create(User user){
        
        // garantindo que o id veio nulo
        user.setId(null);

        user = userRepository.save(user);

        // se fosse possível criar usuarios já com tasks:
        // taskRepository.saveAll(user.getTasks());
        return user;
    }

    @Transactional
    public User update(User user){
        User newUser = findById(user.getId());

        // so pode atualizar a senha :)
        newUser.setPassword(user.getPassword());

        return userRepository.save(newUser);
    }

    @Transactional
    public void delete(Long id){
        // chamando esse método p/ verificar se existe
        findById(id);

        try{
            userRepository.deleteById(id);
        } catch(Exception e){
            throw new RuntimeException("Não pode remover pois há tasks relacionadas");
        }
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
            "Not find user with id="+id+" / Tipo: "+User.class.getName()
        ));

        // dava pra usar um if/else pra dar throw na exception, mas estou fznd de outro jeito
    }

}
