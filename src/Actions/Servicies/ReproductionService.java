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

    public void reproduce(List<IslandEntity> entities) {

        for (IslandEntity entity : entities) {
            if (canReproduce(entity, entities)) {
                reproduceAnimal(entity, entities);
            }
        }
    }

    private boolean canReproduce(IslandEntity entity, List<IslandEntity> entities) {
        //растения мимо
        if (!(entity instanceof AbstractAnimal animal)) {
            return false;
        }
        //приводим к животному
        // если уже размножалось
        if (animal.isReproduced()) {
            return false;
        }

        // проверка на то, есть ли место на клетке (сколько их на клетке)
        int count = countSameTypeAnimals(entity, entities);
        return count > 1 && count < entity.getType().getMaxAmount();
    }

    // находим общее количество вида на клетке
    private int countSameTypeAnimals(IslandEntity entity, List<IslandEntity> entities) {
        return (int) entities.stream()
                .filter(e -> e.getClass() == entity.getClass())
                .count();
    }

    private void reproduceAnimal(IslandEntity entity, List<IslandEntity> entities) {
        AbstractAnimal animal = (AbstractAnimal) entity;


        entities.stream()
                .filter(reproducingAnimal -> reproducingAnimal != entity
                        && reproducingAnimal.getClass() == entity.getClass()
                        && !((AbstractAnimal) reproducingAnimal).isReproduced()
                        && Math.random() > 0.5)
                .findFirst()
                .ifPresent(reproducingAnimal -> {
                    AbstractAnimal newBornEntity =
                            (AbstractAnimal) islantEntityFactory.createEntity(animal.getX(), animal.getY(), animal.getType());
                    newBornEntity.setReproduced(true);
                    animal.setReproduced(true);
                    ((AbstractAnimal) reproducingAnimal).setReproduced(true);
                    //добавляем новенького
                    System.out.println("added new Animal" + newBornEntity);
                    entities.add(newBornEntity);
                });
    }
}
