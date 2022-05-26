package view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static view.Utill.containsWebElementsWithText;

public class voegToeTest {
    WebDriver driver;
    String url = "http://localhost:8080/EindOpdracht_war_exploded/BloemServlet?command=navigeerVoegToe";
   @Before
    public void setUp() throws Exception{
       WebDriverManager.chromedriver().setup();
       driver.get(url + "voegtoe.jsp");
   }



    @Test
    public void Test_Form_is_shown_again_with_error_message_if_all_fields_are_empty(){
    WebElement naamInput =driver.findElement(By.id("naam"));
        naamInput.clear();
        naamInput.sendKeys("");

        WebElement kleurInput =driver.findElement(By.id("color"));
        kleurInput.clear();
        kleurInput.sendKeys("");

        WebElement aantalInput =driver.findElement(By.id("flower"));
        aantalInput.clear();
        aantalInput.sendKeys("");

        driver.findElement(By.id("submit")).click();

        assertEquals("voeg bloem Toe",driver.getTitle());
        ArrayList<WebElement> list = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(list,"vul bloem naam in."));
        assertTrue(containsWebElementsWithText(list,"vul bloem Kleur in."));
        assertTrue(containsWebElementsWithText(list,"Geef aantal bloemen in."));

   }
    @Test
    public void test_Form_is_shown_again_with_error_messages_If_name_field_is_left_empty() {
        WebElement naamInput =driver.findElement(By.id("fname"));
        naamInput.clear();
        naamInput.sendKeys("");

        WebElement kleurInput =driver.findElement(By.id("fcolor"));
        kleurInput.clear();
        kleurInput.sendKeys("Rood");

        WebElement aantalInput =driver.findElement(By.id("flower"));
        aantalInput.clear();
        aantalInput.sendKeys("9");

        driver.findElement(By.id("submit")).click();
        assertEquals("Voeg een bloem toe", driver.getTitle());
        ArrayList<WebElement> lis =
                (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Vul een naam in."));
    }


    @Test
    public void test_Form_is_shown_again_with_error_messages_If_color_field_is_left_empty() {
        WebElement naamInput =driver.findElement(By.id("fname"));
        naamInput.clear();
        naamInput.sendKeys("Tulip");

        WebElement kleurInput =driver.findElement(By.id("fcolor"));
        kleurInput.clear();
        kleurInput.sendKeys("");

        WebElement aantalInput =driver.findElement(By.id("flower"));
        aantalInput.clear();
        aantalInput.sendKeys("9");

        driver.findElement(By.id("submit")).click();
        assertEquals("Voeg een bloem toe", driver.getTitle());
        ArrayList<WebElement> lis =
                (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Vul een kleur in."));
    }

    @Test
    public void test_Form_is_shown_again_with_error_messages_If_aantal_field_is_left_empty() {
        WebElement naamInput =driver.findElement(By.id("naam"));
        naamInput.clear();
        naamInput.sendKeys("Roos");

        WebElement kleurInput =driver.findElement(By.id("color"));
        kleurInput.clear();
        kleurInput.sendKeys("Rood");

        WebElement aantalInput =driver.findElement(By.id("flower"));
        aantalInput.clear();
        aantalInput.sendKeys("");

        driver.findElement(By.id("submit")).click();
        assertEquals("Voeg een bloem toe", driver.getTitle());
        ArrayList<WebElement> lis =
                (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Geef aantal bloemen in."));
    }
    @Test
    public void test_Form_is_shown_again_with_error_messages_If_aantal_field_is_negative() {
        WebElement naamInput =driver.findElement(By.id("naam"));
        naamInput.clear();
        naamInput.sendKeys("Roos");

        WebElement kleurInput =driver.findElement(By.id("color"));
        kleurInput.clear();
        kleurInput.sendKeys("Rood");

        WebElement aantalInput =driver.findElement(By.id("flower"));
        aantalInput.clear();
        aantalInput.sendKeys("-1");

        driver.findElement(By.id("submit")).click();
        assertEquals("Voeg een bloem toe", driver.getTitle());
        ArrayList<WebElement> lis =
                (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "getal mag niet null of negative zijn"));
    }

    @Test
    public void Test_Form_is_shown_again_with_error_message_if_all_fields_are_correctly(){
        WebElement naamInput =driver.findElement(By.id("naam"));
        naamInput.clear();
        naamInput.sendKeys("Tulip");

        WebElement kleurInput =driver.findElement(By.id("color"));
        kleurInput.clear();
        kleurInput.sendKeys("Rood");

        WebElement aantalInput =driver.findElement(By.id("flower"));
        aantalInput.clear();
        aantalInput.sendKeys("9");

        driver.findElement(By.id("submit")).click();

        assertEquals("Bekijk alle Bloemen",driver.getTitle());
        ArrayList<WebElement> tds = (ArrayList<WebElement>) driver.findElement(By.tagName("td"));
        assertTrue(containsWebElementsWithText(tds,"Tulip"));

    }
    @After
    public void clear(){
        driver.quit();
    }

}
