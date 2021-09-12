package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * This is data model used to collect new notes from the user.
 */


public class NoteForm {
    private String tit;
    private String desc;
    private String noteId;

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
}
