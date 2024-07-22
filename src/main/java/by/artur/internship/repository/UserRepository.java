package by.artur.internship.repository;

import by.artur.internship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByFirstName(String firstName);

}
