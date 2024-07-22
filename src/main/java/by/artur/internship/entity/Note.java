package by.artur.internship.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "notes")
public class Note extends BasicEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "text", length = 500)
    private String text;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
