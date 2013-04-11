package GameView.gameEntity;

import GameView.gameEntity.AGameEntity;
import GameView.gameEntity.EGameEntities;

/**
 * Manages game entities.
 * 
 * @author Daniel
 */
public final class GameEntityFactory {
    
    private GameEntityFactory() {}
    
    /**
     * Creates a game entity.
     *
     * @param newEntity The entity to create.
     * @return The created game entity
     */
    public static AGameEntity create(EGameEntities newEntity) {
        AGameEntity entity = newEntity.createEntity();
        return entity;
    }
}
