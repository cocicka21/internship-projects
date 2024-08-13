package by.artur.internship.controller;

import by.artur.internship.dto.UserActionDto;
import by.artur.internship.service.UserActionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eureka")
@Slf4j
public class ActionController {

    private final UserActionService service;


    @GetMapping("/{userId}")
    public ResponseEntity<UserActionDto> getAction(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(service.getUserAction(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserActionDto>> getActionAll() throws JsonProcessingException {
        return ResponseEntity.ok(service.getActions());
    }
}
