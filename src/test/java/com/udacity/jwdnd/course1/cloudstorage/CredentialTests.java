package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTests extends CloudStorageApplicationTests {
    public static final String TestUrlA = "abc.com";
    public static final String TestUsernameA = "abc";
    public static final String TestPasswordA = "abc123";
    public static final String TestUrlB = "xyz.com";
    public static final String TestUsernameB = "xyz";
    public static final String TestPasswordB = "xyz123";

    /** Test that creates a credential and verifies that it is displayed and password displayed is encrypted */

    @Test
    public void testCreationAndDisplay(){
        HomePage homePage = signUpAndLogin();
        createCredentials(TestUrlA, TestUsernameA, TestPasswordA, homePage);
        homePage.navToCredentialsTab();
        homePage = new HomePage(driver);
        Credential credential = homePage.getFirstCredential();
        Assertions.assertEquals(TestUrlA, credential.getSiteUrl()); // Test to confirm credential url
        Assertions.assertEquals(TestUsernameA, credential.getUsername()); // Test to confirm credential username
        Assertions.assertNotEquals(TestPasswordA, credential.getPassword()); // Test to confirm that encoded password is displayed
        deleteCredential(homePage);
        homePage.logout();

    }

    /** Test that creates 2 credentials and tests their deletion*/

    @Test
    public void testDeletion(){
        HomePage homePage = signUpAndLogin();
        createCredentials(TestUrlA, TestUsernameA, TestPasswordA, homePage);
        createCredentials(TestUrlB, TestUsernameB, TestPasswordB, homePage);
        homePage.navToCredentialsTab();
        homePage = new HomePage(driver);
        Assertions.assertFalse(homePage.noCredentials(driver)); // Test to confirm that credentials are added
        deleteCredential(homePage);
        homePage.navToCredentialsTab();
        deleteCredential(homePage);
        homePage.navToCredentialsTab();
        Assertions.assertTrue(homePage.noCredentials(driver)); // Test to confirm deletion of credentials
        homePage.logout();
    }

    /** Test that creates a credential, modifies it and verifies that the modification is displayed */

    @Test
    public void testModification(){
        HomePage homePage = signUpAndLogin();
        createCredentials(TestUrlA, TestUsernameA, TestPasswordA, homePage);
        homePage.navToCredentialsTab();
        Credential originalCredential = homePage.getFirstCredential();
        String firstEncryptedPassword = originalCredential.getPassword();
        homePage = new HomePage(driver);
        homePage.editCredential();
        String newUrl = TestUrlB;
        String newUsername = TestUsernameB;
        String newPassword = TestPasswordB;
        setCredentialFields(newUrl,newUsername,newPassword,homePage);
        homePage.saveCredentialChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToCredentialsTab();
        Credential modifiedCredential = homePage.getFirstCredential();
        Assertions.assertEquals(newUrl, modifiedCredential.getSiteUrl());
        Assertions.assertEquals(newUsername, modifiedCredential.getUsername());
        String modifiedPassword = modifiedCredential.getPassword();
        Assertions.assertNotEquals(newPassword, modifiedPassword);
        Assertions.assertNotEquals(firstEncryptedPassword, modifiedPassword);
        homePage.deleteCredential();
        resultPage.clickOk();
        homePage.logout();

    }

    private void setCredentialFields(String url, String username, String password, HomePage homePage){
        homePage.setCredentialUrl(url);
        homePage.setCredentialPassword(password);
        homePage.setCredentialUsername(username);
    }

    private void createCredentials(String url, String username, String password, HomePage homePage){
        homePage.navToCredentialsTab();
        homePage.addNewCredential();
        setCredentialFields(url, username, password, homePage);
        homePage.saveCredentialChanges();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
        homePage.navToCredentialsTab();
    }

    private void deleteCredential(HomePage homePage){
        homePage.deleteCredential();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickOk();
    }




}
