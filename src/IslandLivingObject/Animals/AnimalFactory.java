package src.IslandLivingObject.Animals;

import src.IslandLivingObject.EntityFactory;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalFactory implements EntityFactory {
    @Override
    public IslandEntity createEntity(IslandEntityType entityType) {
        IslandEntity entity = null;
        IslandEntityType type = getRandomAnimalType();
        if (type != null) {
            entity = new AbstractAnimal(type) {
                @Override
                public Map<IslandEntityType, Integer> getEdibleSpecies() {
                    return null;   //TODO вот тут надо обсудить что происходит
                }
                @Override
                public IslandEntityType getType() {
                    return type;
                }
            };
        }
        return entity;
    }


    public IslandEntityType getRandomAnimalType() {
        int index = ThreadLocalRandom.current().nextInt(IslandEntityType.values().length);
        IslandEntityType randomType = IslandEntityType.values()[index];
        return mapAnimalTypeToIslandEntityType(randomType);
    }
    public IslandEntityType mapAnimalTypeToIslandEntityType(IslandEntityType animalType) {
        return switch (animalType) {
            case WOLF -> IslandEntityType.WOLF;
            case PYTHON -> IslandEntityType.PYTHON;
            case FOX -> IslandEntityType.FOX;
            case Bear -> IslandEntityType.Bear;
            case Eagle -> IslandEntityType.Eagle;
            case Horse -> IslandEntityType.Horse;
            case Deer -> IslandEntityType.Deer;
            case Rabbit -> IslandEntityType.Rabbit;
            case Mouse -> IslandEntityType.Mouse;
            case Goat -> IslandEntityType.Goat;
            case Sheep -> IslandEntityType.Sheep;
            case WildBoar -> IslandEntityType.WildBoar;
            case Buffalo -> IslandEntityType.Buffalo;
            case Duck -> IslandEntityType.Duck;
            case Caterpillar -> IslandEntityType.Caterpillar;
            default -> throw new IllegalArgumentException("Unknown animal type");
        };
    }
}
