package by.artur.internship.repository;

import by.artur.internship.entity.Note;
import by.artur.internship.entity.projection.NoteView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

//    @Query("select n.id, n.title, n.text, n.createdDate from Note n where n.user.id = ?1")
//    @Query("select id from Note where user.id = :user_id")
//    List<NoteView> findAllByUserId(Long userId);
    List<NoteView> findAllByUserId(Long userId);
}
//@Param("user_id")