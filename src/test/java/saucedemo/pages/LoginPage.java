package saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static saucedemo.helpers.PagesUrl.SAUCE_DEMO_MAIN_PAGE;

final public class LoginPage extends PageObject {

    @FindBy(id = "user-name")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement senhaInput;
    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public LoginPage acessar() {
        this.driver.get(SAUCE_DEMO_MAIN_PAGE);
        return this;
    }
    public InventarioPage realizarLoginComSucesso() {
        this.wait.until(ExpectedConditions.visibilityOf(this.emailInput));

        this.emailInput.sendKeys("standard_user");
        this.senhaInput.sendKeys("secret_sauce");
        this.loginButton.click();

        return new InventarioPage(this.driver, this.wait);
    }
}
