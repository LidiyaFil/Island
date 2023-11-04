package src.IslandLivingObject.Animals.Herbivorous;

import src.Island.Animal;
import src.IslandLivingObject.IslandEntity;

import java.util.List;

public abstract class Herbivorous extends Animal  {
    public Herbivorous(int steps, int capacity) {
        super(steps, capacity);
    }

    abstract public void eat(List<IslandEntity> entities);


    abstract public void die();

    abstract public void multiply(List<IslandEntity> entities);
}
