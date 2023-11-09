package src.IslandLivingObject.Plants;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public abstract class AbstractPlant implements IslandEntity {
    IslandField islandField = IslandField.getInstance();
    private int x;
    private int y;

    public AbstractPlant(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        x = x;
    }

    public int getY() {
        return y;
    }

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
