package src;

import src.Island.IslandField;
import src.IslandLivingObject.IslandEntity;
import src.Threads.Coordinator;
import src.Threads.GameSimulationThread;
import src.Threads.PlantGenerationThread;

import java.util.HashMap;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        IslandField islandField = IslandField.getInstance();
        Coordinator coordinator = new Coordinator();
        coordinator.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread thread = new GameSimulationThread();
        thread.start();

        PlantGenerationThread plantThread = new PlantGenerationThread(islandField);
        plantThread.start();


        /*for (List[] lists : IslandField.getInstance().getGameField()) {

            HashMap<IslandEntity, Integer> islandEntityIntegerHashMap = new HashMap<>();

            for (int i = 0, listsLength = lists.length; i < listsLength; i++) {
                List list = lists[i];
                for (int j = 0; j < list.size(); j++) {
                    if (islandEntityIntegerHashMap.containsKey(list.get(j))) {
                        islandEntityIntegerHashMap.put((IslandEntity) list.get(j), islandEntityIntegerHashMap.get(list.get(j)) + 1);
                    } else {
                        islandEntityIntegerHashMap.put((IslandEntity) list.get(j), 1);
                    }
                }
            }
            System.out.println(islandEntityIntegerHashMap);
        }*/
    }
}


