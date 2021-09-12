package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * This is note data model. Consisting of constructor, getters and setters for all attributes of Note object.
 */

public class Note {
    private Integer noteId;
    private String noteTit;
    private String noteDesc;
    private Integer userId;

    public Note(Integer noteId, String noteTit, String noteDesc, Integer userId) {
        this.noteId = noteId;
        this.noteTit = noteTit;
        this.noteDesc = noteDesc;
        this.userId = userId;
    }

    public Note(String title, String description) {
        this.noteTit = title;
        this.noteDesc = description;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTit() {
        return noteTit;
    }

    public void setNoteTit(String noteTit) {
        this.noteTit = noteTit;
    }

    public String getNoteDesc() {
        return noteDesc;
    }

    public void setNoteDesc(String noteDesc) {
        this.noteDesc = noteDesc;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
