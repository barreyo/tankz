package model;

/**
 * Models a basic projectile that can damage at impact.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public final class CannonBallModel extends AExplodingProjectile { 
    
    /**
     * Creates a cannonball.
     */
    public CannonBallModel() {
        super(0.1f, 10, 2000, 5000);
    }
}
