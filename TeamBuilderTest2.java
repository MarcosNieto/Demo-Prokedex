package automatization.example;

import generic.AppiumTest;
import generic.Element;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class TeamBuilderTest2 extends AppiumTest {

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

        introduceTeamName();
        Thread.sleep(1000);

        // Add pokemons to team

        findElementWithId("button1").click();
        Thread.sleep(1000);

        addPokesToTeam();

        //Replaces two pokemons (remove button)

        findElementWithText("560").click();
        Thread.sleep(1000);
        Element removePkmButton = findElementWithId("b_remove_poke");
        removePkmButton.click();
        Thread.sleep(1000);
        Element acceptButton = findElementWithId("button1");
        acceptButton.click();

        findElementWithText("369").click();
        Thread.sleep(1000);
        Element removePkmButtonTeam = findElementWithId("b_remove_poke");
        removePkmButtonTeam.click();
        Thread.sleep(1000);
        Element acceptButtonRemove = findElementWithId("button1");
        acceptButtonRemove.click();
        Thread.sleep(3000);

       //Add two new pokemons to team

        addNewPokesToTeam();
        Thread.sleep(3000);

    }

    public void addPokesToTeam() throws InterruptedException {

        addPokemonToTeam();
        Element pokemonSearch = findElementWithId("et_pokemon_search");
        pokemonSearch.sendKeys("Heracross");
        Thread.sleep(1000);
        findElementWithText("214").click();
        Thread.sleep(1000);

        addPokemonToTeam();
        pokemonSearch.sendKeys("Scrafty");
        Thread.sleep(1000);
        findElementWithText("560").click();
        Thread.sleep(1000);

        addPokemonToTeam();
        pokemonSearch.sendKeys("Relicanth");
        Thread.sleep(1000);
        findElementWithText("369").click();
        Thread.sleep(1000);

    }

    public void addNewPokesToTeam() throws InterruptedException {

        addPokemonToTeam();
        Element pokemonSearch = findElementWithId("et_pokemon_search");
        pokemonSearch.sendKeys("Spinda");
        Thread.sleep(1000);
        findElementWithText("327").click();
        Thread.sleep(1000);

        addPokemonToTeam();
        pokemonSearch.sendKeys("Electivire");
        Thread.sleep(1000);
        findElementWithText("466").click();
        Thread.sleep(1000);

    }

    public void introduceTeamName() throws InterruptedException {

        Element editTeamName = findElementWithId("edit_name");
        editTeamName.click();
        Thread.sleep(1000);

        Element teamNameContainer = findElementWithId("input");
        teamNameContainer.sendKeys("Test Team 2");

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