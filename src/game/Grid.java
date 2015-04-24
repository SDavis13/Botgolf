package game;

import java.util.HashMap;

import org.jbox2d.common.Vec2;

public class Grid {
    Vec2 ballLoc;
    HashMap<Vec2, Obstruction> obstructions;
    
    public Obstruction[] vnNeighborhood(int x, int y){
        Obstruction[] obs = new Obstruction[4];
        obs[0] = obstructions.get(new Vec2(x  ,y+1));
        obs[1] = obstructions.get(new Vec2(x+1,y  ));
        obs[2] = obstructions.get(new Vec2(x  ,y-1));
        obs[3] = obstructions.get(new Vec2(x-1,y  ));
        return obs;
    }

    public Obstruction[] mooreNeighborhood(int x, int y){
        Obstruction[] obs = new Obstruction[8];
        obs[0] = obstructions.get(new Vec2(x  ,y+1));
        obs[1] = obstructions.get(new Vec2(x+1,y+1));
        obs[2] = obstructions.get(new Vec2(x+1,y  ));
        obs[3] = obstructions.get(new Vec2(x+1,y-1));
        obs[4] = obstructions.get(new Vec2(x  ,y-1));
        obs[5] = obstructions.get(new Vec2(x-1,y-1));
        obs[6] = obstructions.get(new Vec2(x-1,y  ));
        obs[7] = obstructions.get(new Vec2(x-1,y+1));
        return obs;
    }
}
