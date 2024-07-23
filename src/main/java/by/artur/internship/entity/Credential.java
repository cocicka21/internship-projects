package by.artur.internship.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "credentials")
@NoArgsConstructor
@AllArgsConstructor
public class Credential extends BasicEntity {

    private String email;
    private String password;

}
