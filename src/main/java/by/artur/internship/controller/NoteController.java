package by.artur.internship.controller;

import by.artur.internship.model.dto.EditNoteDto;
import by.artur.internship.model.dto.NoteDto;
import by.artur.internship.model.dto.projection.NoteView;
import by.artur.internship.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<String> creteNote(@PathVariable("userId") Long userId, @RequestBody EditNoteDto note) {
        noteService.createNote(userId, note);
        return ResponseEntity.ok("Note created successfully");
    }

    @GetMapping
    public ResponseEntity<NoteDto> getNotes(Long noteId) {
        return ResponseEntity.ok(noteService.getNoteById(noteId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<NoteView>> getUserNotes(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(noteService.getUserNotes(userId));
    }

    @PatchMapping("/{noteId}")
    public ResponseEntity<String> updateNote(@PathVariable("noteId") Long noteId, @RequestBody EditNoteDto note) {
        noteService.updateNote(noteId, note);
        return ResponseEntity.ok("Note updated successfully");
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable("noteId") Long noteId) {
        noteService.deleteNote(noteId);
        return ResponseEntity.ok("Note deleted successfully");
    }

}
