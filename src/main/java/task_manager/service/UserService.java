package task_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task_manager.dto.UserDto;
import task_manager.model.User;
import task_manager.repository.UserRepository;

@Service
public class UserService {

    public UserDto registerUser(User user) {
        return userRepository.save(user).converterParaDto();
    }

    public List<UserDto> listUsers() {
        return userRepository.findAll()
        .stream()
        .map(usuario -> usuario.converterParaDto())
        .toList();
    }

    

    public UserDto findUsersById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return null;
        }

        User user = userOpt.get();
        
        return user.converterParaDto();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Autowired
    private UserRepository userRepository;

}
