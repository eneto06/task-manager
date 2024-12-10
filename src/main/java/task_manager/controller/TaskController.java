package task_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import task_manager.model.Task;
import task_manager.service.TaskService;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task registerNewTask(@RequestBody Task task) {
        return taskService.registerTask(task);
    }

    @GetMapping
    public List<Task> listRegisteredTasks() {
        return taskService.listTasks();
    }
}
