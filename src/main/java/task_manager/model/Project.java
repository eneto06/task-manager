package task_manager.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task_manager.constants.ProjectStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "db_project")
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150, unique = true)
    private String name;

    @Lob
    @Column(nullable = false)
    private String description;;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyy")
    private LocalDate startDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    

}
