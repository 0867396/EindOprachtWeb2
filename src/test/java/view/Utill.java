package view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Utill {
    public static void fillOutAddForm(WebDriver driver, String url, String naam) {
        driver.get(url + "BloemServlet?command=navigeerVoegToe");
        driver.findElement(By.id("naam")).clear();
        driver.findElement(By.id("naam")).sendKeys(naam);
        driver.findElement(By.id("color")).clear();
        driver.findElement(By.id("submit")).click();
    }

    public static void clickOnElementByCssSelector(WebDriver driver, String url, String command, String cssSelector, String title) {
        driver.get(url + "BloemServlet?command=" + command);
        driver.findElement(By.cssSelector(cssSelector)).click();
        assertEquals(title, driver.getTitle());
    }

    public static void fillOutSearchForm(WebDriver driver, String url, String naam) {
        driver.get(url + "BloemServlet?command=zoekFormulier");
        driver.findElement(By.id("naam")).clear();
        driver.findElement(By.id("naam")).sendKeys(naam);
        driver.findElement(By.id("submit")).click();
    }

    public static  void addBloemIfNotInbloemDomain(WebDriver driver, String url, String naam) {
        driver.get(url + "BloemServlet?command=overview");
        ArrayList<WebElement> tds = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        if (!containsWebElementsWithText(tds, naam)) {
            driver.get(url + "BloemServlet?command=navigeerVoegToe");
            fillOutAddForm(driver, url, naam);
        }
    }

    public static  void deleteBloemIfInDomain(WebDriver driver, String url, String naam) {
        driver.get(url + "BloemServlet?command=overview");
        ArrayList<WebElement> tds = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        if (containsWebElementsWithText(tds, naam)) {
            driver.get(url + "BloemServlet?command=verwijder&naam=");
        }
    }

    public static  void navigationIsMarkedAsActual(WebDriver driver, String url, String command, String title) {
        driver.get(url + "BloemServlet?command=" + command);
        WebElement hier = driver.findElement(By.id("actual"));
        assertEquals(title,hier.getText());
    }

    public static  void htmlIsValid(WebDriver driver, String url, String command){
        driver.get(url + "BloemServlet?command=" + command);

        String pageSource = driver.getPageSource();

        driver.get("https://validator.w3.org/#validate_by_input");

        WebElement textarea = driver.findElement(By.id("fragment"));
        textarea.clear();
        textarea.click();
        textarea.sendKeys("<!DOCTYPE html>"+pageSource);
        driver.findElement(By.cssSelector("#validate-by-input > form > p.submit_button > a")).click();

        assertEquals("Document checking completed. No errors or warnings to show.",driver.findElement(By.cssSelector(".success")).getText());
    }

    public static  void htmlIsValidByPageSource(WebDriver driver, String pageSource){
        driver.get("https://validator.w3.org/#validate_by_input");

        WebElement textarea = driver.findElement(By.id("fragment"));
        textarea.clear();
        textarea.click();
        textarea.sendKeys("<!DOCTYPE html>"+pageSource);
        driver.findElement(By.cssSelector("#validate-by-input > form > p.submit_button > a")).click();

        assertEquals("Document checking completed. No errors or warnings to show.",driver.findElement(By.cssSelector(".success")).getText());
    }


    public static boolean containsWebElementsWithText(ArrayList<WebElement> list, String text) {
        for(int i = 0; i< list.size();i++){
            if(list.get(i).getText().equals(text)){
                return true;
            }
        }
        return false;
    }
}
