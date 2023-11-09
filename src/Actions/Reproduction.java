package src.Actions;

import src.Island.IslandField;
import src.IslandLivingObject.Animals.AnimalFactory;
import src.IslandLivingObject.IslandEntity;

import java.util.List;

import static src.Island.IslandField.countOfEntityResolver;

public class Reproduction {
    protected int X;
    protected int Y;

    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }

    public void reproduce(List<IslandEntity> entities) {
        AnimalFactory animalFactory = new AnimalFactory();


        for (IslandEntity entity : entities) {
            int i = countOfEntityResolver(entity.getX(), entity.getY(), entity.getClass());

            //если есть пара и достаточно места для данного типа животных
            if (i > 1 && i < entity.getType().getMaxAmount()) {

                for (IslandEntity reproducingAnimal : entities) {
                    if (entity != reproducingAnimal && !entity.isReproduced()) {
                        if (entity.getType() == reproducingAnimal.getType()) {
                            double chanceToReproduce = Math.random() * 1;
                            if (chanceToReproduce > 0.5) {
                                IslandEntity newBornEntity = animalFactory.createEntity(getX(), getY(), entity.getType());
                                //запрещаем всем причастным трогать друг друга
                                newBornEntity.setReproduced(true);
                                entity.setReproduced(true);
                                reproducingAnimal.setReproduced(true);

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
