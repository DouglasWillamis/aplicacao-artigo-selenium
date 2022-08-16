package saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

final public class InventarioPage extends PageObject {

    @FindBy(css = "span.title")
    private WebElement titulo;
    @FindBy(css = "div.peek")
    private WebElement produtoLogo;
    @FindBys(@FindBy(css = "div#inventory_container div.inventory_list div.inventory_item"))
    private List<WebElement> produtos;
    @FindBy(className = "shopping_cart_link")
    private WebElement carrinhoButao;

    public InventarioPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String obterTextoTitulo() {
        return this.titulo.getText().toLowerCase();
    }

    public void adicionarAoCarrinho(int index) {
        this.wait.until(ExpectedConditions.visibilityOf(this.produtoLogo));
        this.produtos.get(index).findElement(By.cssSelector("button.btn_inventory")).click();
    }

    public String obterNomeDoProduto(int index) {
        this.wait.until(ExpectedConditions.visibilityOf(this.produtoLogo));
        WebElement nomeProduto = this.produtos.get(index).findElement(By.className("inventory_item_name"));
        return nomeProduto.getText().toLowerCase();
    }

    public CarrinhoPage irParaCarrinho() {
        this.carrinhoButao.click();

        return new CarrinhoPage(this.driver, this.wait);
    }
}
