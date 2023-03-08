package org.example.model;

import java.util.List;

public interface IRepository {

    List<Note> getAllNotes();

    void createNote(Note note);

    void updateNote(Note note);

    void deleteNote(Note note);
}