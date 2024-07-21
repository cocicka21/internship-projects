package by.artur.internship.repository;

import by.artur.internship.model.dao.Note;
import by.artur.internship.model.dto.projection.NoteView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

//    @Query("select n.id as id, n.title as title, n.text as text, n.createdDate as createdDate from Note n where n.user.id = :user_id")
//    @Query("select id from Note where user.id = :user_id")
//    List<NoteView> findAllByUserId(Long userId);
    List<NoteView> findAllByUserId(@Param("user_id") Long userId);
}
