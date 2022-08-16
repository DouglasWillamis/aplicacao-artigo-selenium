package saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class PageObject {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageObject(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }
}
