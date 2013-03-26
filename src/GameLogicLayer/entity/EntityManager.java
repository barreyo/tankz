/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogicLayer.entity;

import GameLogicLayer.util.Manager;
import GameViewLayer.gameEntity.GameEntity;
import GameViewLayer.gameEntity.TanksEntity;

/**
 *
 * @author Daniel
 */
public class EntityManager {
    public GameEntity create(TanksEntity newEntity) {
        GameEntity entity = newEntity.createEntity();
        return entity;
    }
}
