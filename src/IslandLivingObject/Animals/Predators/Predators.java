package src.IslandLivingObject.Animals.Predators;

import src.Island.Animal;
import src.IslandLivingObject.IslandEntity;

import java.util.List;

public abstract class Predators extends Animal {

    public Predators(int steps, int capacity) {
        super(steps, capacity);
    }

    abstract public void eat(List<IslandEntity> entities);


    abstract public void die();

    abstract public void multiply(List<IslandEntity> entities);
}
