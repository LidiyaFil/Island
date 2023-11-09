package src.IslandLivingObject;

import src.IslandLivingObject.Animals.Herbivorous.*;
import src.IslandLivingObject.Animals.Predators.*;
import src.IslandLivingObject.Plants.Plant;

import java.util.concurrent.ThreadLocalRandom;

public class AbstractFactory {
    private int x;
    private int y;

    public IslandEntity createEntity(int x, int y, IslandEntityType entityType) {
        this.x = x;
        this.y = y;
        return mapAnimalTypeToIslandEntityType(entityType);
    }

    public IslandEntityType getRandomEntityType() {
        int index = ThreadLocalRandom.current().nextInt(IslandEntityType.values().length);
        IslandEntityType randomType = IslandEntityType.values()[index];
        return mapAnimalTypeToIslandEntityType(randomType).getType();
    }

    public IslandEntity mapAnimalTypeToIslandEntityType(IslandEntityType entityType) {

        return switch (entityType) {
            case WOLF -> new Wolf();
            case PYTHON -> new Python();
            case FOX -> new Fox();
            case BEAR -> new Bear();
            case EAGLE -> new Eagle();
            case HORSE -> new Horse();
            case DEER -> new Deer();
            case RABBIT -> new Rabbit();
            case MOUSE -> new Mouse();
            case GOAT -> new Goat();
            case SHEEP -> new Sheep();
            case WILD_BOAR -> new WildBoar();
            case BUFFALO -> new Buffalo();
            case DUCK -> new Duck();
            case CATERPILLAR -> new Caterpillar();
            case PLANT -> new Plant(x, y);
            default -> throw new IllegalArgumentException("Unknown entity type");
        };
    }
}
