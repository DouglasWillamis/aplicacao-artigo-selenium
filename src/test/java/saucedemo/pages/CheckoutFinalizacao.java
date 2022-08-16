package saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

final public class CheckoutFinalizacao extends PageObject {
    @FindBy(css = "span.title")
    private WebElement titulo;
    @FindBy(className = "complete-header")
    private WebElement tituloMensagemSucesso;

    public CheckoutFinalizacao(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String obterTextoTitulo() {
        this.wait.until(ExpectedConditions.visibilityOf(this.titulo));
        return this.titulo.getText().toLowerCase();
    }

    public String obterTituloMensagemSucesso () {
        return this.tituloMensagemSucesso.getText().toLowerCase();
    }
}
