import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PetNameCounter {
    private List<Map<String, Object>> pets;

    public PetNameCounter(List<Map<String, Object>> pets) {
        this.pets = pets;
    }

    public Map<String, Integer> countPetNames() {
        Map<String, Integer> nameCount = new HashMap<>();
        for (Map<String, Object> pet : pets) {
            String name = (String) pet.get("name");
            nameCount.put(name, nameCount.getOrDefault(name, 0) + 1);
        }
        return nameCount;
    }
}