package automatization.example;

import generic.AppiumTest;
import generic.Element;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class CatchedTest1 extends AppiumTest {

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

        createTrainerAndGoToCatched();
        Thread.sleep(1000);

        scrollToAllVersions();
        Thread.sleep(1000);

        //Add pokemons to "catched"

        addPokemonsToCatched();

        //Click on more options and check them

        openMoreOptionsButton();
        findElementWithText("Consejos").click();
        Thread.sleep(2000);

        //Use android button to go back
        navigateBack();
        Thread.sleep(2000);

        openMoreOptionsButton();
        findElementWithText("Mostrar no capturados").click();
        Thread.sleep(4000);

        openMoreOptionsButton();
        findElementWithText("Mostrar todos").click();
        Thread.sleep(4000);

        openMoreOptionsButton();
        findElementWithText("Marcar todos").click();
        Thread.sleep(10000);

        openMoreOptionsButton();
        findElementWithText("Desmarcar todos").click();
        Thread.sleep(5000);

    }

    public void openMoreOptionsButton() throws InterruptedException {

        findElementWithContentDescription("Más opciones").click();
        Thread.sleep(2000);

    }

    public void addPokemonsToCatched() throws InterruptedException {

        Element searchButton = findElementWithId("action_search");
        searchButton.click();
        Thread.sleep(1000);

        Element searchContainer = findElementWithId("search_src_text");

        searchContainer.sendKeys("Bulbasaur");
        Thread.sleep(1000);
        findElementWithText("001").click();
        Thread.sleep(1000);

        searchContainer.sendKeys("Chikorita");
        Thread.sleep(1000);
        findElementWithText("152").click();
        Thread.sleep(1000);

        searchContainer.sendKeys("Treecko");
        Thread.sleep(1000);
        findElementWithText("252").click();
        Thread.sleep(1000);

        searchContainer.sendKeys("Turtwig");
        Thread.sleep(1000);
        findElementWithText("387").click();
        Thread.sleep(1000);

        searchContainer.sendKeys("Snivy");
        Thread.sleep(1000);
        findElementWithText("495").click();
        Thread.sleep(1000);

        searchContainer.sendKeys("Chespin");
        Thread.sleep(1000);
        findElementWithText("650").click();
        Thread.sleep(1000);

        searchContainer.sendKeys("Rowlet");
        Thread.sleep(1000);
        findElementWithText("722").click();
        Thread.sleep(1000);

        searchContainer.sendKeys("Grookey");
        Thread.sleep(1000);
        findElementWithText("810").click();
        Thread.sleep(1000);

        searchContainer.sendKeys("Sprigatito");
        Thread.sleep(1000);
        findElementWithText("906").click();
        Thread.sleep(1000);

    }

    public void createTrainerAndGoToCatched() throws InterruptedException {

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

        findElementWithId("nav_catched").click();

        //WebElement a = bottomSheetContainer.getInternal();
        //List<WebElement> listLinearLayoutChildren = a.findElements(By.className("android.widget.LinearLayout"));
        // bottomSheetContainer.getInternal().findElements(By.xpath("//node()")).get(45).click();

    }

    public void scrollToAllVersions() throws InterruptedException {

        findElementWithId("tv_games").click();

        Thread.sleep(2000);
        findElementWithText("TODAS LAS VERSIONES").click();


    }

    @AfterTest
    public void tearDown() {
        if (Objects.nonNull(driver)) {
            driver.quit();
        }
    }

}