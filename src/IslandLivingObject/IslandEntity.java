package src.IslandLivingObject;

import src.Actions.MoveableReproducibleEatable;

import java.util.Map;

public interface IslandEntity {

    IslandEntityType getType();

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    void die();

    double getSaturation();
}
