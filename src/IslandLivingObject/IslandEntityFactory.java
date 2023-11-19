package src.IslandLivingObject;

import src.IslandLivingObject.Animals.Herbivorous.*;
import src.IslandLivingObject.Animals.Predators.*;
import src.IslandLivingObject.Plants.Plant;

public class IslandEntityFactory {


    public IslandEntity createEntity(int x, int y, IslandEntityType entityType) {

        return mapIslandEntityType(x, y, entityType);
    }

    public IslandEntity mapIslandEntityType(int x, int y, IslandEntityType entityType) {
        return switch (entityType) {
            case WOLF -> new Wolf(x, y);
            case PYTHON -> new Python(x, y);
            case FOX -> new Fox(x, y);
            case BEAR -> new Bear(x, y);
            case EAGLE -> new Eagle(x, y);
            case HORSE -> new Horse(x, y);
            case DEER -> new Deer(x, y);
            case RABBIT -> new Rabbit(x, y);
            case MOUSE -> new Mouse(x, y);
            case GOAT -> new Goat(x, y);
            case SHEEP -> new Sheep(x, y);
            case WILD_BOAR -> new WildBoar(x, y);
            case BUFFALO -> new Buffalo(x, y);
            case DUCK -> new Duck(x, y);
            case CATERPILLAR -> new Caterpillar(x, y);
            case PLANT -> new Plant(x, y);
            default -> throw new IllegalArgumentException("Unknown entity type");
        };
    }
}
