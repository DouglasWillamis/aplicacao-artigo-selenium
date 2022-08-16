package saucedemo.stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import saucedemo.helpers.DriverManager;
import saucedemo.pages.*;

import java.math.BigDecimal;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void before() {
        this.driver = DriverManager.getDriver();
        this.wait = DriverManager.getDriverWait();
        this.driver.manage().window().maximize();
    }

    @After
    public void after() {
        DriverManager.quitDriver();
    }

    @Dado("que estou na tela de produtos")
    public void queEstouNaTelaDeProdutos() {
        LoginPage loginPage = new LoginPage(this.driver, this.wait);
        InventarioPage inventarioPage = loginPage.acessar().realizarLoginComSucesso();

        assertThat("products", is(inventarioPage.obterTextoTitulo()));
    }

    @Quando("adiciono alguns produtos ao carrinho")
    public void adicionoAlgunsProdutosAoCarrinho() {
        InventarioPage inventarioPage = new InventarioPage(this.driver, this.wait);
        int sauceLabsBackPack = 0;
        int sauceLabesBoltTShitr = 2;

        assertThat("sauce labs backpack", is(inventarioPage.obterNomeDoProduto(sauceLabsBackPack)));
        assertThat("sauce labs bolt t-shirt", is(inventarioPage.obterNomeDoProduto(sauceLabesBoltTShitr)));

        inventarioPage.adicionarAoCarrinho(sauceLabsBackPack);
        inventarioPage.adicionarAoCarrinho(sauceLabesBoltTShitr);
    }

    @E("abro carrinho de compras")
    public void abroCarrinhoDeCompras() {
        InventarioPage inventarioPage = new InventarioPage(this.driver, this.wait);
        CarrinhoPage carrinhoPage = inventarioPage.irParaCarrinho();
        int sauceLabsBackPack = 0;
        int sauceLabesBoltTShitr = 1;

        assertThat("your cart", is(carrinhoPage.obterTextoTitulo()));
        assertThat("sauce labs backpack", is(carrinhoPage.obterNomeDoProduto(sauceLabsBackPack)));
        assertThat("$29.99", is(carrinhoPage.obterPrecoDoProduto(sauceLabsBackPack)));
        assertThat("sauce labs bolt t-shirt", is(carrinhoPage.obterNomeDoProduto(sauceLabesBoltTShitr)));
        assertThat("$15.99", is(carrinhoPage.obterPrecoDoProduto(sauceLabesBoltTShitr)));
    }

    @E("realizo o checkout")
    public void realizoCheckout() {
        CarrinhoPage carrinhoPage = new CarrinhoPage(this.driver, this.wait);
        CheckoutInformacoesPage checkoutInformacoesPage = carrinhoPage.irParaCheckout();
        Faker faker = new Faker(new Locale("pt-BR"));
        int sauceLabsBackPack = 0;
        int sauceLabesBoltTShitr = 1;

        assertThat("checkout: your information", is(checkoutInformacoesPage.obterTextoTitulo()));

        checkoutInformacoesPage.preencherFormulario(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().zipCode()
        );
        CheckoutRevisaoPage checkoutRevisaoPage = checkoutInformacoesPage.irParaRevisao();

        assertThat("checkout: overview", is(checkoutRevisaoPage.obterTextoTitulo()));
        assertThat("sauce labs backpack", is(checkoutRevisaoPage.obterNomeDoProduto(sauceLabsBackPack)));
        assertThat("sauce labs bolt t-shirt", is(checkoutRevisaoPage.obterNomeDoProduto(sauceLabesBoltTShitr)));

        BigDecimal sauceLabsBackPackPrice = checkoutRevisaoPage.obterPrecoDoProduto(sauceLabsBackPack);
        BigDecimal sauceLabesBoltTShitrPrice = checkoutRevisaoPage.obterPrecoDoProduto(sauceLabesBoltTShitr);
        BigDecimal produtosValor = sauceLabsBackPackPrice.add(sauceLabesBoltTShitrPrice);

        assertThat(produtosValor, is(checkoutRevisaoPage.obterPrecoTotalProdutos()));
        CheckoutFinalizacao checkoutFinalizacao = checkoutRevisaoPage.finalizarComprar();

        assertThat("checkout: complete!", is(checkoutFinalizacao.obterTextoTitulo()));
    }

    @Então("devo conseguir ver a mensagem {string}")
    public void devoConseguirVerAMensagemDeSucesso(String mensagem) {
        CheckoutFinalizacao checkoutFinalizacao = new CheckoutFinalizacao(this.driver, this.wait);
        assertThat(mensagem.toLowerCase(), is(checkoutFinalizacao.obterTituloMensagemSucesso()));
    }
}
