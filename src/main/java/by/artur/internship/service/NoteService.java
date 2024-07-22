package by.artur.internship.service;

import by.artur.internship.dto.EditNoteDto;
import by.artur.internship.dto.NoteDto;
import by.artur.internship.entity.Note;
import by.artur.internship.entity.User;
import by.artur.internship.entity.projection.NoteView;
import by.artur.internship.exception.NotFoundException;
import by.artur.internship.repository.NoteRepository;
import by.artur.internship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public NoteDto createNote(Long userId, EditNoteDto noteDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User is not found"));
        Note note = Note.builder()
                .user(user)
                .title(noteDto.getTitle())
                .text(noteDto.getText())
                .createdDate(LocalDateTime.now())
                .build();
        return mapper.map(noteRepository.save(note), NoteDto.class);
    }

    public NoteDto getNoteById(Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NotFoundException("Note is not found"));
        return mapper.map(note, NoteDto.class);
    }

    public List<NoteView> getUserNotes(Long userId) {
        return noteRepository.findAllByUserId(userId);
    }

    public List<NoteDto> getNotes() {
        return noteRepository.findAll().stream().map(q -> mapper.map(q, NoteDto.class)).toList();
    }

    public NoteDto updateNote(Long noteId, EditNoteDto changedNote) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NotFoundException("Note is not found"));
        note.setTitle(changedNote.getTitle());
        note.setText(changedNote.getText());
        noteRepository.save(note);
        return mapper.map(note, NoteDto.class);
    }

    public NoteDto deleteNote(Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NotFoundException("Note is not found"));
        noteRepository.delete(note);
        return mapper.map(note, NoteDto.class);
    }
}