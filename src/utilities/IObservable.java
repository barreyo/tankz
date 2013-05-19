package utilities;

import java.beans.PropertyChangeListener;
/**
 * Interface allowing registering listeners for changes in state.
 * 
 * @author Daniel
 */
public interface IObservable {
	/**
	 * Add a PropertyChangeListener to the listener list. The listener is registered for all properties. 
	 * 
	 * @param l The PropertyChangeListener to be added.
	 */
	void addObserver(PropertyChangeListener l);
	
	/**
	 * Removes a PropertyChangeListener from the listener list.
	 * 
	 * @param l The PropertyChangeListener to be removed.
	 */
	void removeObserver(PropertyChangeListener l);
}
