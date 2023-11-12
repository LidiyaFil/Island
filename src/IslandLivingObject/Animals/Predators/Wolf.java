package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Wolf extends Predators {
    public Wolf(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(IslandEntityType.HORSE, 10);
        this.getEdibleSpecies().put(IslandEntityType.DEER, 15);
        this.getEdibleSpecies().put(IslandEntityType.RABBIT, 60);
        this.getEdibleSpecies().put(IslandEntityType.MOUSE, 80);
        this.getEdibleSpecies().put(IslandEntityType.GOAT, 60);
        this.getEdibleSpecies().put(IslandEntityType.SHEEP, 70);
        this.getEdibleSpecies().put(IslandEntityType.WILD_BOAR, 15);
        this.getEdibleSpecies().put(IslandEntityType.BUFFALO, 10);
        this.getEdibleSpecies().put(IslandEntityType.DUCK, 40);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.WOLF;
    }
}