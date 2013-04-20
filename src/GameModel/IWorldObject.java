package GameModel;

import GameUtilities.IObservable;

/**
 *
 * @author Daniel
 */
public interface IWorldObject extends IObservable {
    public void showInWorld();
    public void hideFromWorld();
    void update(float tpf);
    void cleanup();
}
