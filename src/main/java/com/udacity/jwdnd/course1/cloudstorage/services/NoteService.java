package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

/**
 * Service bean for note-related functions. Contains methods to fetch notes belonging to a particular user,
 * create new notes, modify existing notes, delete notes and fetch particular note by id.
 */


@Service
public class NoteService {
    private final UserMapper userMapper;
    private final NoteMapper noteMapper;

    public NoteService(UserMapper userMapper, NoteMapper noteMapper) {
        this.userMapper = userMapper;
        this.noteMapper = noteMapper;
    }

    /** Method to add new note **/
    public void addNote(String title, String description, String username){
        Integer userId =  userMapper.getUser(username).getUserId();
        Note note = new Note(0, title, description, userId);
        noteMapper.insert(note);
    }

    /** Method to fetch notes for a particular user by user id **/
    public Note[] getNoteListings(Integer userId){
        return noteMapper.getNoteListingForUser(userId);
    }

    /** Method to fetch notes by note id **/
    public Note getNote(Integer noteId){
        return noteMapper.getNote(noteId);
    }

    /** Method to delete notes by note id **/
    public void deleteNote(Integer noteId){
        noteMapper.deleteNote(noteId);
    }

    /** Method to modify notes by note id **/
    public void updateNote(Integer noteId, String title, String description){
        noteMapper.updateNote(noteId, title, description);
    }
}
