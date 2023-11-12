package src.IslandLivingObject.Animals.Predators;


import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Python extends Predators {
    public Python(int x, int y) {
        super(x, y);
        this.getEdibleSpecies().put(IslandEntityType.FOX, 14);
        this.getEdibleSpecies().put(IslandEntityType.RABBIT, 20);
        this.getEdibleSpecies().put(IslandEntityType.MOUSE, 40);
        this.getEdibleSpecies().put(IslandEntityType.DUCK, 10);
    }

    @Override
    public IslandEntityType getType() {
        return IslandEntityType.PYTHON;
    }
}
