package by.artur.internship.controller;

import by.artur.internship.dto.EditNoteDto;
import by.artur.internship.dto.NoteDto;
import by.artur.internship.entity.projection.NoteView;
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

    @GetMapping
    public ResponseEntity<List<NoteDto>> getNotes() {
        return ResponseEntity.ok(noteService.getNotes());
    }

    @PostMapping
    public ResponseEntity<NoteDto> creteNote(@RequestHeader("userId") Long userId, @RequestBody EditNoteDto note) {
        return ResponseEntity.ok(noteService.createNote(userId, note));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNote(@PathVariable("id") Long noteId) {
        return ResponseEntity.ok(noteService.getNoteById(noteId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NoteView>> getUserNotes(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(noteService.getUserNotes(userId));
    }

    @PatchMapping("/{noteId}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable("noteId") Long noteId, @RequestBody EditNoteDto note) {
        return ResponseEntity.ok(noteService.updateNote(noteId, note));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<NoteDto> deleteNote(@PathVariable("noteId") Long noteId) {
        return ResponseEntity.ok(noteService.deleteNote(noteId));
    }

}
