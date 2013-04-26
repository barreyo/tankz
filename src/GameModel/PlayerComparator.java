
package GameModel;

import java.util.Comparator;

/**
 * Comparator for comparing Players, compars scores first, if score is equal
 * compare deaths.
 * 
 * @author Johan Backman
 */
public class PlayerComparator implements Comparator {

    /**
     * Compares kills of players first, many kills overpower, if kills are equal
     * then deaths determine the order, less kills overpower.
     * 
     * @param t first player.
     * @param t1 second player.
     * @return an int less than one if t is lesser than t1, zero if equal and a
     *         value greater than zero if t is greater than t1.
     */
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
