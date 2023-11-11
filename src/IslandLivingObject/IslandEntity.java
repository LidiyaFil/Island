package src.IslandLivingObject;

import java.util.Map;

public interface IslandEntity {

    IslandEntityType getType();

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);
}
