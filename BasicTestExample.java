package automatization.example;

import generic.AppiumTest;
import generic.Element;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class BasicTestExample extends AppiumTest {

    private static final String APP_PACKAGE = "com.locuthor.prodex";
    private static final String APP_ACTIVITY = "com.locuthor.prodex.SplashActivity";

    @Override
    protected void configureDriver() {
        super.configureDriver();
    }

    @BeforeTest
    public void setUp() {
        instantiateDriver(APP_PACKAGE, APP_ACTIVITY);
        configureDriver();
    }

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(6000); //Wait until splash screen is done
        Element spriteOpenButton = findElementWithId("iv_trainer_sprite_icon");
        Thread.sleep(6000);
        spriteOpenButton.click();
        Thread.sleep(6000);
        Element bottomSheetContainer = findElementWithId("design_bottom_sheet");
        Element recyclerView = findElementWithTag(bottomSheetContainer, "androidx.recyclerview.widget.RecyclerView");
        List<Element> trainers = recyclerView.getChildren();
        trainers.get(0).click();

        Element daySpinner = findElementWithId("actv_day");
        daySpinner.click();
        daySpinner.sendKeys("17");

        Element monthSpinner = findElementWithId("actv_month");
        monthSpinner.click();
        monthSpinner.sendKeys("11");

        Element monthYear = findElementWithId("actv_year");
        monthYear.click();
        monthYear.sendKeys("1997");

        findElementWithId("etTrainerName").sendKeys("Test");

        findElementWithId("bStartAdventure").click();

        Thread.sleep(4000); //Wait until next screen is loaded and displayed
        findElementWithId("guest_button").click();


        Thread.sleep(6000); //Wait until next screen is loaded and displayed

        findElementWithId("nav_pokedex").click();

        //WebElement a = bottomSheetContainer.getInternal();
        //List<WebElement> listLinearLayoutChildren = a.findElements(By.className("android.widget.LinearLayout"));
        // bottomSheetContainer.getInternal().findElements(By.xpath("//node()")).get(45).click();
    }

    public void scrollTo(String text) {
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")"));
    }

    @AfterTest
    public void tearDown() {
        if (Objects.nonNull(driver)) {
            driver.quit();
        }
    }

}