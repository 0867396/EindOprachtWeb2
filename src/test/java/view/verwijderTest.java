package view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static view.Utill.*;

public class verwijderTest {
    WebDriver driver;
    String url = "http://localhost:8080/EindOpdracht_war_exploded/";
    private String geldigeNaam;

    @Before
    public void setUp() throws Exception{
        WebDriverManager.chromedriver().setup();
        driver.get(url);
        geldigeNaam = "Tulip";

    }



    public void BloemTest_Overzicht_OverviewLink_ToontOverviewPagina() {
        String cssSelector = "body > header:nth-child(1) > nav:nth-child(2) > ul:nth-child(3) > li:nth-child(3) > a:nth-child(1)";
        String title = "Verwijderen";

        clickOnElementByCssSelector(driver, url, "home", cssSelector, title);
        clickOnElementByCssSelector(driver, url, "overview", cssSelector, title);
        clickOnElementByCssSelector(driver, url, "navigeerVoegToe", cssSelector, title);
        clickOnElementByCssSelector(driver, url, "zoekFormulier", cssSelector, title);
    }

    @Test
    public void BloemTest_Overview_ToontActuelePagina() {
        navigationIsMarkedAsActual(driver, url, "overview","Overzicht");
    }


    @Test
    public void BloemTest_Overview_VerwijderKnopBevestigd_VerwijdertBloemEnToontOverviewPagina() {
        addBloemIfNotInbloemDomain(driver, url, geldigeNaam);

        driver.get(url + "BloemServlet?command=verwijder&naam="+geldigeNaam);
        String cssSelectorJa = ".kiekBloem > form:nth-child(3) > button:nth-child(1)";
        driver.findElement(By.cssSelector(cssSelectorJa)).click();

        ArrayList<WebElement> tds = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertEquals("Overzicht", driver.getTitle());
        assertFalse(containsWebElementsWithText(tds,geldigeNaam));
    }


    @Test
    public void BloemTest_Overview_VerwijderKnopGeannuleerd_VerwijdertBloemNietEnToontOverviewPagina() {
        addBloemIfNotInbloemDomain(driver, url, geldigeNaam);

        driver.get(url + "BloemServlet?command=verwijder&naam="+geldigeNaam);
        String cssSelectorNee = ".kiekBloem > form:nth-child(3) > button:nth-child(3)";
        driver.findElement(By.cssSelector(cssSelectorNee)).click();

        ArrayList<WebElement> tds = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertEquals("Overzicht", driver.getTitle());
        assertFalse(containsWebElementsWithText(tds,geldigeNaam));
    }
}
