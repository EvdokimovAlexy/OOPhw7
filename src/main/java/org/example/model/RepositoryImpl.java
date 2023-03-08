package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements IRepository {
    private final IMapper mapper;
    private final IStorage storage;

    public RepositoryImpl(IMapper mapper, IStorage storage) {
        this.mapper = mapper;
        this.storage = storage;
    }

    @Override
    public List<Note> getAllNotes() {
        List<String> lines = storage.readAllLines();
        List<Note> notes = new ArrayList<>();
        for (String line : lines) {
            notes.add(mapper.map(line));
        }
        return notes;
    }

    @Override
    public void createNote(Note note) {

        List<Note> notes = getAllNotes();
        int max = 0;
        for (Note item : notes) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        note.setId(id);
        notes.add(note);
        writeDown(notes);
    }

    @Override
    public void updateNote(Note note) {
        List<Note> notes = getAllNotes();
        Note target = notes.stream().filter(i -> i.getId().equals(note.getId())).findFirst().get();
        target.setHeader(note.getHeader());
        target.setText(note.getText());
        writeDown(notes);
    }

    @Override
    public void deleteNote(Note note) {
        List<Note> notes = getAllNotes();
        Note target = notes.stream().filter(i -> i.getId().equals(note.getId())).findFirst().get();
        notes.remove(target);
        writeDown(notes);
    }

    private void writeDown(List<Note> notes) {
        List<String> lines = new ArrayList<>();
        for (Note item : notes) {
            lines.add(mapper.map(item));
        }
        storage.saveAllLines(lines);
    }
}