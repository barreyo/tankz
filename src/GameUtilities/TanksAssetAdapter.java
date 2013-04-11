package GameUtilities;

import App.TanksApp;
import com.jme3.asset.AssetManager;

/**
 *
 * @author Daniel
 */
public enum TanksAssetAdapter {
    INSTANCE;
    
    private final AssetManager assetManager;
    
    private TanksAssetAdapter() {
        assetManager = TanksApp.getApp().getAssetManager();
    }
    
    public AssetManager getAssetManager() {
        return assetManager;
    }
}
