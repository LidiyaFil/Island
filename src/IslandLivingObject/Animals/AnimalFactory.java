package src.IslandLivingObject.Animals;

import src.IslandLivingObject.Animals.Herbivorous.*;
import src.IslandLivingObject.Animals.Predators.*;
import src.IslandLivingObject.EntityFactory;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.concurrent.ThreadLocalRandom;

public class AnimalFactory implements EntityFactory {
    IslandEntityType type = getRandomAnimalType();

    @Override
    public IslandEntity createEntity(int x, int y, IslandEntityType entityType) {
        return new AbstractAnimal() {
            @Override
            public void setX(int x) {
                super.setX(x);
            }
            @Override
            public void setY(int y) {
                super.setY(y);
            }
            @Override
            public IslandEntityType getType() {
                return entityType;
            }
        };
    }

    public IslandEntityType getRandomAnimalType() {
        int index = ThreadLocalRandom.current().nextInt(IslandEntityType.values().length);
        IslandEntityType randomType = IslandEntityType.values()[index];
        return mapAnimalTypeToIslandEntityType(randomType).getType();
    }

    public IslandEntity mapAnimalTypeToIslandEntityType(IslandEntityType animalType) {
        return switch (animalType) {
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
            default -> throw new IllegalArgumentException("Unknown animal type");
        };
    }
}
