package org.example.di;


import org.example.controllers.NoteController;
import org.example.model.FileStorage;
import org.example.model.IMapper;
import org.example.model.INoteController;
import org.example.model.IRepository;
import org.example.model.IStorage;
import org.example.model.IValidator;
import org.example.model.MapperIml;
import org.example.model.RepositoryImpl;
import org.example.views.NoteView;

public class Module {

    public static IStorage provideStorage() {
        return new FileStorage("notes.txt");
    }

    public static IMapper provideMapper() {
        return new MapperIml();
    }

    public static IValidator provideValidator() {
        return new MapperIml();
    }

    public static IRepository provideRepository(
            IStorage storage,
            IMapper mapper
    ) {
        return new RepositoryImpl(mapper, storage);
    }

    public static INoteController provideNoteController(
            IRepository repository,
            IValidator validator
    ) {
        return new NoteController(repository, validator);
    }

    public static NoteView provideView(
            INoteController controller
    ) {
        return new NoteView(controller);
    }
}