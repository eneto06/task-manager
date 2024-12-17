package task_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import task_manager.model.Task;
import task_manager.repository.TaskRepository;

@Service
public class TaskService {


    public Task registerTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    public Page<Task> listTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Optional<Task> findTasksById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id) {
         taskRepository.deleteById(id);
    }

    @Autowired
    private TaskRepository taskRepository;
}
