package by.artur.internship.service;

import by.artur.internship.exception.NotFoundException;
import by.artur.internship.model.dao.Note;
import by.artur.internship.model.dto.EditNoteDto;
import by.artur.internship.model.dto.NoteDto;
import by.artur.internship.model.dto.projection.NoteView;
import by.artur.internship.repository.NoteRepository;
import by.artur.internship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public NoteDto createNote(Long userId, EditNoteDto noteDto) {
        Note note = new Note();
        note.setUser(userRepository.findById(userId).get());
        note.setTitle(noteDto.getTitle());
        note.setText(noteDto.getText());
        note.setCreatedDate(LocalDateTime.now());
        return mapper.map(noteRepository.save(note), NoteDto.class);
    }

    public NoteDto getNoteById(Long noteId) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            return mapper.map(note, NoteDto.class);
        } else {
            throw new NotFoundException("Note not found");
        }
    }

    public List<NoteView> getUserNotes(Long userId) {
//        return noteRepository.findAllByUserId(userId).stream().map(q -> mapper.map(q, NoteDto.class)).toList();
        return noteRepository.findAllByUserId(userId);
    }

    public NoteDto updateNote(Long noteId, EditNoteDto changedNote) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setTitle(changedNote.getTitle());
            note.setText(changedNote.getText());
            noteRepository.save(note);
            return mapper.map(note, NoteDto.class);
        } else {
            throw new NotFoundException("Note not found");
        }
    }

    public void deleteNote(Long noteId) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        if (optionalNote.isPresent()) {
            noteRepository.delete(optionalNote.get());
        } else {
            throw new NotFoundException("Note not found");
        }
    }
}