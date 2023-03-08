package org.example.model;

public interface IMapper {

    String map(Note note);

    Note map(String line);
}