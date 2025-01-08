package task_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    
    private Long id;
    private String name;
    private String description;
    private String nameResponsible;
    private int numberOfPendingTasks;
    private int numberOfTasksInProgress;
    private int numberOfCompletedTasks;
}
