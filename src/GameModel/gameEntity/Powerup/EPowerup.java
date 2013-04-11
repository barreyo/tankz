package GameModel.gameEntity.Powerup;

/**
 * 
 *
 * @author Daniel
 */
public enum EPowerup {
    /**
     *
     */
    HEALTH_BOOST("Health"),
    /**
     *
     */
    SPEED_BOOST("Speed"),
    /**
     *
     */
    INVINSIBLE("Invincible"),
    /**
     *
     */
    INVISIBLE("Invisable"),
    /**
     *
     */
    MISSILE("Missile");
    
    private final String name;
    
    private EPowerup(String name) {
        this.name = name;
    };
}
