package GameLogicLayer.logic;

import com.jme3.asset.AssetManager;

/**
 *
 * @author Daniel
 */
public enum TanksAssetAdapter {
    INSTANCE;
    
    private final AssetManager assetManager;
    
    private TanksAssetAdapter() {
        assetManager = TanksGame.getApp().getAssetManager();
    }
    
    public AssetManager getAssetManager() {
        return assetManager;
    }
}
