package view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static view.Utill.*;

public class zoekTest {
    WebDriver driver;
    String url = "http://localhost:8080/EindOpdracht_war_exploded/";
    private String geldigeNaam, geldigeKleur, geldigeAantalBloemen;



    @Before
    public void setUp() throws Exception{
        WebDriverManager.chromedriver().setup();
        driver.get(url);
        geldigeNaam = "Tulip";

    }

    public void BloemTest_Search_ZoekLink_ToontSearchPagina() {
        String cssSelector = "body > header:nth-child(1) > nav:nth-child(2) > ul:nth-child(3) > li:nth-child(4) > a:nth-child(1)";
        String title = "zoeken";

        clickOnElementByCssSelector(driver, url, "index", cssSelector, title);
        clickOnElementByCssSelector(driver, url, "overview", cssSelector, title);
        clickOnElementByCssSelector(driver, url, "navigeerVoegToe", cssSelector, title);
        clickOnElementByCssSelector(driver, url, "zoekFormulier", cssSelector, title);
    }

    @Test
    public void BloemTest_Search_ToontActuelePagina() {
        navigationIsMarkedAsActual(driver, url, "zoekFormulier","Zoeken");
    }

    @Test
    public void BloemTest_Search_BloemDieReedsIsToegevoegdOpNaam_ToontZoekGevondenPagina() {
        addBloemIfNotInbloemDomain(driver, url, geldigeNaam);
        fillOutSearchForm(driver, url, geldigeNaam);
        assertEquals("Bloem gevonden", driver.getTitle());
    }

    @Test
    public void BloemTest_Search_BloemDieNogNietIsToegevoegdOpNaam_ToontZoekNietGevondenPagina() {
        deleteBloemIfInDomain(driver, url, geldigeNaam);
        fillOutSearchForm(driver, url, geldigeNaam);
        assertEquals("Bloem Niet Gevonden", driver.getTitle());
    }


    @Test
    public void Bloem_Search_Op_Naam_ToontFoutmelding() {
        fillOutSearchForm(driver, url, "");
        assertEquals("zoeken", driver.getTitle());

        String error = driver.findElement(By.cssSelector("div.error > ul:nth-child(1) > li:nth-child(1)")).getText();
        assertEquals("De naam kan niet leeg zijn.", error);
    }

    @After
    public void clear(){
        driver.quit();
    }


}
