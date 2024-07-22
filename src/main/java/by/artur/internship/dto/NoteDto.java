package by.artur.internship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {

    private Long id;
    private String title;
    private String text;
    private LocalDateTime createdDate;

}
