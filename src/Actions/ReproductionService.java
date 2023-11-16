package src.Actions;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslandEntityType;
import src.IslandLivingObject.IslantEntityFactory;
import src.IslandLivingObject.IslandEntity;

import java.util.List;

public class ReproductionService {
    private final IslantEntityFactory islantEntityFactory;

    public ReproductionService(IslantEntityFactory islantEntityFactory) {
        this.islantEntityFactory = islantEntityFactory;
    }

    public void reproduceAllAnimalOnCell(List<IslandEntity> entities, IslandEntity entity) {
        if (canReproduce(entity, entities))
            reproduceOneAnimal(entity, entities);
    }

    private boolean canReproduce(IslandEntity entity, List<IslandEntity> entities) {
        if (!(entity instanceof AbstractAnimal)) return false;
        if (((AbstractAnimal) entity).isReproduced()) return false;

        int count = countSameTypeAnimals(entity, entities);
        return count > 1 && count < entity.getType().getMaxAmount();
    }

    private int countSameTypeAnimals(IslandEntity entity, List<IslandEntity> entities) {
        return (int) entities.stream()
                .filter(e -> e.getType() == entity.getType())
                .count();
    }

    private void reproduceOneAnimal(IslandEntity entity, List<IslandEntity> entities) {
        AbstractAnimal firstParent = (AbstractAnimal) entity;
        for (IslandEntity islandEntity : entities) {
            if (islandEntity.getType() == IslandEntityType.PLANT) {
                break;
            }
            AbstractAnimal secondParent = (AbstractAnimal) islandEntity;
            if (secondParent != firstParent
                    && secondParent.getType() == firstParent.getType()
                    && !(secondParent.isReproduced())
                    && Math.random() > 0.5) {
                AbstractAnimal newBornEntity =
                        (AbstractAnimal) islantEntityFactory.createEntity(firstParent.getX(), firstParent.getY(), firstParent.getType());
                newBornEntity.setReproduced(true);
                firstParent.setReproduced(true);
                secondParent.setReproduced(true);
//                System.out.println("added new Animal" + newBornEntity);
                entities.add(newBornEntity);
            }
        }
    }
}
