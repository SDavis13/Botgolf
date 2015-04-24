package game;

import java.util.HashMap;

import org.jbox2d.common.Vec2;

public class Grid {
    Vec2 ballLoc;
    HashMap<Vec2, Obstruction> obstructions;
    float gridSize;
    
    public Grid(float gridSize){
        obstructions = new HashMap<Vec2, Obstruction>();
        this.gridSize = gridSize;
    }
    
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
    
    public boolean addObstruction(Vec2 position, int obstructionType){
        int gridX = (int)(position.x/4);
        int gridY = (int)(position.y/4);
        return addObstruction(gridX,gridY,obstructionType);
    }
    
    public boolean removeObstruction(int gridX, int gridY, int obstructionType){
        Vec2 pos = new Vec2(gridX,gridY);
        Obstruction obs = obstructions.get(pos);
        if(obs == null){
            return false;
        }else{
            return obs.removeItem(obstructionType);
        }
    }
    
    public boolean removeObstruction(Vec2 position, int obstructionType){
        int gridX = (int)(position.x/4);
        int gridY = (int)(position.y/4);
        return removeObstruction(gridX,gridY,obstructionType);
    }
    
    /**
     * Gets a array of Obstructions in the <a href="http://en.wikipedia.org/wiki/Von_Neumann_neighborhood">Von Neumann neighborhood</a> of a grid position.
     * @param gridX
     * @param gridY
     * @return array of Obstructions clockwise from North.
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
     * Gets a array of Obstructions in the <a href="http://en.wikipedia.org/wiki/Moore_neighborhood">Moore neighborhood</a> of a grid position.
     * @param gridX
     * @param gridY
     * @return array of Obstructions clockwise from North.
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
