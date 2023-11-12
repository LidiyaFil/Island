package src.IslandLivingObject.Animals.Predators;

import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Bear extends Predators {
    public Bear(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(IslandEntityType.PYTHON, 80);
        this.getEdibleSpecies().put(IslandEntityType.HORSE, 40);
        this.getEdibleSpecies().put(IslandEntityType.DEER, 80);
        this.getEdibleSpecies().put(IslandEntityType.RABBIT, 80);
        this.getEdibleSpecies().put(IslandEntityType.MOUSE, 90);
        this.getEdibleSpecies().put(IslandEntityType.GOAT, 70);
        this.getEdibleSpecies().put(IslandEntityType.SHEEP, 70);
        this.getEdibleSpecies().put(IslandEntityType.WILD_BOAR, 50);
        this.getEdibleSpecies().put(IslandEntityType.BUFFALO, 20);
        this.getEdibleSpecies().put(IslandEntityType.DUCK, 10);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.BEAR;
    }
}
