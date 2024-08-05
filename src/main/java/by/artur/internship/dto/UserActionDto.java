package by.artur.internship.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserActionDto {

    private String userId;

    private String action;

    private LocalDateTime actionsDate;

}
