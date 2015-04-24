package game;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

/**
 * Adapted from http://www.iforce2d.net/b2dtut/explosions
 * Applies an impulse to a jBox2d Body inversely proportional to its distance away,
 * to simulate something similar to an explosion.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public class Utils {

    /**
     * ApplyBlastImpulse method used for physics part of entity.
     * 
     * @param body			Object of Body passed
     * @param blastLoc		Object of Vec2 passed
     * @param applyPoint	Object of Vec2 passed
     * @param blastPower	Float value is passed
     */
    public static void applyBlastImpulse(Body body, Vec2 blastLoc, Vec2 applyPoint, float blastPower){
        Vec2 blastDir = applyPoint.sub(blastLoc);
        float distance = blastDir.normalize();
        if(distance == 0) return;
        float invDistance = 1/distance;
        float impulseMag = blastPower * invDistance * invDistance;
        body.applyLinearImpulse(blastDir.mul(impulseMag), applyPoint);
    }

    /**
     * Convert a JBox2D x coordinate to a Swing pixel x coordinate
     * 
     * @param physX		A x-coordinate float value is passed.
     * @return			Returns a pixel x-coordinate
     */
    public static float toPixX(float physX) {
        float pixX = Consts.SCALE*physX / 100.0f - Consts.pxOffset;
        return pixX;
    }

    /**
     * Convert a Swing pixel x coordinate to a JBox2D x coordinate
     * 
     * @param pixX		A x-coordinate float value is passed.
     * @return			Returns a pixel point of x-coordinate
     */
    public static float toPhysX(float pixX) {
        float physX = (100.0f*(pixX + Consts.pxOffset))/Consts.SCALE;
        return physX;
    }

    /**
     * Convert a JBox2D y coordinate to a Swing pixel y coordinate
     * 
     * @param physY		A y-coordinate float value is passed
     * @return			Returns a pixel point of y-coordinate
     */
    public static float toPixY(float physY) {
        float pixY = Consts.SCALE - (Consts.SCALE) * physY / 100.0f - Consts.pyOffset;
        return pixY;
    }

    /**
     * Convert a Swing pixel y coordinate to a JBox2D y coordinate
     * 
     * @param pixY		A y-coordinate float is passed
     * @return			Returns a physics point of y-coordinate
     */
    public static float toPhysY(float pixY) {
        float physY = 100.0f - ((100 * (pixY + Consts.pyOffset)) / Consts.SCALE) ;
        return physY;//not entirely sure if this formula is correct.
    }

    /**
     * Convert a JBox2D width to pixel width
     * 
     * @param physLen	A physics length float is passed
     * @return			Returns a pixel length
     */
    public static float toPixLength(float physLen) {
        return Consts.SCALE*physLen / 100.0f;
    }

    /**
     * Convert a JBox2D width to pixel width
     * 
     * @param pixLen	A pixel length float is passed
     * @return			Returns a Physics length
     */
    public static float toPhysLength(float pixLen) {
        return  100f*pixLen / Consts.SCALE ;
    }
}
