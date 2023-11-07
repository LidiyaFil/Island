package src.IslandLivingObject.Animals;

import src.IslandLivingObject.Animals.Predators.Wolf;
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

                @Override
                public boolean isReproduced() {
                    return false;
                }

                @Override
                public void setReproduced(boolean b) {
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

    public IslandEntity mapAnimalTypeToIslandEntityType(IslandEntityType animalType) {
        return switch (animalType) {
            case WOLF -> new Wolf();
            case PYTHON -> IslandEntityType.PYTHON;
            case FOX -> IslandEntityType.FOX;
            case BEAR -> IslandEntityType.BEAR;
            case EAGLE -> IslandEntityType.EAGLE;
            case HORSE -> IslandEntityType.HORSE;
            case DEER -> IslandEntityType.DEER;
            case RABBIT -> IslandEntityType.RABBIT;
            case MOUSE -> IslandEntityType.MOUSE;
            case GOAT -> IslandEntityType.GOAT;
            case SHEEP -> IslandEntityType.SHEEP;
            case WILD_BOAR -> IslandEntityType.WILD_BOAR;
            case BUFFALO -> IslandEntityType.BUFFALO;
            case DUCK -> IslandEntityType.DUCK;
            case CATERPILLAR -> IslandEntityType.CATERPILLAR;
            default -> throw new IllegalArgumentException("Unknown animal type");
        };
    }
}
