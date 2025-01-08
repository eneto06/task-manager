package task_manager.model;

import java.time.LocalDate;
import java.time.Period;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task_manager.constants.UserStatus;
import task_manager.dto.UserDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "db_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME_USUARIO", nullable = false, length = 150)
    private String name;

    @Column(name = "CPF_USER", nullable = false, unique = true)
    private String cpf;

    @Column(name = "EMAIL_USER", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD_USER", nullable = false)
    private String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "BORN_DATE_USER", nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public UserDto toDto()

    {

        UserDto dto = new UserDto();

        dto.setId(id);
        dto.setName(name);
        dto.setEmail(email);
        dto.setCpf(email);

        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(dataNascimento, dataAtual);

        dto.setIdade(periodo.getYears());
        dto.setStatus(status);

        return dto;

    }

}
