package src;

import src.Island.IslandField;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        IslandField islandField = IslandField.getInstance();

        for (List[] lists : IslandField.getGameField()) {
            for (List list :lists) {
                list.stream().forEach(System.out::println);
                System.out.println("____________________________________________________");
            }
            System.out.println("*********************************************************");
        }
    }
}
