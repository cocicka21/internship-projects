package by.artur.internship.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "notes")
public class Note extends BasicEntity {

    @ManyToOne
    private User user;
    @Column(name = "title")
    private String title;

    private String text;
    private LocalDateTime createdDate;

}
