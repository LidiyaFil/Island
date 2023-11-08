package src.IslandLivingObject.Animals.Predators;


import src.IslandLivingObject.IslandEntityType;
import java.util.Map;

public class Python extends Predators {

    public Python() {
        Map<IslandEntityType, Integer> edibleSpecies = this.getEdibleSpecies();
        edibleSpecies.put(IslandEntityType.FOX, 14);
        edibleSpecies.put(IslandEntityType.RABBIT, 20);
        edibleSpecies.put(IslandEntityType.MOUSE, 40);
        edibleSpecies.put(IslandEntityType.DUCK, 10);
    }
    @Override
    public IslandEntityType getType() {
        return IslandEntityType.PYTHON;
    }
}
