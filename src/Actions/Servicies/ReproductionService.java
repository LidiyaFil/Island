package src.Actions.Servicies;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslantEntityFactory;
import src.IslandLivingObject.IslandEntity;

import java.util.List;

public class ReproductionService {
    private final IslantEntityFactory islantEntityFactory;

    public ReproductionService(IslantEntityFactory islantEntityFactory) {
        this.islantEntityFactory = islantEntityFactory;
    }

    public void reproduce(List<IslandEntity> entities, IslandEntity entity) {
            if (canReproduce(entity, entities)) {
                reproduceAnimal(entity, entities);
            }
    }

    private boolean canReproduce(IslandEntity entity, List<IslandEntity> entities) {
        //растения мимо
        if (!(entity instanceof AbstractAnimal)) {
            return false;
        }
        // если уже размножалось
        if (((AbstractAnimal) entity).isReproduced()) {
//            System.out.println(animal + " отказано в размножении");
            return false;
        }

        // проверка на то, есть ли место на клетке (сколько их на клетке)
        int count = countSameTypeAnimals(entity, entities);
        return count > 1 && count < entity.getType().getMaxAmount();
    }

    // находим общее количество вида на клетке
    private int countSameTypeAnimals(IslandEntity entity, List<IslandEntity> entities) {
        return (int) entities.stream()
                .filter(e -> e.getType() == entity.getType())
                .count();
    }

    private void reproduceAnimal(IslandEntity entity, List<IslandEntity> entities) {
        AbstractAnimal animal = (AbstractAnimal) entity;


        //добавляем новенького
        for (IslandEntity islandEntity : entities) {
            if (islandEntity != entity
                    && islandEntity.getType() == entity.getType()
                    && !((AbstractAnimal) islandEntity).isReproduced()
                    && Math.random() > 0.5) {
                AbstractAnimal newBornEntity =
                        (AbstractAnimal) islantEntityFactory.createEntity(animal.getX(), animal.getY(), animal.getType());
                newBornEntity.setReproduced(true);
                animal.setReproduced(true);
                ((AbstractAnimal) islandEntity).setReproduced(true);
                //добавляем новенького
//                System.out.println("added new Animal" + newBornEntity);
                entities.add(newBornEntity);
                break;
            }
        }
    }
}
