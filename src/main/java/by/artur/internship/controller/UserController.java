package by.artur.internship.controller;

import by.artur.internship.dto.ChangeUserDto;
import by.artur.internship.dto.RegistrationRequest;
import by.artur.internship.dto.UserDto;
import by.artur.internship.service.KafkaProducerService;
import by.artur.internship.service.MQService;
import by.artur.internship.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final KafkaProducerService kafkaProducerService;
    private final MQService mqService;

    @GetMapping("/test/kafka")
    public void sendDataKafka() {
        kafkaProducerService.sendMessage("1", "Hello");
    }

    @GetMapping("/test/mq")
    public void sendDataMQ() {
//        mqService.sendRabbitToMQ("1", "Hello");
        mqService.sendRabbitToMQ("Hello");
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody ChangeUserDto dto) {
        return ResponseEntity.ok(userService.updateUser(userId, dto));
    }

}