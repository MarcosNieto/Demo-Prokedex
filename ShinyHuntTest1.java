package automatization.example;

import generic.AppiumTest;
import generic.Element;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class ShinyHuntTest1 extends AppiumTest {

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

        createTrainerAndGoToShinyHunt();
        Thread.sleep(1000);

        scrollToSilver();
        Thread.sleep(1000);

        //Add shiny hunt

        Element addShinyHuntButton = findElementWithId("action_add");
        addShinyHuntButton.click();
        Thread.sleep(1000);

        Element pokemonSearchContainer = findElementWithId("et_pokemon_search");
        pokemonSearchContainer.sendKeys("Heracross");
        Thread.sleep(1000);
        findElementWithText("214").click();
        Thread.sleep(2000);

        //Testing shiny hunt functions

        Element attemptsPreview = findElementWithId("tvAttempts");
        attemptsPreview.click();
        Thread.sleep(2000);

        Element attemptsContainer = findElementWithId("et_attempts");
        attemptsContainer.sendKeys("100");
        Thread.sleep(2000);

        //Test location and go back from routes

        Element locationsButton = findElementWithId("b_location");
        locationsButton.click();
        Thread.sleep(3000);

        findElementWithText("Ruta 29").click();
        Thread.sleep(3000);
        navigateBack();
        Thread.sleep(3000);
        navigateBack();

        //Introduce notes and click on catched

        findElementWithId("et_notes").sendKeys("Test notes");
        Thread.sleep(2000);

        findElementWithId("b_caught").click();
        Thread.sleep(6000);

    }

    public void createTrainerAndGoToShinyHunt() throws InterruptedException {

        Thread.sleep(3000);//Wait until splash screen is done
        Element spriteOpenButton = findElementWithId("iv_trainer_sprite_icon");
        Thread.sleep(3000);
        spriteOpenButton.click();
        Thread.sleep(3000);

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

        findElementWithId("nav_shiny").click();

        //WebElement a = bottomSheetContainer.getInternal();
        //List<WebElement> listLinearLayoutChildren = a.findElements(By.className("android.widget.LinearLayout"));
        // bottomSheetContainer.getInternal().findElements(By.xpath("//node()")).get(45).click();

    }

    public void scrollToSilver() throws InterruptedException {

        findElementWithId("tv_games").click();

        Thread.sleep(2000);
        findElementWithText("PLATA").click();

    }

    @AfterTest
    public void tearDown() {
        if (Objects.nonNull(driver)) {
            driver.quit();
        }
    }

}