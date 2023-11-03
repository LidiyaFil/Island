package src.IslandLivingObject.Animals.Predators;

import com.fasterxml.jackson.annotation.JsonCreator;
import src.IslandLivingObject.Animals.AbstractAnimals;
import src.IslandLivingObject.IslandEntity;

import java.util.List;

public class Wolf extends AbstractAnimals {

    @JsonCreator
    public Wolf(int maxAmount, int maxMove) {
        super(maxAmount, maxMove);
    }

    @Override
    public void eat(List<IslandEntity> entities) {

    }

    @Override
    public void move() {

    }


    @Override
    public void multiply(List<IslandEntity> entities) {

    }
}
