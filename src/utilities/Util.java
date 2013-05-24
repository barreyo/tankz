package utilities;

import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods.
 *
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class Util {

    /**
     * Find a geometry with a specific name in a spatial.
     *
     * @param spatial to search in.
     * @param name geo to find.
     * @return the geometry, if not found return null.
     */
    public static Geometry findGeom(Spatial spatial, String name) {
        if (spatial instanceof Node) {
            Node node = (Node) spatial;
            for (int i = 0; i < node.getQuantity(); i++) {
                Spatial child = node.getChild(i);
                Geometry result = findGeom(child, name);
                if (result != null) {
                    return result;
                }
            }
        } else if (spatial instanceof Geometry) {
            if (spatial.getName().startsWith(name)) {
                return (Geometry) spatial;
            }
        }
        return null;
    }

    /**
     * Returns list with all geometrys in the tree of the specified node thats starts with specified name.
     * 
     * @param node the node to be searched
     * @param name the geometry name to be searched for
     * @return a list with all geometrys in the tree of the specified node thats starts with specified name.
     */
    public static List<Geometry> findGeomsThatStartWith(Node node, String name) {
        List<Geometry> geoms = new ArrayList<Geometry>();
        addAllGeoms(node, name, geoms);
        return geoms;
    }

    private static void addAllGeoms(Spatial spatial, String name, List<Geometry> geoms) {
        // Recursive search
        if (spatial != null) {
            if (spatial instanceof Node) {
                for (Spatial child : ((Node)spatial).getChildren()) {
                    addAllGeoms(child, name, geoms);
                }
            } else if (spatial instanceof Geometry) {
                if (spatial.getName().startsWith(name)) {
                    geoms.add((Geometry)spatial);
                }
            }
        }
    }
}
