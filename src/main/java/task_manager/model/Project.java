package task_manager.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task_manager.constants.TaskStatus;
import task_manager.dto.ProjectDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150, unique = true)
    private String name;

    @Lob
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tb_projects_tasks", joinColumns = @JoinColumn(name = "ID_PROJECTS"), inverseJoinColumns = @JoinColumn(name = "ID_TASKS"))
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "ID_USER_RESP")
    private User responsible;

    public ProjectDto toDto() {
        ProjectDto dto = new ProjectDto();

        dto.setName(name);
        dto.setId(id);
        dto.setDescription(description);

        if (Objects.nonNull(responsible)) {
            dto.setNameResponsible(responsible.getName());
        }

        List<Task> pending = tasks.stream()
                .filter(task -> TaskStatus.PENDING.equals(task.getStatus()))
                .collect(Collectors.toList());

        List<Task> doing = tasks.stream()
                .filter(task -> TaskStatus.DOING.equals(task.getStatus()))
                .collect(Collectors.toList());

        List<Task> ended = tasks.stream()
                .filter(task -> TaskStatus.ENDED.equals(task.getStatus()))
                .collect(Collectors.toList());

        dto.setNumberOfPendingTasks(pending.size());
        dto.setNumberOfCompletedTasks(ended.size());
        dto.setNumberOfTasksInProgress(doing.size());

        return dto;
    }

}
