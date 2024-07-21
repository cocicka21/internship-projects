package by.artur.internship.controller;

import by.artur.internship.model.dto.ChangeUserDto;
import by.artur.internship.model.dto.RegistrationRequest;
import by.artur.internship.model.dto.UserDto;
import by.artur.internship.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("OK");
    }

    public ResponseEntity<String> updateUser(@PathVariable("id") Long userId, @RequestBody ChangeUserDto dto) {
        userService.updateUser(userId, dto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}