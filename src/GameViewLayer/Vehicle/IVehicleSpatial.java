
package GameViewLayer.Vehicle;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * Interface describing a vehical spatial with a node connection
 *
 * @author Daniel, Per, Johan, Albin
 */
public interface IVehicleSpatial {
    /**
     * Getter for the spatial of the vehicle.
     *
     * @return Spatial of the vehicle.
     */
    Spatial getVehicleSpatial();
    /**
     * Getter for the vehicle node.
     *
     * @return Node that the vehicle spatial is connected to.
     */
    Node getVehicleNode();
}
