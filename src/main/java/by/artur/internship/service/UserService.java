package by.artur.internship.service;

import by.artur.internship.annotation.CustomLogger;
import by.artur.internship.dto.ChangeUserDto;
import by.artur.internship.dto.RegistrationRequest;
import by.artur.internship.dto.UserDto;
import by.artur.internship.entity.Credential;
import by.artur.internship.entity.Role;
import by.artur.internship.entity.User;
import by.artur.internship.exception.AlreadyExistsException;
import by.artur.internship.exception.NotFoundException;
import by.artur.internship.repository.RoleRepository;
import by.artur.internship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CredentialService credentialService;
    private final ModelMapper mapper;

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .toList();
    }

    public UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User is not found"));
        return mapper.map(user, UserDto.class);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @CustomLogger
    public UserDto createUser(RegistrationRequest registrationRequest) {
        if (!userRepository.existsByFirstName(registrationRequest.getFirstname())) {
            Set<Role> roles = new HashSet<>();
            Role role = roleRepository.findByName("user").orElseThrow(() -> new NotFoundException("Role is not found"));
            roles.add(role);
            Credential credential = Credential.builder()
                    .email(registrationRequest.getEmail())
                    .password(registrationRequest.getPassword())
                    .build();
            credentialService.createCredential(credential);
            User user = User.builder()
                    .firstName(registrationRequest.getFirstname())
                    .lastName(registrationRequest.getLastname())
                    .registrationDate(LocalDateTime.now())
                    .roles(roles)
                    .build();
            saveUser(user);

            return mapper.map(user, UserDto.class);
        } else {
            throw new AlreadyExistsException("User already exists");
        }
    }

    public UserDto updateUser(Long userId, ChangeUserDto dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User is not found"));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.getCredential().setEmail(dto.getEmail());
        saveUser(user);
        return mapper.map(user, UserDto.class);
    }

    public UserDto deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User is not found"));
        userRepository.deleteById(userId);
        return mapper.map(user, UserDto.class);

    }
}
