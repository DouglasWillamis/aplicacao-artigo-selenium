package saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

final public class CarrinhoPage extends PageObject {

    @FindBy(css = "span.title")
    private WebElement titulo;
    @FindBys(@FindBy(css = "div.cart_item"))
    private List<WebElement> produtos;
    @FindBy(id = "checkout")
    private WebElement checkoutButao;

    public CarrinhoPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String obterTextoTitulo() {
        wait.until(ExpectedConditions.visibilityOf(titulo));
        return this.titulo.getText().toLowerCase();
    }

    public String obterNomeDoProduto(int index) {
        WebElement nomeProduto = this.produtos.get(index).findElement(By.className("inventory_item_name"));
        return nomeProduto.getText().toLowerCase();
    }

    public String obterPrecoDoProduto(int index) {
        WebElement precoProduto = this.produtos.get(index).findElement(By.className("inventory_item_price"));
        return precoProduto.getText();
    }

    public CheckoutInformacoesPage irParaCheckout() {
        this.checkoutButao.click();
        return new CheckoutInformacoesPage(this.driver, this.wait);
    }
}
