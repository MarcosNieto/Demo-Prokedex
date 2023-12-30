package automatization.example;

import generic.AppiumTest;
import generic.Element;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class TeamBuilderTest1 extends AppiumTest {

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

        Element addTeamButton = findElementWithId("action_add");
        addTeamButton.click();
        Thread.sleep(1000);

        //Introduce team name

        Element editTeamName = findElementWithId("edit_name");
        editTeamName.click();
        Thread.sleep(1000);

        Element teamNameContainer = findElementWithId("input");
        teamNameContainer.sendKeys("Test Team 1");

        Thread.sleep(1000);

        findElementWithId("button1").click();
        Thread.sleep(1000);

        // Add pokemons to team

        addPokemonToTeam();
        Element pokemonSearch = findElementWithId("et_pokemon_search");
        pokemonSearch.sendKeys("Trevenant");
        Thread.sleep(1000);
        findElementWithText("709").click();
        Thread.sleep(1000);

        addPokemonToTeam();
        pokemonSearch.sendKeys("Gengar");
        Thread.sleep(1000);
        findElementWithText("094").click();
        Thread.sleep(1000);

        addPokemonToTeam();
        pokemonSearch.sendKeys("Rotom");
        Thread.sleep(1000);
        findElementWithText("479").click();
        Thread.sleep(1000);

        addPokemonToTeam();
        pokemonSearch.sendKeys("Sableye");
        Thread.sleep(1000);
        findElementWithText("302").click();
        Thread.sleep(1000);

        addPokemonToTeam();
        pokemonSearch.sendKeys("Decidueye");
        Thread.sleep(1000);
        findElementWithText("724").click();
        Thread.sleep(1000);

        addPokemonToTeam();
        pokemonSearch.sendKeys("Mimikyu");
        Thread.sleep(1000);
        findElementWithText("778").click();
        Thread.sleep(1000);

        //Check type and attacks coverages

        Element typeCoverage = findElementWithText("COBERTURA TIPOS");
        typeCoverage.click();
        Thread.sleep(3000);

        Element AtkCoverage = findElementWithText("COBERTURA ATAQUES");
        AtkCoverage.click();
        Thread.sleep(3000);

    }

    public void addPokemonToTeam() throws InterruptedException {

        Element addPokemonBuildButton = findElementWithId("add_button");
        addPokemonBuildButton.click();
        Thread.sleep(1000);

        Element addPokemonButton = findElementWithId("add_pokemon_button");
        addPokemonButton.click();
        Thread.sleep(1000);

    }

    public void createTrainerAndGoToTeambuilder() throws InterruptedException {

        Thread.sleep(3000);//Wait until splash screen is done
        Element spriteOpenButton = findElementWithId("iv_trainer_sprite_icon");
        Thread.sleep(5000);
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