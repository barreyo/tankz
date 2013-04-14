package GameView.gameEntity;

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
    MISSILE_PROJECTILE,
    /**
     * The poweruop game entity.
     */
    POWERUP;
    
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
                entity = new MissileProjectileEntity();
                break;
            case POWERUP:
                entity = new PowerupEntity();
                break;
            default:
                entity = new Tank();
                break;
        }
        return entity;
    }
}
