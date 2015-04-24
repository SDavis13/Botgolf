package game;

import java.util.Set;

/**
 * This is Obstruction class for types of obstructions to Mob movement
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     1.0
 * @since       2015-04-21
 */
public class Obstruction {
    public static final int IGNORE  = 0,
                            KILL    = 1,
                            DYNAMIC = 2,
                            STATIC  = 3;
    public static final int NUMTYPES= 4;
    int[] obs;
    
    public Obstruction(){
        obs = new int[NUMTYPES];
    }
    
    public Obstruction(int type){
        obs = new int[NUMTYPES];
        if(type < NUMTYPES && type >= 0)
            obs[type]++;
    }
    
    public boolean removeItem(int type){
        if(type < NUMTYPES && type >= 0){
            obs[type]--;
            return true;
        }
        return false;
    }
    
    public boolean addItem(int type){
        if(type < NUMTYPES && type >= 0){
            obs[type]++;
            return true;
        }
        return false;
    }
}
