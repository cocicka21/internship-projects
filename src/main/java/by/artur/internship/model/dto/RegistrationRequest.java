package by.artur.internship.model.dto;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
