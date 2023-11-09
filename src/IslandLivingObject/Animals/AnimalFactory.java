package src.IslandLivingObject.Animals;

import src.IslandLivingObject.Animals.Herbivorous.*;
import src.IslandLivingObject.Animals.Predators.*;
import src.IslandLivingObject.EntityFactory;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalFactory implements EntityFactory {


    @Override
    public IslandEntity createEntity(int x, int y, IslandEntityType entityType) {
        AbstractAnimal abstractAnimal = new AbstractAnimal() {
            @Override
            public IslandEntityType getType() {
                return entityType;
            }
        };
        return mapAnimalTypeToIslandEntityType(entityType);
    }
    public IslandEntityType getRandomAnimalType() {
        int index = ThreadLocalRandom.current().nextInt(IslandEntityType.values().length);
        IslandEntityType randomType = IslandEntityType.values()[index];
        return mapAnimalTypeToIslandEntityType(randomType).getType();
    }

    public IslandEntity mapAnimalTypeToIslandEntityType(IslandEntityType entityType) {
        if (entityType == IslandEntityType.WOLF) {
            return new Wolf();
        }


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
            default -> throw new IllegalArgumentException("Unknown animal type");
        };
    }
}
