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
                reproduceEntity(entity, entities);
            }
        }
    }

    private boolean canReproduce(IslandEntity entity, List<IslandEntity> entities) {
        //растения мимо
        if (!(entity instanceof AbstractAnimal)) {
            return false;
        }
        //приводим к энимал
        AbstractAnimal animal = (AbstractAnimal) entity;
        // само животное уже размножалось
        if (animal.isReproduced()) {
            return false;
        }

        //есть ли место на клетке (сколько их на клетке)
        int count = countSameTypeAnimals(entity, entities);
        return count > 1 && count < entity.getType().getMaxAmount();
    }

    // находим общее количество вида на клетке
    private int countSameTypeAnimals(IslandEntity entity, List<IslandEntity> entities) {
        return (int) entities.stream()
                .filter(e -> e.getClass() == entity.getClass())
                .count();
    }

    private void reproduceEntity(IslandEntity entity, List<IslandEntity> entities) {
        AbstractAnimal animal = (AbstractAnimal) entity;
        IslantEntityFactory animalFactory = new IslantEntityFactory();

        // пробегаемся по списку всех животных поля
        for (IslandEntity reproducingAnimal : entities) {

            //не самого себя, пара еще не размножалась, вероятность 50%
            if (reproducingAnimal != entity
                    && reproducingAnimal.getClass() == entity.getClass()
                    && !((AbstractAnimal) reproducingAnimal).isReproduced()
                    && Math.random() > 0.5) {
                AbstractAnimal newBornEntity =
                        (AbstractAnimal) animalFactory.createEntity(animal.getX(), animal.getY(), animal.getType());
                newBornEntity.setReproduced(true);
                animal.setReproduced(true);
                ((AbstractAnimal) reproducingAnimal).setReproduced(true);
                //добавляем новенького
//                System.out.println("добавили новое животное " + newBornEntity.toString());
                entities.add(newBornEntity);
                break;  // если найдено подходящее животное, переходим к следующему
            }
        }
    }
}
