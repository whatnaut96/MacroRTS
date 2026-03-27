package ai.puppet;

import java.util.Random;

import rts.GameState;
import util.Pair;


class CacheEntry{
	PuppetGameState _state;
	CacheEntry(PuppetGameState state){
		_state=state;
	}
	CacheEntry(){}
}

class CacheTable
{
	CacheEntry[] _entries;

	CacheTable(int size)
	{
		_entries=new CacheEntry[size];
		for(int i=0;i<size;i++){
			_entries[i]=new CacheEntry();
		}
	}
	void store(PuppetGameState origState, PuppetGameState newState)
	{
		_entries[newState.getHash()%_entries.length]._state=newState;
	}
	CacheEntry lookup(PuppetGameState state, int depth, Move move1, Move move2)
	{
		int hash=state.getHash(depth, move1, move2);
		CacheEntry entry=_entries[hash % _entries.length];
		if(entry._state!=null&&entry._state.getHash()==hash){
			return entry;
		}else{
			return null;
		}
	}
}
