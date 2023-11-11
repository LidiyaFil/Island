package src;

import src.Island.IslandField;
import src.Threads.StatisticThread;
import src.Threads.GameSimulationThread;
import src.Threads.PlantGenerationThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        IslandField islandField = IslandField.getInstance();

        Thread thread = new GameSimulationThread();
        thread.start();

        PlantGenerationThread plantThread = new PlantGenerationThread(islandField);
        plantThread.start();

        StatisticThread coordinator = new StatisticThread();
        coordinator.start();
    }
}
