package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTests {
    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach(){
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach(){
        if (this.driver != null){
            driver.quit();
        }
    }

    /** Test that verifies page access */

    @Test
    public void testPageAccess() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    /** Test that signs up an user, logs in and checks home page access, later logs out and verifies that homepage is not accessible */

    @Test
    public void testSignUpLoginLogout(){
        driver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle()); // Tests for sign up page access

        SignupPage signupPage = new SignupPage(driver);
        signupPage.setFirstName("Rahul");
        signupPage.setLastName("Singh");
        signupPage.setUsername("rahul11");
        signupPage.setPassword("singh11");
        signupPage.signUp();

        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle()); // Tests for login page access

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername("rahul11");
        loginPage.setPassword("singh11");
        loginPage.login();
        Assertions.assertEquals("Home", driver.getTitle()); // Tests for home page access after login

        HomePage homePage = new HomePage(driver);
        homePage.logout();

        driver.get("http://localhost:" + this.port + "/home"); // Tests for home page access after logout (redirected to login page)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals("Login", driver.getTitle());
    }

}
