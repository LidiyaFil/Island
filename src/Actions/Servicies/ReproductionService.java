package src.Actions.Servicies;

import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslantEntityFactory;
import src.IslandLivingObject.IslandEntity;

import java.util.Iterator;
import java.util.List;

public class ReproductionService {
    private AbstractAnimal abstractAnimal;

    public ReproductionService(AbstractAnimal abstractAnimal) {
        this.abstractAnimal = abstractAnimal;
    }

    public void reproduce(List<IslandEntity> entities) {
        Iterator<IslandEntity> iterator = entities.iterator();

        while (iterator.hasNext()) {
            IslandEntity entity = iterator.next();

            if (canReproduce(entity, entities)) {
                reproduceAnimal(entity, entities);
            }
        }
    }

    private boolean canReproduce(IslandEntity entity, List<IslandEntity> entities) {
        //растения мимо
        if (!(entity instanceof AbstractAnimal)) {
            return false;
        }
        //приводим к животному
        AbstractAnimal animal = (AbstractAnimal) entity;
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
        IslantEntityFactory animalFactory = new IslantEntityFactory();

        entities.stream()
                .filter(reproducingAnimal -> reproducingAnimal != entity
                        && reproducingAnimal.getClass() == entity.getClass()
                        && !((AbstractAnimal) reproducingAnimal).isReproduced()
                        && Math.random() > 0.5)
                .findFirst()
                .ifPresent(reproducingAnimal -> {
                    AbstractAnimal newBornEntity =
                            (AbstractAnimal) animalFactory.createEntity(animal.getX(), animal.getY(), animal.getType());
                    newBornEntity.setReproduced(true);
                    animal.setReproduced(true);
                    ((AbstractAnimal) reproducingAnimal).setReproduced(true);
                    //добавляем новенького
                    entities.add(newBornEntity);
                });
    }
}
