package src;

import src.Actions.Moving;
import src.Actions.Nutrition;
import src.Actions.Reproduction;
import src.Island.IslandField;
import src.IslandLivingObject.Animals.AbstractAnimal;

import java.util.List;

public class GameSimulationThread extends Thread {
    IslandField islandField = IslandField.getInstance();
    Nutrition nutrition = new Nutrition();
    Reproduction reproduction = new Reproduction();
    Moving moving = new Moving();
    private boolean running;

    public GameSimulationThread(IslandField islandField) {
        this.islandField = islandField;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            for (List[] lists : IslandField.getGameField()) {
                for (List list : lists) {
                    // cначала все питаются
                    list.stream().forEach(nutrition.eat(list));
                    // затем пробуют размножиться
                    list.stream().forEach(reproduction.reproduce(list));
                    // затем пробуют переместиться
                    list.stream().forEach(moving.move());
                }
            }
            if (checkEndCondition()) {
                running = false;
            }
        }
    }

    public void stopSimulation() {
        running = false;
    }

    private boolean checkEndCondition() {
        return islandField.areAllPredatorsDead();
    }
}
