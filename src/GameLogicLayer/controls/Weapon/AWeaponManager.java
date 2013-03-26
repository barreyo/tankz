
package GameLogicLayer.controls.Weapon;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;


/**
 *
 * @author Daniel
 */
public abstract class AWeaponManager implements ActionListener {
    private InputManager inputManager;
  
    AWeaponManager(InputManager inputManager) {
        this.inputManager = inputManager;
        setupKeys();
    }
   
    
    /**
     * Sets the keybindings for a weapon.
     */
    private void setupKeys() {
        inputManager.addMapping("Space", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this, "Space");
    }

    /**
     *
     * @param direction
     */
    public abstract void updateDirectionOfWeapon(Vector3f direction);
}
