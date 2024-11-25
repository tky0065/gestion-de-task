package com.enokdev.todo.services;

import com.enokdev.todo.models.Task;
import com.enokdev.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        // order by completed
        List<Task> tasks = taskRepository.findAll();
        tasks.sort((t1, t2) -> Boolean.compare(t1.isCompleted(), t2.isCompleted()));

        return tasks;
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);

    }

    public void deleteTask(String id) {
         taskRepository.deleteById(id);
    }

    public void toggleTask(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
