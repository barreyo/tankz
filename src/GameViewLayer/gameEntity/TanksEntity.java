/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameViewLayer.gameEntity;

/**
 * Enum holding the games different game entitys.
 * 
 * @author Daniel
 */
public enum TanksEntity {
    /**
     *
     */
    TEST_FLOOR,
    /**
     *
     */
    TANK;

    /*
     * 
     */
    /**
     *
     * @return
     */
    public GameEntity createEntity() {

        GameEntity entity;

        switch (this) {
            case TEST_FLOOR:
                entity = new TestPlatform();
                break;
            case TANK:
                entity = new MainTank();
                break;
            default:
                entity = new MainTank();
                break;
        }
        return entity;
    }
}
