package task_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task_manager.dto.UserDto;
import task_manager.exception.CpfRegisteredException;
import task_manager.exception.EmailRegisteredException;
import task_manager.model.User;
import task_manager.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        Optional<User> userOpt = userRepository.findByCpf(user.getCpf());

        if (userOpt.isPresent()) {
            throw new CpfRegisteredException("cpf informed already exists");
        }

        userOpt = userRepository.findByEmail(user.getEmail());

        if (userOpt.isPresent()) {
            throw new EmailRegisteredException("email informed already exists");
        }

        return userRepository.save(user);
    }

    public List<UserDto> listUsers() {
        return userRepository.findAll()
                .stream()
                .map(usuario -> usuario.toDto())
                .toList();
    }

    public UserDto findUsersById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {
            return userOpt.get().toDto();
        }

        return null;
    }

    public List<UserDto> filterUserByName(String name) {
        List<User> users = userRepository.findByNameContains(name);

        return users.stream().map(User::toDto).toList();
    }

    public List<UserDto> filterUserByNameStartingWith(String name) {
        List<User> users = userRepository.findByNameLike(name + "%");

        return users.stream().map(User::toDto).toList();
    }

    public UserDto filterUserByCpf(String cpf) {
        Optional<User> userOpt = userRepository.findByCpf(cpf);

        if (userOpt.isPresent()) {
            return userOpt.get().toDto();
        }

        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User dataUser) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            user.setName(dataUser.getName());
            user.setCpf(dataUser.getCpf());
            user.setDataNascimento(dataUser.getDataNascimento());
            user.setEmail(dataUser.getEmail());
            user.setPassword(dataUser.getPassword());
            user.setStatus(dataUser.getStatus());

            return userRepository.save(user);
        }

        return null;

    }
}
