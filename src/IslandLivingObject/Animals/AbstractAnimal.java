package src.IslandLivingObject.Animals;

import src.Actions.Eateble;
import src.Actions.Moveable;
import src.Actions.Reproducible;
import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractAnimal implements IslandEntity, Eateble, Reproducible, Moveable {
    IslandField islandField = IslandField.getInstance();
    private int x;
    private int y;
    private boolean reprodused = false;
    private double saturation;
    private Map<IslandEntityType, Integer> edibleSpecies = new HashMap<>();

    public AbstractAnimal(int x, int y) {
        this.x = x;
        this.y = y;
        //инициализируем заполненность желудка 61% от максимально вместимости
        this.saturation = this.getType().getFullSaturation() * 0.61;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean isReproduced() {
        return reprodused;
    }

    @Override
    public void setReproduced(boolean b) {
        this.reprodused = b;
    }

    @Override
    public double getSaturation() {
        return saturation;
    }

    @Override
    public void setSaturation(double newSaturation) {
        this.saturation = newSaturation;
    }

    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return this.edibleSpecies = edibleSpecies;
    }

    public void doStarvation() {
        //отнимаем по 25% от максимальной вместимости желудка
        if (this.getSaturation() > 0) {
            this.setSaturation(this.getSaturation() - this.getType().getFullSaturation() / 2);
        } else {
            //удаляем объект с игрового поля, если животное голодает в начале хода
            this.die();
        }
    }

    public void die() {
        IslandField.getInstance().getGameField()[this.getX()][this.getY()].remove(this);
    }

    @Override
    public String toString() {
        String builder = String.valueOf(this.getType());
        return builder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return x == that.x
                && y == that.y
                && reprodused == that.reprodused
                && Double.compare(saturation, that.saturation) == 0
                && Objects.equals(islandField, that.islandField)
                && Objects.equals(edibleSpecies, that.edibleSpecies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(islandField, x, y, reprodused, saturation, edibleSpecies);
    }
}
