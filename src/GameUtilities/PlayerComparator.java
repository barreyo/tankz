/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameUtilities;

import GameModel.IPlayer;
import java.util.Comparator;

/**
 * Comparator for comparing Players, compars scores first, if score is equal
 * compare deaths.
 * 
 * @author Johan Backman
 */
public class PlayerComparator implements Comparator {

    @Override
    public int compare(Object t, Object t1) {
        IPlayer p1 = (IPlayer) t;
        IPlayer p2 = (IPlayer) t1;
        
        if (p1.getKills() == p2.getKills()) {
            return p1.getDeaths() - p2.getDeaths();
        }
        return p2.getKills() - p1.getKills();
    }
    
}
