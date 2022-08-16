package saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

final public class CheckoutInformacoesPage extends PageObject {
    @FindBy(css = "span.title")
    private WebElement titulo;
    @FindBy(id = "first-name")
    private WebElement primeiroNome;
    @FindBy(id = "last-name")
    private WebElement ultimoNome;
    @FindBy(id = "postal-code")
    private WebElement codigoPostal;
    @FindBy(id = "continue")
    private WebElement continuaButao;

    public CheckoutInformacoesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String obterTextoTitulo() {
        this.wait.until(ExpectedConditions.visibilityOf(titulo));
        return this.titulo.getText().toLowerCase();
    }

    public void preencherFormulario(String primeiroNome, String ultimoNome, String codigoPostal) {
        this.primeiroNome.sendKeys(primeiroNome);
        this.ultimoNome.sendKeys(ultimoNome);
        this.codigoPostal.sendKeys(codigoPostal);
    }

    public CheckoutRevisaoPage irParaRevisao() {
        continuaButao.click();
        return new CheckoutRevisaoPage(this.driver, this.wait);
    }
}
