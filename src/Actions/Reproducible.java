package src.Actions;

import src.IslandLivingObject.IslandEntity;

public interface Reproducible extends IslandEntity {
    boolean isReproduced();
    void setReproduced(boolean reproduced);
}
