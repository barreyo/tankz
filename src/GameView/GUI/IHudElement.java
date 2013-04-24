
package GameView.GUI;

/**
 * The basic methods a hud element should have.
 * 
 * @author Johan Backman
 */
public interface IHudElement {
    /**
     * Make the element visible in the GUI.
     */
    public void show();
    /**
     * Remove the element from the GUI.
     */
    public void hide();
}
