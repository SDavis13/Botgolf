package game;

public class Utils {
    //Convert a JBox2D x coordinate to a Swing pixel x coordinate
    public static float toPixX(float physX) {
        float pixX = Consts.SCALE*physX / 100.0f - Consts.pxOffset;
        return pixX;
    }

    //Convert a Swing pixel x coordinate to a JBox2D x coordinate
    public static float toPhysX(float pixX) {
        float physX = (100.0f*(pixX + Consts.pxOffset))/Consts.SCALE;
        return physX;
    }

    //Convert a JBox2D y coordinate to a Swing pixel y coordinate
    public static float toPixY(float physY) {
        float pixY = Consts.SCALE - (Consts.SCALE) * physY / 100.0f - Consts.pyOffset;
        return pixY;
    }

    //Convert a Swing pixel y coordinate to a JBox2D y coordinate
    public static float toPhysY(float pixY) {
        float physY = 100.0f - ((100 * (pixY + Consts.pyOffset)) / Consts.SCALE) ;
        return physY;//not entirely sure if this is correct.
    }

    //Convert a JBox2D width to pixel width
    public static float toPixLength(float physLen) {
        return Consts.SCALE*physLen / 100.0f;
    }
    
    //Convert a JBox2D width to pixel width
    public static float toPhysLength(float pixLen) {
        return  100f*pixLen / Consts.SCALE ;
    }
}
