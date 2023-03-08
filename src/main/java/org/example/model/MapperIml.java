package org.example.model;

public class MapperIml implements IMapper, IValidator {

    private final String separator = ":;:";

    @Override
    public String map(Note note) {
        return String.format("%s" + separator + "%s" + separator + "%s", note.getId(), note.getHeader(), note.getText());
    }

    @Override
    public Note map(String line) {
        String[] lines = line.split(separator);
        return new Note(lines[0], lines[1], lines[2]);
    }

    @Override
    public boolean validateNote(Note note) {
        return (!note.getHeader().contains(separator) &&
                !note.getText().contains(separator)
        );
    }
}