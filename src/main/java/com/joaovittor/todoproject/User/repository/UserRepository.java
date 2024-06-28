package com.joaovittor.todoproject.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaovittor.todoproject.User.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    // ja tem os findBy por cada atributo (id, username, password)
}