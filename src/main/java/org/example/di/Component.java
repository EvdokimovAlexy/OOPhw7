package org.example.di;

import org.example.model.INoteController;
import org.example.model.IRepository;
import org.example.model.IStorage;
import org.example.views.NoteView;

public class Component {

    public IStorage storage;
    public IRepository repository;
    public INoteController noteController;
    public NoteView noteView;

    public Component() {

        storage = Module.provideStorage();
        repository = Module.provideRepository(storage, Module.provideMapper());
        noteController = Module.provideNoteController(repository, Module.provideValidator());
        noteView = Module.provideView(noteController);
    }
}