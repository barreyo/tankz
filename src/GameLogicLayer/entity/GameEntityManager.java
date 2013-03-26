/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.entity;

import GameLogicLayer.util.Manager;
import GameViewLayer.gameEntity.GameEntity;
import GameViewLayer.gameEntity.TanksEntity;

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
    public GameEntity create(TanksEntity newEntity) {
        GameEntity entity = newEntity.createEntity();
        return entity;
    }
}
