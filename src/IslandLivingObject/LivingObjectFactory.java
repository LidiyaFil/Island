package src.IslandLivingObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import src.IslandLivingObject.Animals.Herbivorous.Sheep;
import src.IslandLivingObject.Animals.Predators.Wolf;
import src.IslandLivingObject.Plants.Plant;

public class LivingObjectFactory {

    ObjectMapper mapper = new YAMLMapper();

    public IslandEntity createObject(IslandEntityType type) throws JsonProcessingException {
        IslandEntity livingObject = switch (type) {
            case WOLF -> {
                livingObject = mapper.readValue("resources/wolf", Wolf.class);
                yield livingObject;
            }
            case SHEEP -> {
                livingObject = mapper.readValue("resources/sheep", Sheep.class);
                yield livingObject;
            }
            case PLANT -> {
                livingObject = mapper.readValue("resources/plant", Plant.class);
                yield livingObject;
            }
        };
        return livingObject;
    }
}
