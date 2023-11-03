package src.IslandLivingObject.Animals;

import com.fasterxml.jackson.annotation.JsonCreator;
import src.IslandLivingObject.IslandEntity;

import java.util.List;
import java.util.Objects;

public abstract class AbstractAnimals implements IslandEntity {
    private final int maxAmount;
    private final int maxMove;

    @JsonCreator
    public AbstractAnimals(int maxAmount, int maxMove) {
        this.maxAmount = maxAmount;
        this.maxMove = maxMove;
    }

    protected abstract void eat(List<IslandEntity> entities);

    public abstract void move();

    public void multiply(List<IslandEntity> entities) {
        for (IslandEntity entity : entities) {
            int count;

        }
        ;

    }
}
