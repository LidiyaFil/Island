package src.Actions;

import src.IslandLivingObject.Animals.AbstractAnimal;

public class LiveAbilityValidator {

    public boolean checkLiveAbility(AbstractAnimal abstractAnimal) {
        return abstractAnimal.getSaturation() > 0;
    }
}
