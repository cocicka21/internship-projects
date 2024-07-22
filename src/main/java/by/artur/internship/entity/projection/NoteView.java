package by.artur.internship.entity.projection;

import java.time.LocalDateTime;

public interface NoteView {

    Long getId();

    String getTitle();

    String getText();

    LocalDateTime getCreatedDate();

}
