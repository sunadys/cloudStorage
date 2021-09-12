package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller bean for home page. Contains methods for handling GET and POST requests to home.html
 * Uses methods from service beans to get details to be populated on the html.
 */


@Controller
@RequestMapping("/home")
public class HomeController {
    private final UserService userService;
    private final NoteService noteService;
    private final FilesService filesService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, NoteService noteService, FilesService filesService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.noteService = noteService;
        this.filesService = filesService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    // Method to handle GET request on homepage
    @GetMapping
    public String getHomePage(Authentication authentication, @ModelAttribute("newFile") FileForm newFile, @ModelAttribute("newNote") NoteForm newNote, @ModelAttribute("newCredential") CredentialForm newCredential, Model model){
        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();
        model.addAttribute("files", this.filesService.getFileListings(userId));
        model.addAttribute("notes", noteService.getNoteListings(userId));
        model.addAttribute("credentials", credentialService.getCredentialListings(userId));
        model.addAttribute("encryptionService", encryptionService);

        return "home";

    }

    // Method to handle POST request, i.e. new file entry on the homepage
    @PostMapping
    public String newFile(Authentication authentication, @ModelAttribute("newFile") FileForm newFile, @ModelAttribute("newNote") NoteForm newNote, @ModelAttribute("newCredential") CredentialForm newCredential, Model model) throws IOException {
        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();
        String[] fileListings = filesService.getFileListings(userId);
        MultipartFile multipartFile = newFile.getFile(); // Getting multipart file from inputStream
        String filename = multipartFile.getOriginalFilename(); // Getting filename
        boolean fileIsDuplicate = false;
        // Checking if filename already exists
        for (String fileListing: fileListings) {
            if (fileListing.equals(filename)) {
                fileIsDuplicate = true;

                break;
            }
        }
        if(!fileIsDuplicate){
            filesService.addFile(multipartFile,username);
            model.addAttribute("result", "success"); //Updating success flag

        } else {
            model.addAttribute("result", "error"); //Updating error message
            model.addAttribute("message", "File already exists.");
        }
        model.addAttribute("files", filesService.getFileListings(userId));

        return "result";
    }

    // To download a particular file
    @GetMapping(value = "/get-file/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    byte[] getFile(@PathVariable String filename){
        return filesService.getFile(filename).getFileData();
    }

    // To delete a particular file
    @GetMapping(value = "/delete-file/{filename}")
    public String deleteFile(Authentication authentication, @PathVariable String filename, @ModelAttribute("newFile") FileForm newFile, @ModelAttribute("newNote") NoteForm newNote, @ModelAttribute("newCredential") CredentialForm newCredential, Model model){
        filesService.deleteFile(filename);
        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();
        model.addAttribute("files", filesService.getFileListings(userId));
        model.addAttribute("result","success");

        return "result";
    }
}
