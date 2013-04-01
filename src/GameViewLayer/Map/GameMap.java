package GameViewLayer.Map;

import GameViewLayer.gameEntity.GameEntity;
import java.util.LinkedList;

/**
 *
 * @author Daniel
 */
public interface GameMap {
    /**
     *
     */
    public void load();
    /**
     *
     */
    public void cleanup();
    /**
     *
     * @return
     */
    public LinkedList<GameEntity> getAllEntities();
}
