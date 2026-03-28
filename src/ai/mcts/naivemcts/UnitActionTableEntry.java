/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.mcts.naivemcts;

import java.util.List;

import rts.units.Unit;

/**
 *
 * @author santi
 */
public class UnitActionTableEntry {
    public Unit u;
    public int nactions = 0;
    public List<UnitAction1> actions;
    public double[] accum_evaluation;
    public int[] visit_count;
}
