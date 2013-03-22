
package GameLogicLayer.Vehicle;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;

/**
 * Abstract vehicle controller.
 *
 * @author Daniel, Per, Johan, Albin
 */
public abstract class AVehicleController implements ActionListener {
    
    private InputManager inputManager;
  
    AVehicleController(InputManager inputManager) {
        this.inputManager = inputManager;
        setupKeys();
    }
   
    
    /**
     * Sets the keybindings for any vehicle.
     * Rotate left = H
     * Rotate right = K
     * Accelerate forward = U
     * Accelerate backwards = J
     * Reset the program = Return
     */
    private void setupKeys() {
        inputManager.addMapping("Lefts", new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping("Rights", new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("Ups", new KeyTrigger(KeyInput.KEY_U));
        inputManager.addMapping("Downs", new KeyTrigger(KeyInput.KEY_J));
        //inputManager.addMapping("Space", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("Reset", new KeyTrigger(KeyInput.KEY_RETURN));
        inputManager.addListener(this, "Lefts");
        inputManager.addListener(this, "Rights");
        inputManager.addListener(this, "Ups");
        inputManager.addListener(this, "Downs");
        //inputManager.addListener(this, "Space");
        inputManager.addListener(this, "Reset");
    }

    public abstract void simpleUpdate(float tpf);
}
