package game;

import java.util.HashMap;

import org.jbox2d.common.Vec2;

/**
 * Class of grid is used to keep track of all obstructions in the world.
 * 
 * @authors     Spencer Davis, Josh Kepros, Josh McDermott, Chris Swanson
 * @version     2015-04-24
 * @since       2015-04-24
 */
public class Grid {
    Vec2 ballLoc;
    HashMap<Vec2, Obstruction> obstructions;
    float gridSize;
    float xOffset;
    float yOffset;
    
    /**
     * Constructor for Grid
     * 
     * @param gridSize		Float of grid size is passed
     * @param xOffset		Float of x-coordinate offset passed
     * @param yOffset		Float of y-coordinate offset passed
     */
    public Grid(float gridSize, float xOffset, float yOffset){
        obstructions = new HashMap<Vec2, Obstruction>();
        this.gridSize = gridSize;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    /**
     * AddObstruction method used to add obstruction to Vec2 object.
     * 
     * @param gridX				Integer of x-coordinate passed
     * @param gridY				Integer of y-coordinate passed
     * @param obstructionType	Integer of obstruction type passed
     * @return					Returns a boolean value
     */
    public boolean addObstruction(int gridX, int gridY, int obstructionType){
        Vec2 pos = new Vec2(gridX,gridY);
        Obstruction obs = obstructions.get(pos);
        if(obs == null){
            if(obstructionType < Obstruction.NUMTYPES && obstructionType >= 0){
                obstructions.put(pos, new Obstruction(obstructionType));
                return true;
            }
            return false;
        }else{
            return obs.addItem(obstructionType);
        }
    }
    
    /**
     * AddObstruction method used to add obstruction to Vec2 object.
     * 
     * @param position			Object type of Vec2 passed
     * @param obstructionType	Integer type of obstruction type passed
     * @return					Returns a boolean value
     */
    public boolean addObstruction(Vec2 position, int obstructionType){
        int gridX = (int)((position.x - xOffset)/gridSize);
        int gridY = (int)((position.y - xOffset)/gridSize);
        return addObstruction(gridX,gridY,obstructionType);
    }
    
    /**
     * RemoveObstruction method used to remove obstruction from Vec2 object.
     * 
     * @param gridX				Integer of x-coordinate passed
     * @param gridY				Integer of y-coordinate passed
     * @param obstructionType	Integer of obstruction type passed
     * @return					Returns a boolean value
     */
    public boolean removeObstruction(int gridX, int gridY, int obstructionType){
        Vec2 pos = new Vec2(gridX,gridY);
        Obstruction obs = obstructions.get(pos);
        if(obs == null){
            return false;
        }else{
            return obs.removeItem(obstructionType);
        }
    }
    
    /**
     * RemoveObstruction method used to remove obstruction from Vec2 object.
     * 
     * @param position			Object type of Vec2 passed
     * @param obstructionType	Integer type of obstruction type passed
     * @return					Returns a boolean value
     */
    public boolean removeObstruction(Vec2 position, int obstructionType){
        int gridX = (int)((position.x - xOffset)/gridSize);
        int gridY = (int)((position.y - xOffset)/gridSize);
        return removeObstruction(gridX,gridY,obstructionType);
    }
    
    /**
     * Gets a array of Obstructions in the <a href="http://en.wikipedia.org/wiki/Von_Neumann_neighborhood">
     * Von Neumann neighborhood</a> of a grid position.
     * 
     * @param gridX		Integer type of x-coordinate passed
     * @param gridY		Integer type of y-coordinate passed
     * @return 			Array of Obstructions clockwise from North.
     */
    public Obstruction[] vnNeighborhood(int gridX, int gridY){
        Obstruction[] obs = new Obstruction[4];
        obs[0] = obstructions.get(new Vec2(gridX  ,gridY+1));
        obs[1] = obstructions.get(new Vec2(gridX+1,gridY  ));
        obs[2] = obstructions.get(new Vec2(gridX  ,gridY-1));
        obs[3] = obstructions.get(new Vec2(gridX-1,gridY  ));
        return obs;
    }

    /**
     * Gets a array of Obstructions in the <a href="http://en.wikipedia.org/wiki/Moore_neighborhood">
     * Moore neighborhood</a> of a grid position.
     * 
     * @param gridX		Integer type of x-coordinate passed
     * @param gridY		Integer type of y-coordinate passed
     * @return 			Array of Obstructions clockwise from North.
     */
    public Obstruction[] mooreNeighborhood(int gridX, int gridY){
        Obstruction[] obs = new Obstruction[8];
        obs[0] = obstructions.get(new Vec2(gridX  ,gridY+1));
        obs[1] = obstructions.get(new Vec2(gridX+1,gridY+1));
        obs[2] = obstructions.get(new Vec2(gridX+1,gridY  ));
        obs[3] = obstructions.get(new Vec2(gridX+1,gridY-1));
        obs[4] = obstructions.get(new Vec2(gridX  ,gridY-1));
        obs[5] = obstructions.get(new Vec2(gridX-1,gridY-1));
        obs[6] = obstructions.get(new Vec2(gridX-1,gridY  ));
        obs[7] = obstructions.get(new Vec2(gridX-1,gridY+1));
        return obs;
    }
}
