package view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static view.Utill.*;

public class validatorHtml {
        WebDriver driver;
        String url = "http://localhost:8080/EindOpdracht_war_exploded/";
        private String geldigeNaam, geldigeKleur, geldigeAantalBloemen;

        @Before
        public void setUp() throws Exception{
            WebDriverManager.chromedriver().setup();
            driver.get(url);

            geldigeAantalBloemen = "3"  ;
            geldigeNaam = "Tulip";
            geldigeKleur = "Rood";
        }


    @Test
    public void BloemTest_HomePagina_HTML_isValide() {
        htmlIsValid(driver, url, "home");
    }

    @Test
    public void BloemTest_OverviewPagina_HTML_isValide() {
        htmlIsValid(driver, url, "overview");
    }

    @Test
    public void BloemTest_AddPagina_HTML_isValide() {
        htmlIsValid(driver, url, "navigeerVoegToe");
    }

    @Test
    public void BloemTest_SearchPagina_HTML_isValide() {
        htmlIsValid(driver, url, "zoekFormulier");
    }


    @Test
    public void BloemTest_EditPagina_HTML_isValide() {
        addBloemIfNotInbloemDomain(driver, url, geldigeNaam);
        htmlIsValid(driver, url, "edit&naam="+ geldigeNaam);
    }

    @Test
    public void BloemTest_VerwijderConfirmationPagina_HTML_isValide() {
        addBloemIfNotInbloemDomain(driver, url, geldigeNaam);
        htmlIsValid(driver, url, "verwijder&naam="+geldigeNaam);
    }

    @Test
    public void BloemTest_zoekGevondenPagina_HTML_isValide() {
        addBloemIfNotInbloemDomain(driver, url,  geldigeNaam);
        fillOutSearchForm(driver, url, geldigeNaam);
        htmlIsValidByPageSource(driver, driver.getPageSource());
    }

    @Test
    public void BloemTest_ZoekNietGevondenPagina_HTML_isValide() {
        deleteBloemIfInDomain(driver, url, geldigeNaam);
        fillOutSearchForm(driver, url, geldigeNaam);
        htmlIsValidByPageSource(driver, driver.getPageSource());
    }




        @After
        public void clear(){
            driver.quit();
        }
}
