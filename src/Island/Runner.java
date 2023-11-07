package src.Island;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.*;

public class Runner {

    public static void main(String[] args) {
        IslandFieldNew islandFieldNew = IslandFieldNew.getInstance();

        for (List[] lists : IslandFieldNew.getGameField()) {
            for (List list :lists) {
                list.stream().forEach(System.out::println);
                System.out.println("____________________________________________________");
            }
            System.out.println("*********************************************************");
        }
    }
}
