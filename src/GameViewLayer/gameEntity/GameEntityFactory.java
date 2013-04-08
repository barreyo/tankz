package GameViewLayer.gameEntity;

import GameViewLayer.gameEntity.AGameEntity;
import GameViewLayer.gameEntity.EGameEntities;

/**
 * Manages game entities.
 * 
 * @author Daniel
 */
public class GameEntityFactory {
    private static GameEntityFactory instance;
    
    private GameEntityFactory() {}
    
    public static synchronized GameEntityFactory getInstance() {
        if (instance == null) {
            instance = new GameEntityFactory();
        }
        return instance;
    }
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
