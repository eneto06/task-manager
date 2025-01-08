package task_manager.dto;

import java.time.LocalDate;

import org.apache.tomcat.util.http.parser.Priority;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task_manager.constants.TaskStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate;
    private int numberOfDaysWorked;
    private Priority priority;
    private TaskStatus status;
}
