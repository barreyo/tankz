
package GameLogicLayer.Weapon;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;

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
}
