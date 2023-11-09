package src.Actions;

import src.Coordinator;
import src.Island.IslandField;
import src.IslandLivingObject.Animals.AnimalFactory;
import src.IslandLivingObject.IslandEntity;

import java.util.List;

public class ReproductionService {
    private IslandEntity islandEntity;
    IslandField islandField = IslandField.getInstance();

    public ReproductionService(IslandEntity islandEntity) {
        this.islandEntity = islandEntity;
    }

    public void reproduce(List<IslandEntity> entities) {
        AnimalFactory animalFactory = new AnimalFactory();
// пробегаемся по списку всех животных поля
        for (IslandEntity entity : entities) {
            // находим их общее количество на карте
            int i = islandField.countOfEntityResolver(entity.getX(), entity.getY(), entity.getClass());

            //если есть пара и достаточно места для данного типа животных
            if (i > 1 && i < entity.getType().getMaxAmount()) {

                for (IslandEntity reproducingAnimal : entities) {
                    if (entity != reproducingAnimal && !entity.isReproduced()) {
                        if (entity.getType() == reproducingAnimal.getType()) {
                            double chanceToReproduce = Math.random() * 1;
                            if (chanceToReproduce > 0.5) {
                                IslandEntity newBornEntity = animalFactory
                                        .createEntity(islandEntity.getX(), islandEntity.getY(), entity.getType());
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
