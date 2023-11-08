package src.IslandLivingObject.Plants;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.Map;

public abstract class AbstractPlant implements IslandEntity {

    private int X;
    private int Y;

    public AbstractPlant(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    @Override
    public boolean isReproduced() {
        return false;
    }

    @Override
    public void setReproduced(boolean b) {

    }

    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return null;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public void die() {
        IslandField.getGameField()[this.getX()][this.getY()].remove(this);
    }
}