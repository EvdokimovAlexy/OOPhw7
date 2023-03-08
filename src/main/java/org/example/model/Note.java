package org.example.model;


public class Note {
    private String id = "";
    private String header;
    private String text;

    public Note(String header, String text) {
        this.header = header;
        this.text = text;
    }

    public Note(String id, String header, String text) {
        this(header, text);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "Идентификатор: "+ id +" Заголовок: " + header + " Текст: " + text;
    }
}