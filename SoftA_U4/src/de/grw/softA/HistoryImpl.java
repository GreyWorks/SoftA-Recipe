package de.grw.softA;

import java.util.ArrayList;

public class HistoryImpl implements History {

	private ArrayList<ArrayList<String>> snapshots = new ArrayList<ArrayList<String>>();
	private int currentSnap = -1;
	
	@Override
	public void snapshot(ArrayList<String> baseList) {
		currentSnap++;
		ArrayList<String> listToAdd = (ArrayList<String>) baseList.clone();
		snapshots.add(currentSnap, listToAdd);	
	}

	@Override
	public void undo(ArrayList<String> baseList) {
		if(currentSnap > -1){
			ArrayList<String> lastList = snapshots.get(currentSnap);
			baseList.clear();
			baseList.addAll(lastList);
			currentSnap--;
		}	
	}
	


}
