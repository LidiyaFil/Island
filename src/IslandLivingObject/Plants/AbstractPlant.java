package src.IslandLivingObject.Plants;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public abstract class AbstractPlant implements IslandEntity {
    IslandField islandField = IslandField.getInstance();
    private int x;
    private int y;

    // зачем нам этот конструктор?
    public AbstractPlant(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        y = y;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(this.getType()));
        builder.append(" (").append(getX()).append(", ").append(getY()).append(")");
        return builder.toString();
    }

    @Override
    public void die() {
        islandField.getGameField()[this.getX()][this.getY()].remove(this);
    }
}
