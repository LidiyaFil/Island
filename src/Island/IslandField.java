package src.Island;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

public class IslandField {
    private final List<CopyOnWriteArrayList<Cell>> cells;
    private final int y;

    public IslandField(int x, int y, CellFactory cellFactory) throws JsonProcessingException {
        this.y = y;
        cells = cellFactory.createCellLists(x, y);
    }

    public List<CopyOnWriteArrayList<Cell>> getCells() {
        return cells;
    }

    public int getY() {
        return y;
    }
}