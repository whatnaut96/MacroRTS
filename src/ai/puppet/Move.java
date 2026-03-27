package ai.puppet;
import java.util.ArrayList;
import java.util.stream.Collectors;

import util.Pair;

public class Move{
	ArrayList<Pair<Integer,Integer>> choices;
	int player;

	public Move(ArrayList<Pair<Integer,Integer>> choices, int player){
		this.choices=choices;
		this.player=player;
	}
	public String toString(ConfigurableScript<?> script){
		return "choices: "+choices.stream().map(
				(Pair<Integer,Integer>  p)->
                        new Pair<>(script.choicePointValues[p.m_a].name(), p.m_b))
				.collect(Collectors.toList())+", player: "+player;
	}
	
}
