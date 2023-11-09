package src;

import src.Island.IslandField;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        Coordinator coordinator = new Coordinator();

      /*  for (List[] lists : IslandField.getGameField()) {
            for (List list : lists) {
                list.stream().forEach(System.out::println);
                System.out.println("____________________________________________________");
            }
            System.out.println("*********************************************************");
        }*/
        coordinator.start();
        Thread thread = new GameSimulationThread();
        thread.start();
        coordinator.start();
    }
}
