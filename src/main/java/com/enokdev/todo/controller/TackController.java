package com.enokdev.todo.controller;

import com.enokdev.todo.models.Task;
import com.enokdev.todo.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//@RequestMapping("/tasks")
public class TackController {

    private final TaskService taskService;

    public TackController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
 public String getTask(Model model){
     List<Task> tasks = taskService.getAllTasks();

          model.addAttribute("tasks", tasks);

        return "tasks";
    }

    @PostMapping
    public String createTask(@RequestParam  String title){
       taskService.createTask(title);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable String id){
       taskService.deleteTask(id);


        return "redirect:/";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable String id){
        taskService.toggleTask(id);

        return "redirect:/";
    }
}
