package automatization.example;

import generic.Element;
import generic.SeleniumTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class WikipediaTest extends SeleniumTest {

    @BeforeTest
    public void setUp() {
        instantiateDriver();
        configureDriver();
    }

    @Test
    public void test() throws AWTException {
        driver.get("https://www.google.es/");

        closeCookies();

        Element inputBar = findElementContainingValueForAttribute("Buscar", "title");
        inputBar.sendKeys("automatizacion");

        Robot robot = new Robot();
        pressKey(robot, KeyEvent.VK_ENTER);

        removeElementFromWebElement(findElementWithId("searchform"));

        Element wikipediaEntry = findElementWithText("Automatización - Wikipedia, la enciclopedia libre");
        scrollToElementAndClick(wikipediaEntry);

        Element firstAutomationMentionParagraphy = findElementWithText("En 1771 ");
        scrollTo(firstAutomationMentionParagraphy);

        System.out.println(firstAutomationMentionParagraphy.getText());

        takeScreenshot("wikipedia_first_automation_mention.png");
    }

    private void closeCookies() {
        findElementsWithText("Aceptar todo").stream().filter(element -> element.getText().equals("Aceptar todo")).findAny().orElse(null).click();
    }

    @AfterTest
    public void tearDown() {
        if (Objects.nonNull(driver)) {
            driver.quit();
        }
    }

}