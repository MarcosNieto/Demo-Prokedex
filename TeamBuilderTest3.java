package automatization.example;

import generic.AppiumTest;
import generic.Element;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class TeamBuilderTest3 extends AppiumTest {

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

        createTrainerAndGoToTeambuilder();
        Thread.sleep(1000);

        scrollToAllVersions();
        Thread.sleep(1000);

        Element myBuildsButton = findElementWithText("MIS BUILDS");
        myBuildsButton.click();
        Thread.sleep(1000);

        Element createBuild = findElementWithId("action_add");
        createBuild.click();
        Thread.sleep(1000);

        //Add Heracross

        Element pokemonSearch = findElementWithId("et_pokemon_search");
        pokemonSearch.sendKeys("Heracross");
        Thread.sleep(1000);
        findElementWithText("214").click();
        Thread.sleep(1000);

        findElementWithText("Heracross").click();
        Thread.sleep(1000);

        //Test the build creator making Heracross competitive

        //Shiny/not shiny option
        Element shinySlider = findElementWithId("sm_shiny");
        shinySlider.click();
        Thread.sleep(1000);

        //Female option
        Element femaleButton = findElementWithId("tb_female");
        femaleButton.click();
        Thread.sleep(1000);

        //Male option
        Element maleButton = findElementWithId("tb_male");
        maleButton.click();
        Thread.sleep(1000);

        //Increase/decrease level (0 to 100)
        Element levelEditorContainer = findElementWithId("et_pokemon_editor_level_title");
        levelEditorContainer.sendKeys("100");
        Thread.sleep(1000);

        //Increase/decrease Happiness (0 to 255)
        Element happinessEditorContainer = findElementWithId("tv_pokemon_editor_happiness_title");
        happinessEditorContainer.sendKeys("255");
        Thread.sleep(1000);

        //Continue with ability, nature and item when scroll method is implemented

        findElementWithText("Autoestima").click();
        Thread.sleep(1000);

        findElementWithId("tv_pokemon_editor_nature").click();
        Thread.sleep(1000);

        findElementWithText("FIRME (-ATQ.SPE +ATQ)").click();
        Thread.sleep(1000);

        findElementWithId("tv_pokemon_editor_item").click();
        Thread.sleep(1000);

        findElementWithId("et_item_search").sendKeys("Pañuelo elección");

        findElementWithId("tv_item").click();
        Thread.sleep(1000);

        //Add moves
        //Go to "moves" page
        Element movesPage = findElementWithText("MOVIMIENTOS");
        movesPage.click();
        Thread.sleep(1000);

        //Add moves
        Element addMoveButton = findElementWithId("fab_add_move");

        addMoveButton.click();
        Thread.sleep(1000);

        Element searchMoveContainer = findElementWithId("et_search");

        searchMoveContainer.sendKeys("A Bocajarro");
        findElementWithId("tv_list_item_move_name").click();
        Thread.sleep(1000);

        addMoveButton.click();
        Thread.sleep(1000);
        searchMoveContainer.sendKeys("Megacuerno");
        findElementWithId("tv_list_item_move_name").click();
        Thread.sleep(1000);

        addMoveButton.click();
        Thread.sleep(1000);
        searchMoveContainer.sendKeys("Roca Afilada");
        findElementWithId("tv_list_item_move_name").click();
        Thread.sleep(1000);

        addMoveButton.click();
        Thread.sleep(1000);
        searchMoveContainer.sendKeys("Terremoto");
        findElementWithId("tv_list_item_move_name").click();
        Thread.sleep(1000);

        //Go to "EVS" page

        Element evsPage = findElementWithText("EVS");
        evsPage.click();
        Thread.sleep(1000);

        //Change evs to max.

        findElementWithId("sb_atk_ev").click();
        Thread.sleep(2000);

        findElementWithId("sb_speed_ev").click();
        Thread.sleep(2000);

        //Change ivs to max.

        Element ivsPage = findElementWithText("IVS");
        ivsPage.click();
        Thread.sleep(1000);

    }

    public void introduceTeamName() throws InterruptedException {

        Element editTeamName = findElementWithId("edit_name");
        editTeamName.click();
        Thread.sleep(1000);

        Element teamNameContainer = findElementWithId("input");
        teamNameContainer.sendKeys("Test Team 2");

    }

    public void createTrainerAndGoToTeambuilder() throws InterruptedException {

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

        findElementWithId("nav_teambuilder").click();

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