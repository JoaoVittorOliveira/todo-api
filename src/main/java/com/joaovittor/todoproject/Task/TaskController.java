package com.joaovittor.todoproject.Task;

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

import com.joaovittor.todoproject.Task.model.Task;
import com.joaovittor.todoproject.Task.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @PostMapping
    @Validated
    public ResponseEntity<Void> create( @Valid @RequestBody Task task ){
        taskService.create(task);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                  .path("/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update( @Valid @RequestBody Task task, @PathVariable Long id){
        task.setId(id);
        taskService.update(task);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id){
        taskService.delete(id); 
       return ResponseEntity.noContent().build(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(Long id){
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

}