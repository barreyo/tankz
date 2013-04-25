/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameUtilities;

import GameModel.IPlayer;
import java.util.Comparator;

/**
 *
 * @author backman
 */
public class PlayerComparator implements Comparator {

    @Override
    public int compare(Object t, Object t1) {
        IPlayer p1 = (IPlayer) t;
        IPlayer p2 = (IPlayer) t1;
        
        if (p1.getKills() == p2.getKills()) {
            
        }
        
        return 1;
    }
    
}
