package src.Actions.Servicies;

import src.Actions.Reproducible;
import src.Island.IslandField;
import src.IslandLivingObject.Animals.AbstractAnimal;
import src.IslandLivingObject.IslantEntityFactory;
import src.IslandLivingObject.IslandEntity;

import java.util.List;

public class ReproductionService {
    private AbstractAnimal abstractAnimal;

    public ReproductionService(Reproducible islandEntity) {
        this.abstractAnimal = (AbstractAnimal) islandEntity;
    }

    public void reproduce(List<AbstractAnimal> entities) {
        IslandField islandField = IslandField.getInstance();
        IslantEntityFactory islantEntityFactory = new IslantEntityFactory();
        // пробегаемся по списку всех животных поля
        entities.stream()
                // находим их общее количество на карте
                .filter(entity -> islandField.countOfEntityResolver(entity.getX(), entity.getY(), entity.getClass()) > 1
                        // если есть пара и достаточно места для данного типа животных
                        && islandField.countOfEntityResolver(entity.getX(), entity.getY(), entity.getClass()) < entity.getType().getMaxAmount()
                        // если ещё не размножалось
                        && !entity.isReproduced())
                .forEach(entity -> entities.stream()
                        .filter(reproducingAnimal -> entity != reproducingAnimal && entity.getType() == reproducingAnimal.getType() && !reproducingAnimal.isReproduced())
                        // находим первого везунчика
                        .findFirst()
                        .ifPresent(reproducingAnimal -> {
                            // великий рандом вычисляет шанс на зачатие
                            double chanceToReproduce = Math.random();
                            if (chanceToReproduce > 0.5) {
                                IslandEntity newBornEntity = islantEntityFactory.createEntity(entity.getX(), entity.getY(), entity.getType());
                                //запрещаем всем участникам процесса трогать друг друга
//                                newBornEntity.setReproduced(true);
                                entity.setReproduced(true);
                                reproducingAnimal.setReproduced(true);
                                entities.add((AbstractAnimal) newBornEntity);
                            }
                        }));
    }
}
