package src.IslandLivingObject.Animals;

import src.Actions.MoveableReproducibleEatable;
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

public abstract class AbstractAnimal implements IslandEntity, MoveableReproducibleEatable {
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
    public boolean isReproduced() {
        return reprodused;
    }

    @Override
    public void setReproduced(boolean b) {
        this.reprodused = reprodused;
    }

    @Override
    public double getSaturation() {
        return saturation;
    }

    public void setSaturation(double newSaturation) {
        if (getSaturation() > 0) {
            this.saturation = newSaturation;
        } else {
            //удаляем объект с игрового поля, если животное голодает в начале хода
            die();
        }
    }

    @Override
    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return this.edibleSpecies = edibleSpecies;
    }



    @Override
    public void die() {
        islandField.getGameField()[this.getX()][this.getY()].remove(this);
    }

    @Override
    public String toString() {
        String builder = this.getType() +
                " (" + getX() + ", " + getY() + ")";
        return builder;
    }
}
