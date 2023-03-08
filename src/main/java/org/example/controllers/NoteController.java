package org.example.controllers;

import org.example.model.INoteController;
import org.example.model.IRepository;
import org.example.model.IValidator;
import org.example.model.Note;

import java.util.List;

public class NoteController implements INoteController {

    private final IRepository repository;
    private final IValidator validator;

    public NoteController(IRepository repository, IValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public void saveNote(Note note) throws Exception {
        validateNote(note);
        repository.createNote(note);
    }

    @Override
    public Note readNote(String noteId) throws Exception {
        List<Note> notes = repository.getAllNotes();
        for (Note note : notes) {
            if (note.getId().equals(noteId)) {
                return note;
            }
        }

        throw new Exception("User not found");
    }

    @Override
    public List<Note> readList() {
        List<Note> result = repository.getAllNotes();
        return result;
    }

    @Override
    public void updateNote(String noteId, Note newNote) throws Exception {
        idPresenceValidation(noteId);
        newNote.setId(noteId);
        validateNoteId(newNote);
        repository.updateNote(newNote);
    }

    @Override
    public void deleteNote(String noteId) throws Exception {
        idPresenceValidation(noteId);
        repository.deleteNote(
                readNote(noteId)
        );
    }

    @Override
    public void idPresenceValidation(String noteId) throws Exception {
        List<Note> notes = repository.getAllNotes();
        for (Note item : notes) {
            if (item.getId().equals(noteId))
                return;
        }
        throw new Exception("No such ID here");
    }

    private void validateNote(Note note) throws Exception {
        if (!validator.validateNote(note)) {
            throw new Exception("Note has unacceptable characters");
        }
    }

    private void validateNoteId(Note note) throws Exception {
        if (note.getId().isEmpty())
            throw new Exception("Note has no id");
        validateNote(note);
    }
}