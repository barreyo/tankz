package GameViewLayer.gameEntity;

/**
 * Enum holding the games different game entitys.
 * 
 * The class can be used to get an instance of a specified entity.
 * 
 * @author Daniel
 */
public enum EGameEntities {
    /**
     * The tank game entity.
     */
    TANK,
    /**
     * The missile projectile game entity.
     */
    MISSILE_PROJECTILE;
    
    /**
     * Returns an instance of the apropriate game enitity.
     * 
     * @return An instance of the apropriate game enitity.
     */
    public AGameEntity createEntity() {
        AGameEntity entity;

        switch (this) {
            case TANK:
                entity = new Tank();
                break;
            case MISSILE_PROJECTILE:
                entity = new MissileProjectile();
                break;
            default:
                entity = new Tank();
                break;
        }
        return entity;
    }
}
