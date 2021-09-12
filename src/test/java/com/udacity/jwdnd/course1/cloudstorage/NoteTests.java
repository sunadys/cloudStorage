package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTests extends CloudStorageApplicationTests {

    /** Test that creates a new note and verifies that it is displayed */
    @Test
    public void testCreateAndDisplay(){
        String noteTit = "Test Note";
        String noteDesc = "This is a test note.";
        HomePage homePage = signUpAndLogin();
        createNote(noteTit,noteDesc,homePage);
        homePage.navToNotesTab();
        homePage = new HomePage(driver);
        Note note = homePage.getFirstNote();
        Assertions.assertEquals(noteTit, note.getNoteTit()); // Test to validate displayed note title
        Assertions.assertEquals(noteDesc, note.getNoteDesc()); // Test to validate displayed note description
        deleteNote(homePage);
        homePage.logout();
    }

    /** Test that deletes a note and verifies that it is not displayed */

    @Test
    public void testDelete(){
        String noteTitle = "Test Note";
        String noteDescription = "This is a test note.";
        HomePage homePage = signUpAndLogin();
        createNote(noteTitle,noteDescription,homePage);
        homePage.navToNotesTab();
        homePage = new HomePage(driver);
        Assertions.assertFalse(homePage.noNotes(driver)); // Test to confirm note addition
        deleteNote(homePage);
        Assertions.assertTrue(homePage.noNotes(driver)); // Test to confirm note deletion
    }

    /** Test that modifies a note and verifies that the changes are displayed */

    @Test
    public void testModify(){
        String noteTitle = "Test Note";
        String noteDescription = "This is a test note.";
        HomePage homePage = signUpAndLogin();
        createNote(noteTitle,noteDescription,homePage);
        homePage.navToNotesTab();
        homePage = new HomePage(driver);
        homePage.editNote();
        String modNoteTitle = "Modified note";
        String modNoteDescription = "This is a modified note.";
        homePage.modifyNoteTitle(modNoteTitle);
        homePage.modifyNoteDescription(modNoteDescription);
        homePage.saveNoteChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToNotesTab();
        Note note = homePage.getFirstNote();
        Assertions.assertEquals(modNoteTitle, note.getNoteTit()); // Testing modified note title
        Assertions.assertEquals(modNoteDescription, note.getNoteDesc()); // Testing modified note description


    }


    private void createNote(String noteTit, String noteDesc, HomePage homePage){
        homePage.navToNotesTab();
        homePage.addNewNote();
        homePage.setNoteTitle(noteTit);
        homePage.setNoteDescription(noteDesc);
        homePage.saveNoteChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToNotesTab();
    }

    private void deleteNote(HomePage homePage) {
        homePage.deleteNote();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
    }

}
