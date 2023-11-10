package src.Actions.Servicies;

import src.Actions.Reproducible;
import src.Island.IslandField;
import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslantEntityFactory;
import src.IslandLivingObject.IslandEntity;

import java.util.List;

public class ReproductionService {
    private AbstractAnimal abstractAnimal;

    public ReproductionService(AbstractAnimal abstractAnimal) {
        this.abstractAnimal = abstractAnimal;
    }

    public void reproduce(List<IslandEntity> entities) {
        IslantEntityFactory animalFactory = new IslantEntityFactory();
// пробегаемся по списку всех животных поля
        for (IslandEntity entity : entities) {
            // находим их общее количество на карте
            int i = IslandField.getInstance().countOfEntityResolver(entity.getX(), entity.getY(), entity.getClass());
            //если есть пара и достаточно места для данного типа животных
            if (i > 1 && i < entity.getType().getMaxAmount()) {
                for (IslandEntity reproducingAnimal : entities) {
                    if (entity instanceof AbstractAnimal) {
                        AbstractAnimal animal = (AbstractAnimal) entity;
                        if (entity != reproducingAnimal && !animal.isReproduced()) {
                            if (entity.getType() == reproducingAnimal.getType()) {
                                double chanceToReproduce = Math.random() * 1;
                                if (chanceToReproduce > 0.5) {
                                    AbstractAnimal newBornEntity = (AbstractAnimal) animalFactory.createEntity(animal.getX(), animal.getY(), animal.getType());                                    //запрещаем всем причастным трогать друг друга
                                    newBornEntity.setReproduced(true);
                                    abstractAnimal.setReproduced(true);
                                    animal.setReproduced(true);
                                    //добавляем новенького
                                    entities.add(newBornEntity);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
