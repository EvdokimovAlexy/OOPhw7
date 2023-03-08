package org.example.views;


import org.example.model.INoteController;
import org.example.model.Note;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NoteView {

    private final INoteController noteController;

    public NoteView(INoteController noteController) {
        this.noteController = noteController;
    }

    public void run() {
        Intentions intention = Intentions.NONE;

        while (true) {
            String command = prompt("Введите команду: " +
                    Arrays.toString(Intentions.values())) + " ";
            intention = Intentions.valueOf(command.toUpperCase());
            if (intention == Intentions.EXIT) return;
            try {
                switch (intention) {
                    case CREATE -> {
                        String header = prompt("Введите заголовок: ");
                        String text = prompt("Введите текст: ");
                        noteController.saveNote(new Note(header, text));
                    }
                    case READ -> {
                        String id = prompt("Идентификатор записки: ");
                        Note note = noteController.readNote(id);
                        System.out.println(note);
                    }
                    case LIST -> {
                        List<Note> lst = noteController.readList();
                        lst.forEach(i -> System.out.println(i + "\n"));
                    }
                    case UPDATE -> {
                        String numId = prompt("Какую записку редактировать? Введите номер ID: ");
                        noteController.idPresenceValidation(numId);
                        noteController.updateNote(numId, createNote());
                    }
                    case DELETE -> {
                        String deleteId = prompt("Какой контакт удалить? Введите номер ID: ");
                        noteController.idPresenceValidation(deleteId);
                        noteController.deleteNote(deleteId);
                        noteController.readList()
                                .forEach(i -> System.out.println(i + "\n"));
                    }
                }
            } catch (Exception e) {
                System.out.println("Oopsie!\n"+ e.getMessage()); ;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private Note createNote() {
        String header = prompt("Заголовок: ");
        String text = prompt("Текст: ");
        return new Note(header, text);
    }
}
