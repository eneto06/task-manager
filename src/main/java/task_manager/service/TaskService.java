package task_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task_manager.dto.TaskDto;
import task_manager.model.Task;
import task_manager.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task registerTask(Task task) {
        return taskRepository.save(task);
    }

    public List<TaskDto> listTasks() {
        return taskRepository.findAll()
                .stream()
                .map(Task::toDto)
                .collect(Collectors.toList());

    }

    public TaskDto findTasksById(Long id) {
        Optional<Task> taskOpt = taskRepository.findById(id);

        if (taskOpt.isPresent()) {
            return taskOpt.get().toDto();
        }

        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task dataTask) {
        Optional<Task> taskOpt = taskRepository.findById(id);

        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();

            task.setTitle(dataTask.getTitle());
            task.setDescription(dataTask.getDescription());
            task.setCreationDate(dataTask.getCreationDate());
            task.setConclusionDate(dataTask.getCreationDate());
            task.setPriority(dataTask.getPriority());
            task.setStatus(dataTask.getStatus());

            return taskRepository.save(task);
        }

        return null;
    }

}
