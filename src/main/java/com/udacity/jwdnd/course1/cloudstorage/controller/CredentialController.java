package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Controller bean for credentials tab of home page. Contains methods for handling GET and POST requests to credentials tab of home.html
 * Uses methods from service beans to get details to be populated on the html page.
 */

@Controller
@RequestMapping("credential")
public class CredentialController {

    private final CredentialService credentialService;
    private final UserService userService;
    private final EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    // Method to handle GET requests on credentials
    @GetMapping
    public String getHomePage(Authentication authentication, @ModelAttribute("newFile") FileForm newFile,
                              @ModelAttribute("newCredential") CredentialForm newCredential,
                              @ModelAttribute("newNote") NoteForm newNote, Model model){
        String username = authentication.getName();
        User user = userService.getUser(username);
        model.addAttribute("credentials",this.credentialService.getCredentialListings(user.getUserId()));
        model.addAttribute("encryptionService", encryptionService);

        return "home";
    }

    // Method to handle POST requests on credentials tab, i.e. new credential entry or modification of existing credentials
    @PostMapping("add-credential")
    public String newCredential(
            Authentication authentication, @ModelAttribute("newFile") FileForm newFile,
            @ModelAttribute("newCredential") CredentialForm newCredential,
            @ModelAttribute("newNote") NoteForm newNote, Model model){
        String username = authentication.getName();
        String newUrl = newCredential.getSiteUrl();
        String credentialIdStr = newCredential.getCredentialId();
        String password = newCredential.getPassword();

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password,encodedKey);

        if (credentialIdStr.isEmpty()){
            credentialService.addCredential(newUrl, username, newCredential.getUsername(),encodedKey,encryptedPassword);
        } else {
            Credential existingCredential = getCredential(Integer.parseInt(credentialIdStr));
            credentialService.updateCredential(existingCredential.getCredentialId(),newCredential.getUsername(),newUrl,encodedKey,encryptedPassword);
        }
        User user = userService.getUser(username);
        model.addAttribute("credentials", credentialService.getCredentialListings(user.getUserId()));
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("result", "success");

        return "result";
    }

    // To view a particular credential
    @GetMapping(value = "/get-credential/{credentialId}")
    public Credential getCredential(@PathVariable Integer credentialId){
        return credentialService.getCredential(credentialId);
    }

    // To delete a particular credential
    @GetMapping(value = "/delete-credential/{credentialId}")
    public String deleteCredential(Authentication authentication, @PathVariable Integer credentialId,
                                   @ModelAttribute("newCredential") CredentialForm newCredential,
                                   @ModelAttribute("newFile") FileForm newFile,
                                   @ModelAttribute("newNote") NoteForm newNote, Model model){
        credentialService.deleteCredential(credentialId);
        String username = authentication.getName();
        User user = userService.getUser(username);
        model.addAttribute("credentials", credentialService.getCredentialListings(user.getUserId()));
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("result", "success");

        return "result";

    }
}
