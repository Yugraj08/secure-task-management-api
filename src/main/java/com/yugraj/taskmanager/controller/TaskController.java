package com.yugraj.taskmanager.controller;

import com.yugraj.taskmanager.entity.Task;
import com.yugraj.taskmanager.entity.User;
import com.yugraj.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.yugraj.taskmanager.repository.TaskRepository;
import com.yugraj.taskmanager.security.JwtUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final UserRepository userRepo;

    private final TaskRepository taskRepo;
    private final JwtUtil jwtUtil;


    @PostMapping
    public Task create(@RequestBody Task task, @RequestHeader("Authorization") String token) {

        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));

        User user = userRepo.findByUsername(username).orElseThrow();

        task.setUserId(user.getId());

        return taskRepo.save(task);
    }

    @GetMapping
    public List<Task> getAll(@RequestHeader("Authorization") String token) {

        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        User user = userRepo.findByUsername(username).orElseThrow();

        if (user.getRole().equals("ADMIN")) {
            return taskRepo.findAll();   // admin sees everything
        }

        return taskRepo.findByUserId(user.getId()); // user sees own
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id,
                       @RequestBody Task updatedTask,
                       @RequestHeader("Authorization") String token) {

        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        User user = userRepo.findByUsername(username).orElseThrow();

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Optional: ensure user owns task
        if (!task.getUserId().equals(user.getId()) && !user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Unauthorized");
        }

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());

        return taskRepo.save(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskRepo.deleteById(id);
    }
}
