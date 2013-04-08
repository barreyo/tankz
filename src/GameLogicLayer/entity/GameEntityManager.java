package GameLogicLayer.entity;

import GameLogicLayer.Effects.EffectsManager;
import GameViewLayer.gameEntity.AGameEntity;
import GameViewLayer.gameEntity.EGameEntities;

/**
 * Manages game entities.
 * 
 * @author Daniel
 */
public class GameEntityManager {
    private static GameEntityManager instance;
    
    private GameEntityManager() {}
    
    public static synchronized GameEntityManager getInstance() {
        if (instance == null) {
            instance = new GameEntityManager();
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
