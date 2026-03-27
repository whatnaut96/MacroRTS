package ai.puppet;
import java.util.ArrayList;
import util.Pair;

class MoveGenerator{
	ArrayList<ArrayList<Pair<Integer,Integer>>> choices;
	int current=0;
	int player;
	MoveGenerator(ArrayList<ArrayList<Pair<Integer,Integer>>> choices, int player){
		this.choices=choices;
		this.player=player;
	}
	boolean hasNext(){
		return current<choices.size();
	}
	void swapFront(Move bestMove){
		for(int i=0;i<choices.size();i++){
			if(choices.get(i).equals(bestMove.choices)){
				if(i==0){
					break;
				}
				choices.set(i, choices.get(0));
				choices.set(0, bestMove.choices);
				break;
			}
		}
	}
	Move next(){
		return new Move(choices.get(current++),player);
	}
	Move last(){
		return new Move(choices.get(current-1),player);
	}
	void ABcut(){
		current=choices.size();
	}
}



