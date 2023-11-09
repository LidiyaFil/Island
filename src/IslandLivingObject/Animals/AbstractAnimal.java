package src.IslandLivingObject.Animals;

import src.Island.IslandField;
import src.IslandLivingObject.Animals.Herbivorous.Herbivorous;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.Plants.AbstractPlant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractAnimal implements IslandEntity {
    IslandField islandField = IslandField.getInstance();
    private int x;
    private int y;
    private boolean reprodused = false;
    private double saturation;
    private Map<IslandEntityType, Integer> edibleSpecies = new HashMap<>();

    public AbstractAnimal() {
        //инициализируем заполненность желудка 50% от максимально вместимости
        this.saturation = this.getSaturation() / 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        x = x;
    }

    public void setY(int y) {
        y = y;
    }

    public boolean isReproduced() {
        return reprodused;
    }

    public void setReproduced(boolean b) {
        this.reprodused = reprodused;
    }

    public double getSaturation() {
        return saturation;
    }

    private void setSaturation(AbstractAnimal abstractAnimal) {
        //отнимаем по 25% от максимальной вместимости желудка
        if (getSaturation() > 0) {
            this.saturation = getSaturation() - abstractAnimal.getSaturation() / 4;
        } else {
            //удаляем объект с игрового поля, если животное голодает в начале хода
            die();
        }
    }

    protected Map<IslandEntityType, Integer> getEdibleSpecies() {
        return this.edibleSpecies = edibleSpecies;
    }

    public void die() {
        islandField.getGameField()[this.getX()][this.getY()].remove(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(this.getType()));
        builder.append(" (").append(getX()).append(", ").append(getY()).append(")");
        return builder.toString();
    }
}
