package saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.List;

import static saucedemo.helpers.StringHelper.stringSemSinalDeDinheiroParaDecimal;

final public class CheckoutRevisaoPage extends PageObject {
    @FindBy(css = "span.title")
    private WebElement titulo;
    @FindBys(@FindBy(css = "div.cart_item"))
    private List<WebElement> produtos;
    @FindBy(className = "summary_subtotal_label")
    private WebElement precoTotalProdutos;
    @FindBy(id = "finish")
    private WebElement finalizarButao;

    public CheckoutRevisaoPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String obterTextoTitulo() {
        this.wait.until(ExpectedConditions.visibilityOf(titulo));
        return this.titulo.getText().toLowerCase();
    }

    public String obterNomeDoProduto(int index) {
        WebElement nomeProduto = this.produtos.get(index).findElement(By.className("inventory_item_name"));
        return nomeProduto.getText().toLowerCase();
    }

    public BigDecimal obterPrecoDoProduto(int index) {
        WebElement precoProduto = this.produtos.get(index).findElement(By.className("inventory_item_price"));
        return stringSemSinalDeDinheiroParaDecimal(precoProduto.getText());
    }

    public BigDecimal obterPrecoTotalProdutos() {
        return stringSemSinalDeDinheiroParaDecimal(precoTotalProdutos.getText());
    }

    public CheckoutFinalizacao finalizarComprar() {
        this.finalizarButao.click();
        return new CheckoutFinalizacao(this.driver, this.wait);
    }
}
