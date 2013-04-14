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
    LANDMINE("Mine"),
    /**
     *
     */
    HASTE("Speed"),
    /**
     *
     */
    BEER("Beer"),
    /**
     *
     */
    EMPTY("Empty"),
    /**
     *
     */
    HOMING("Missile");
    
    private final String name;
    
    private EPowerup(String name) {
        this.name = name;
    };
}
