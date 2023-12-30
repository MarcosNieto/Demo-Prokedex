package automatization.example;

import generic.AppiumTest;
import generic.Element;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class PokedexTestPokemonNormalByWeight extends AppiumTest {

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

        createTrainerAndGoToPokedex();

        scrollToAllVersions();

        //Select normal type and filter by weight

        Element typeSpot1 = findElementWithId("tv_type_1");
        typeSpot1.click();
        Thread.sleep(2000);

        Element normalType = findElementWithText("NORMAL");
        normalType.click();
        Thread.sleep(2000);

        Element sortMenuButton = findElementWithId("fab_sort_menu");
        sortMenuButton.click();
        Thread.sleep(2000);

        Element sortByWeight = findElementWithId("rg_bottom_sheet_sort_by_weight");
        sortByWeight.click();
        Thread.sleep(2000);

        Element sortApply = findElementWithId("b_bottom_sheet_sort_apply");
        sortApply.click();
        Thread.sleep(8000);

    }

    public void createTrainerAndGoToPokedex() throws InterruptedException {

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

        findElementWithId("nav_pokedex").click();

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