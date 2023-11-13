package src.IslandLivingObject.Animals;

import src.Island.IslandField;
import src.IslandLivingObject.Animals.Predators.Predators;
import src.IslandLivingObject.IslandEntity;
import src.IslandLivingObject.IslandEntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractAnimal implements IslandEntity {
    IslandField islandField = IslandField.getInstance();
    private int x;
    private int y;
    private boolean reproduced = false;
    private double saturation;

    private final Map<IslandEntityType, Integer> edibleSpecies = new HashMap<>();

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

    public boolean isReproduced() {
        return reproduced;
    }

    public void setReproduced(boolean b) {
        this.reproduced = b;
    }

    public double getSaturation() {
        return saturation;
    }

    public void setSaturation(double newSaturation) {
        this.saturation = newSaturation;
    }


    public Map<IslandEntityType, Integer> getEdibleSpecies() {
        return this.edibleSpecies;
    }

    public void doStarvation() {
        //отнимаем по 50% от максимальной вместимости желудка
        if (this.getSaturation() > 0) {
//            System.out.println("голодает " + this + "его сатурация была " + this.getSaturation());
           /* if (this instanceof Predators) {
                if (this.getEdibleSpecies().containsKey(IslandEntityType.PLANT)) {
                    this.setSaturation(this.getSaturation() - this.getType().getFullSaturation() * 0.4);
                } else {
                    this.setSaturation(this.getSaturation() - this.getType().getFullSaturation() * 0.53);
                }
            } else {
                this.setSaturation(this.getSaturation() - this.getType().getFullSaturation() * 0.6);
            }*/
            this.setSaturation(this.getSaturation() - this.getType().getFullSaturation() * 0.53);
            if (this.getSaturation() <= 0)
//            System.out.println("стала " + this.getSaturation());
            if (this.getSaturation() <= 0) {
                this.die();
            }
        } else {
            //удаляем объект с игрового поля, если животное голодает в начале хода
            this.die();
        }
    }

    public void die() {
        int oldIslandEntityX = this.getX();
        int oldIslandEntityY = this.getY();
//        System.out.println("method die" + this);
//        System.out.println("удаляем" + this);
        IslandField.getInstance().getGameField()[oldIslandEntityX][oldIslandEntityY].remove(this);
//        System.out.println("клетка содержит удаленное животное? - "
//                + IslandField.getInstance().getGameField()[oldIslandEntityX][oldIslandEntityY].contains(this));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getType()).append("(" + this.getX() + ", " + this.getY() + ")");
        return String.valueOf(builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return x == that.x
                && y == that.y
                && reproduced == that.reproduced
                && Double.compare(saturation, that.saturation) == 0
                && Objects.equals(islandField, that.islandField)
                && Objects.equals(edibleSpecies, that.edibleSpecies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(islandField, x, y, reproduced, saturation, edibleSpecies);
    }
}
