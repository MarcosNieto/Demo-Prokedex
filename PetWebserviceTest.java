package automatization.example;


import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import pojos.Pet;
import pojos.User;
import utils.GenericUtils;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class PetWebserviceTest {

    private static final String URL_BASE = "https://petstore.swagger.io/v2";
    private static final String USER_CREATION = "/user";
    private static final String USER_RECOVER = "/user/";
    private static final String PET_RECOVER = "/pet/findByStatus";

    @Test
    public void test() throws URISyntaxException {
        User user = createNewUser();
        registerUser(user);

        Pet[] pets = getPets();
        Map<String, List<Pet>> petsWithSameName = identifyPetsWithSameName(pets);

        for (Map.Entry<String, List<Pet>> entry : petsWithSameName.entrySet()) {
            System.out.println("Number of pets with name " + entry.getKey() + "=" + entry.getValue().size());
        }
    }

    /**
     * Using stream we group the List of pets by their name, in order to avoid grouping exceptions, we first filter
     * the pets excluding the pets without name.
     * @param pets pets
     * @return grouped pets by name.
     */
    private Map<String, List<Pet>> identifyPetsWithSameName(Pet[] pets) {
        return Arrays.stream(pets)
                .filter(pet -> Objects.nonNull(pet.getName()))
                .collect(Collectors.groupingBy(Pet::getName));
    }

    private Pet[] getPets() throws URISyntaxException {
        URIBuilder builder = new URIBuilder(URL_BASE + PET_RECOVER);
        builder.setParameter("status", "sold");
        Pet[] pets;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(builder.build());
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            switch (statusLine.getStatusCode()) {
                case 200:
                    String rawResponse = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                    pets = GenericUtils.deserialize(rawResponse, Pet[].class);
                    break;
                default:
                    throw new RuntimeException("Something went wrong");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pets;
    }

    private User createNewUser() {
        return new User(0, "Testusername", "TEst Firstname", "Test lastname", "test@gmail.com", "1234", "654323123", 0);
    }

    /**
     * Register a new user in Petstore backend
     *
     * @param user user
     */
    private void registerUser(User user) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URL_BASE + USER_CREATION);
        httpPost.setHeader("Content-type", "application/json");
        try {
            StringEntity stringEntity = new StringEntity(GenericUtils.serialize(user));
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            switch (statusLine.getStatusCode()) {
                case 200: //The user has been registered successfully in external backend.
                    recoverRegisteredUser(user.getUsername());
                    break;
                default:
                    throw new RuntimeException("Something went wrong");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * We request the previously created user to Petstore backend to ensure the data has been registered
     *
     * @param username username
     */
    private void recoverRegisteredUser(String username) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL_BASE + USER_RECOVER + username);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            switch (statusLine.getStatusCode()) {
                case 200: //The user has been recovered from the backend, now we proceed with deserialization in order to transform it to Java object.
                    String rawResponse = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                    User user = GenericUtils.deserialize(rawResponse, User.class);
                    break;
                default:
                    throw new RuntimeException("Something went wrong");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
