package game;

import java.util.Set;

/**
 * This is Obstruction class for types of obstructions to Mob movement
 * 
 * @author     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public class Obstruction {
    public static final int IGNORE  = 0,
                            KILL    = 1,
                            DYNAMIC = 2,
                            STATIC  = 3;
    public static final int NUMTYPES= 4;
    final int[] obs;
    
    /**
     * First constructor for Obstruction
     */
    public Obstruction(){
        obs = new int[NUMTYPES];
    }
    
    /**
     * Second constructor for Obstruction with different signature
     * 
     * @param type	Integer type is passed
     */
    public Obstruction(int type){
        obs = new int[NUMTYPES];
        if(type < NUMTYPES && type >= 0)
            obs[type]++;
    }
    
    public boolean hasItem(int type){
        if(type < NUMTYPES && type >= 0){
            if(obs[type] > 0) return true;
        }
        return false;
    }
    
    public boolean freeSpace(){
        for(int i = DYNAMIC; i < NUMTYPES; i++){
            if(obs[i] > 0){
                return false;
            }
        }
        return true;
    }
    
    /**
     * RemoveItem method removes obstruction from world.
     * 
     * @param type	Integer type is passed
     * @return		Returns a boolean value.  
     */
    public boolean removeItem(int type){
        if(type < NUMTYPES && type >= 0){
            obs[type]--;
            return true;
        }
        return false;
    }
    
    /**
     * AddItem method adds item to world.
     * 
     * @param type	Integer type is passed
     * @return		Returns a boolean value. 
     */
    public boolean addItem(int type){
        if(type < NUMTYPES && type >= 0){
            obs[type]++;
            return true;
        }
        return false;
    }
}
