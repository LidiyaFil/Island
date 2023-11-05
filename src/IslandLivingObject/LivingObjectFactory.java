package src.IslandLivingObject;

import src.IslandLivingObject.Animals.Herbivorous.Sheep;
import src.IslandLivingObject.Animals.Predators.Wolf;

@Deprecated
public class LivingObjectFactory {
/*
    public IslandEntity createObject(IslandEntityType type) {
        IslandEntity livingObject = switch (type) {
            case WOLF -> {
                livingObject = new Wolf();
                yield livingObject;
            }
            case SHEEP -> {
                livingObject = new Sheep();
                yield livingObject;
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        return livingObject;
    }*/
}
//    ObjectMapper mapper = new YAMLMapper();
//
//    public IslandEntity createObject(IslandEntityType type) throws JsonProcessingException {
//        IslandEntity livingObject = switch (type) {
//            case WOLF -> {
//                livingObject = mapper.readValue("resources/wolf", Wolf.class);
//                yield livingObject;
//            }
//            case SHEEP -> {
//                livingObject = mapper.readValue("resources/sheep", Sheep.class);
//                yield livingObject;
//            }
//            case PLANT -> {
//                livingObject = mapper.readValue("resources/plant", Plant.class);
//                yield livingObject;
//            }
//        };
//        return livingObject;
//    }
