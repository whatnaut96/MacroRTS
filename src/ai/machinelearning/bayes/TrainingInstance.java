/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.machinelearning.bayes;

import java.util.ArrayList;
import java.util.List;
import rts.GameState;
import rts.units.Unit;

/**
 *
 * @author santi
 */
public class TrainingInstance {
    public GameState gs;
    public Unit u;
    public UnitAction1 ua;
    
    public TrainingInstance(GameState a_gs, long uID, UnitAction1 a_ua) throws Exception {
        gs = a_gs;
        u = gs.getUnit(uID);
        if (u==null) throw new Exception("Unit " + uID + " not found!");
        ua = a_ua;
        if (ua!=null && ua.getType() == UnitAction1.TYPE_ATTACK_LOCATION) {
            // turn into relative:
            ua = new UnitAction1(UnitAction1.TYPE_ATTACK_LOCATION, ua.getLocationX() - u.getX(), ua.getLocationY() - u.getY());
        }
    }
    
    
    public List<Integer> getPossibleActions(List<UnitAction1> allPossibleActions) {
        List<Integer> l = new ArrayList<>();
        for(UnitAction1 ua:u.getUnitActions(gs)) {
            if (ua.getType()== UnitAction1.TYPE_ATTACK_LOCATION) {
                ua = new UnitAction1(UnitAction1.TYPE_ATTACK_LOCATION, ua.getLocationX() - u.getX(), ua.getLocationY() - u.getY());
            }
            l.add(allPossibleActions.indexOf(ua));
        }            
        return l;
    }
}
