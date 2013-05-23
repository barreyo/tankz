
package model;

import utilities.Commands;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import utilities.Constants;

/**
 * 
 * @author Albin Garpetun, Daniel Bäckström, Johan Backman, Per Thoresson
 */
public class AtomicBombModel extends AExplodingProjectile {
    public AtomicBombModel() {
        super(Constants.ATOMIC_BOMB_MASS, Constants.ATOMIC_BOMB_DAMAGE, 
                Constants.ATOMIC_BOMB_EXPLOSION_END_TIME, Constants.ATOMIC_BOMB_LIFE_TIME);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float tpf) {
        if (isInWorld) {
            Quaternion oldRotation = new Quaternion(rotation);
            rotation.lookAt(position.add(linearVelocity.normalize()), Vector3f.UNIT_Y);
            pcs.firePropertyChange(Commands.ROTATE, oldRotation, rotation);
        }
    }
}
