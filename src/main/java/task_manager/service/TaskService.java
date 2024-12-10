package task_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task_manager.model.Task;
import task_manager.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task registerTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> listTasks() {
        return taskRepository.findAll();
    }
}
