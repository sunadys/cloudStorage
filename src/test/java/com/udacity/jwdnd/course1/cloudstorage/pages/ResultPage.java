package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.h2.mvstore.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Result page web elements and result page actions.
 */

public class ResultPage {

    @FindBy(id = "aResultSuccess")
    private WebElement aResultSuccess;

    private final JavascriptExecutor js;

    public ResultPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        js = (JavascriptExecutor) driver;
    }

    public void clickOk(){
        js.executeScript("arguments[0].click();", aResultSuccess);
    }
}
