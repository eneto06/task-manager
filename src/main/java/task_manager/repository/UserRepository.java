package task_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task_manager.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
