package by.artur.internship.model.dto.projection;

import java.time.LocalDateTime;

public interface NoteView {

    Long getId();
    String getTitle();
    String getText();
    LocalDateTime getCreatedDate();

}
