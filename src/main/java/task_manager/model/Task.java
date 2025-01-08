package task_manager.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import org.apache.tomcat.util.http.parser.Priority;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task_manager.constants.TaskStatus;
import task_manager.dto.TaskDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "db_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(name = "DESC_TAREFA", columnDefinition = "TEXT")
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DT_CREATION_TASK", nullable = false)
    private LocalDate creationDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DT_CONCLUSION_TASK")
    private LocalDate conclusionDate;

    @Column(name = "PRIORITY_TASK", nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "STATUS_TAREFA", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public TaskDto toDto() {
        
        TaskDto dto = new TaskDto();

        dto.setId(id);
        dto.setTitle(title);
        dto.setDescription(description);
        dto.setCreationDate(creationDate);

        Period period = null;

        if (Objects.isNull(conclusionDate)) {
            period = Period.between(creationDate, LocalDate.now());
        } else {
            period = Period.between(creationDate, conclusionDate);
        }

        int numberOfDaysWorked = period.getDays() + period.getMonths() * 30 + period.getYears() * 365;

        dto.setNumberOfDaysWorked(numberOfDaysWorked);
        dto.setPriority(priority);
        dto.setStatus(status);

        return dto;
    }

}
