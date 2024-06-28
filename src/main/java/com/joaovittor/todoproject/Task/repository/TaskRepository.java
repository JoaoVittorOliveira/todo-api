package com.joaovittor.todoproject.Task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaovittor.todoproject.Task.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

    public List<Task> findByUser_Id(Long id);

    // OUTRA MANEIRA:
    // @Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
    // public List<Task> findByUser_Id( @Param("id") Long id);

}