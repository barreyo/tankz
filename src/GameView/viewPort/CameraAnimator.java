/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameView.viewPort;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * This is a lol-class.
 * 
 * SHOULD BE REMOVED
 * 
 * @author Johan Backman, Daniel Bäckström, Albin Garpetun, Per Thoresson
 */
public class CameraAnimator {
    
    public static final Quaternion ROT_LEFT = new Quaternion().fromAngleAxis(FastMath.PI/32, new Vector3f(0,1,0));
    public static final Quaternion ROT_RIGHT = new Quaternion().fromAngleAxis(-FastMath.PI/32, new Vector3f(0,1,0));
    public static final Quaternion ROT_UP = new Quaternion().fromAngleAxis(FastMath.PI/32, new Vector3f(1,0,0));
    public static final Quaternion ROT_DOWN = new Quaternion().fromAngleAxis(-FastMath.PI/32, new Vector3f(1,0,0));
    
//    private static Quaternion origRot;
//    private static boolean shakeActive;
//    private float animTimer;
//    private boolean firstShakeFinished = false;
//    private static Camera cam;
//    
//    private static void shakeCamera(IPlayer p) {
//        cam = ViewPortManager.INSTANCE.getViewportForPlayer(p.getName()).getCamera();
//        origRot = cam.getRotation();
//        shakeActive = true;
//        cam.setRotation(ROT_DOWN);
//    }

    
//    public void update(float tpf) {
//        
//        if (shakeActive) {
//            animTimer += tpf;
//            
//            if (firstShakeFinished) {
//                cam.setRotation(origRot);
//                shakeActive = false;
//            }
//            if (animTimer >= 0.1f) {
//                cam.setRotation(ROT_UP);
//                firstShakeFinished = true;
//                animTimer = 0;
//            }   
//        }
//    }
//    
}
