package src.IslandLivingObject.Animals;

import src.IslandLivingObject.EntityFactory;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;


import java.util.concurrent.ThreadLocalRandom;

public class AnimalFactory implements EntityFactory {
    @Override
    public IslandEntity createEntity() {
        IslandEntity entity = null;
        IslandEntityType type = getRandomAnimalType();
        if (type != null) {

            //TODO переделать на абстрактное животное?
            entity = new Animal(type);
        }
        return entity;
    }


    public IslandEntityType getRandomAnimalType() {
        int index = ThreadLocalRandom.current().nextInt(IslandEntityType.values().length);
        IslandEntityType randomType = IslandEntityType.values()[index];
        return mapAnimalTypeToIslandEntityType(randomType);
    }

    //TODO добавить  классы. Подумать на счет того, где записать характеристики объектов
    public IslandEntityType mapAnimalTypeToIslandEntityType(IslandEntityType animalType) {
        switch (animalType) {
            case Wolf:
                return IslandEntityType.WOLF;
            case Python:
                return IslandEntityType.SNAKE;
            case Fox:
                return IslandEntityType.FOX;
            case Bear:
                return IslandEntityType.BEAR;
            case Eagle:
                return IslandEntityType.EAGLE;
            case Horse:
                return IslandEntityType.HORSE;
            case Deer:
                return IslandEntityType.DEER;
            case Rabbit:
                return IslandEntityType.RABBIT;
            case Mouse:
                return IslandEntityType.MOUSE;
            case Goat:
                return IslandEntityType.GOAT;
            case Sheep:
                return IslandEntityType.SHEEP;
            case WildBoar:
                return IslandEntityType.WILD_BOAR;
            case Buffalo:
                return IslandEntityType.BUFFALO;
            default:
                throw new IllegalArgumentException("Unknown animal type");
        }
    }
}
