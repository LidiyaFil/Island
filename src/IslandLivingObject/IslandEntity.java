package src.IslandLivingObject;

import java.util.Map;

public interface IslandEntity {

    IslandEntityType getType();

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);
}


//todo сделать чтобы у сущности бралась координата динамически

//убрать дублирование кода

//вероятность поедания сделать в конфиг и передавать в сервис поедания

//убрать row type везде параметризовать