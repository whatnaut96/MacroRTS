/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.portfolio.portfoliogreedysearch;

import rts.GameState;
import rts.units.Unit;

/**
 *
 * @author santi
 */
public abstract class UnitScript {
    public abstract UnitAction1 getAction(Unit u, GameState gs);
    public abstract UnitScript instantiate(Unit u, GameState gs);
}
