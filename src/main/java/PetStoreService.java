import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetStoreService {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    public static List<Map<String, Object>> getPetsByStatus(String status) throws IOException {
        String response = ApiClient.sendGetRequest(BASE_URL + "/pet/findByStatus?status=" + status);
        return new Gson().fromJson(response, new TypeToken<List<Map<String, Object>>>(){}.getType());
    }

    public static List<Map<String, Object>> getSoldPets() throws IOException {
        List<Map<String, Object>> pets = getPetsByStatus("sold");
        List<Map<String, Object>> soldPetsList = new ArrayList<>();

        for (Map<String, Object> pet : pets) {
            if (pet.containsKey("id") && pet.containsKey("name")) {
                Map<String, Object> petData = new HashMap<>();
                petData.put("id", pet.get("id"));
                petData.put("name", pet.get("name"));
                soldPetsList.add(petData);
            }
        }
        return soldPetsList;
    }
}