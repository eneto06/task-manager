package task_manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task_manager.model.User;
import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCpf(String cpf);

    Optional<User> findByEmail(String email);

    Optional<User> findByCpfAndEmail(String cpf, String email);

    List<User> findByNameContains(String name);

    List<User> findByNameLike(String nome);

    List<User> findByDataNascimentoBetween(LocalDate dataBegin, LocalDate dataEnd);

}
