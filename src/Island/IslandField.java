package src.Island;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
@Deprecated
public class IslandField {
    public static List<CopyOnWriteArrayList<Cell>> cells;
    //это поле для ограничений поля, только я пока хз как и куда его вписать
    public static int playingFieldWidth;


    /**
     * Создание игрового поля
     */
    public IslandField(int x, int y, CellFactory cellFactory) throws JsonProcessingException {
        this.playingFieldWidth = y;
        cells = cellFactory.createCellLists(x, y);
    }


    /**
     * Возвращает игровое поле
     * TODO нужно ли убрать статический контекст?
     */
    public static List<CopyOnWriteArrayList<Cell>> getCells() {
        return cells;
    }
    /**
     * Возвращает ссылку на клетку по заданным координатам x и y
     * TODO нужно ли убрать статический контекст?
     */
    public static Cell getCell(int x, int y) {
        if (x >= 0 && x < cells.size() && y >= 0 && y < cells.get(x).size()) {
            return cells.get(x).get(y);
        } else {
            return null; //если координаты находятся вне диапазона
        }
    }

    public static int getPlayingFieldWidth() {
        return playingFieldWidth;
    }
}