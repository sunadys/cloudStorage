package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller bean for notes tab of home page. Contains methods for handling GET and POST requests to notes tab of home.html
 * Uses methods from service beans to get details to be populated on the html page.
 */



@Controller
@RequestMapping("note")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    // Method to handle GET request on notes
    @GetMapping
    public String getHomePage(Authentication authentication, @ModelAttribute("newFile") FileForm newFile, @ModelAttribute("newNote") NoteForm newNote, @ModelAttribute("newCredential") CredentialForm newCredential, Model model){
        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();
        model.addAttribute("notes", this.noteService.getNoteListings(userId));

        return "home";
    }

    // Method to handle POST request on notes tab, i.e. new note entry and modification of existing note.
    @PostMapping("add-note")
    public String newNote(Authentication authentication, @ModelAttribute("newFile") FileForm newFile, @ModelAttribute("newNote") NoteForm newNote, @ModelAttribute("newCredential") CredentialForm newCredential, Model model){
        String username = authentication.getName();
        String newTitle = newNote.getTit();
        String noteIdStr = newNote.getNoteId();
        String newDescription = newNote.getDesc();
        if (noteIdStr.isEmpty()){
            noteService.addNote(newTitle,newDescription,username);
        } else {
            Note existingNote = getNote(Integer.parseInt(noteIdStr));
            noteService.updateNote(existingNote.getNoteId(),newTitle,newDescription);
        }
        User user = userService.getUser(username);
        Integer userId = user.getUserId();
        model.addAttribute("notes", noteService.getNoteListings(userId));
        model.addAttribute("result", "success");

        return "result";
    }

    // To view a particular note
    @GetMapping(value = "/get-note/{noteId}")
    public Note getNote(@PathVariable Integer noteId){
        return noteService.getNote(noteId);
    }

    // To delete a particular note
    @GetMapping(value = "/delete-note/{noteId}")
    public String deleteNote(
            Authentication authentication, @PathVariable Integer noteId, @ModelAttribute("newNote") NoteForm newNote,
            @ModelAttribute("newFile") FileForm newFile, @ModelAttribute("newCredential") CredentialForm newCredential,
            Model model){
        noteService.deleteNote(noteId);
        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();
        model.addAttribute("notes", noteService.getNoteListings(userId));
        model.addAttribute("result", "success");

        return "result";



    }


}
