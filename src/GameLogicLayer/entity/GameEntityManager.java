package GameLogicLayer.entity;

import GameViewLayer.gameEntity.AGameEntity;
import GameViewLayer.gameEntity.EGameEntities;

/**
 * Manages game entities.
 * 
 * @author Daniel
 */
public class GameEntityManager {
    /**
     * Creates a game entity.
     *
     * @param newEntity The entity to create.
     * @return The created game entity
     */
    public AGameEntity create(EGameEntities newEntity) {
        AGameEntity entity = newEntity.createEntity();
        return entity;
    }
}
