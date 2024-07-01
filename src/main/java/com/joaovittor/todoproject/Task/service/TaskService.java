package com.joaovittor.todoproject.Task.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaovittor.todoproject.Task.model.Task;
import com.joaovittor.todoproject.Task.repository.TaskRepository;
import com.joaovittor.todoproject.User.model.User;
import com.joaovittor.todoproject.User.service.UserService;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task create(Task task){
        User user = userService.findById(task.getUser().getId());
        task.setId(null);
        task.setUser(user);
        task = taskRepository.save(task);
        return task;
    }

    public Task update(Task task){
        Task newTask = findById(task.getId());
        newTask.setDescription(task.getDescription());
        return taskRepository.save(newTask);
    }

    public void delete(Long id){
        findById(id);
        try{
            taskRepository.deleteById(id);
        } catch(Exception e){
            throw new RuntimeException("Não pode remover pois há entidades relacionadas");
        }
    }

    public Task findById(Long id){
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
            "Not find task with id="+id+" / Tipo: "+Task.class.getName()
        ));
    }

}
