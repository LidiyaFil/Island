package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntity;

import java.util.List;

public abstract class Predators {

    abstract public void eat(List<IslandEntity> entities);

    abstract public void move();

    abstract public void die();

    abstract public void multiply(List<IslandEntity> entities);
}
