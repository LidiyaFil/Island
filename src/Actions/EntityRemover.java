package src.Actions;

import src.Island.IslandField;
import src.IslandLivingObject.Animals.AbstractAnimal;

public class EntityRemover {
    LiveAbilityValidator validator;

    public EntityRemover(LiveAbilityValidator validator) {
        this.validator = validator;
    }

    public void removeOrStayAnimal(AbstractAnimal abstractAnimal) {
        int x = abstractAnimal.getX();
        int y = abstractAnimal.getY();

        if (!validator.checkLiveAbility(abstractAnimal)) {
            IslandField.getInstance().getGameField()[x][y].remove(abstractAnimal);
        }
    }
}
