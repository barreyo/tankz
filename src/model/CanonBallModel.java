package model;

/**
 * Models a basic projectile that can damage at impact.
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public final class CanonBallModel extends AExplodingProjectile { 
    
    /**
     * Creates a cannonball.
     */
    public CanonBallModel() {
        super(0.1f, 10, 2000, 5000);
    }
}
