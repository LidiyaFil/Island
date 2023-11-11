package src.IslandLivingObject.Plants;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractPlant implements IslandEntity {
    IslandField islandField = IslandField.getInstance();
    private int x;
    private int y;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPlant that = (AbstractPlant) o;
        return x == that.x && y == that.y && Objects.equals(islandField, that.islandField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(islandField, x, y);
    }
}
