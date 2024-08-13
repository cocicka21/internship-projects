package by.artur.internship.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserActionDto {

//    @JsonProperty("userId")
    private String userId;

//    @JsonProperty("action")
    private String action;

//    @JsonProperty("actionsDate")
    private LocalDateTime actionsDate;

}
