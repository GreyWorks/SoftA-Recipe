package de.grw.softA;

import java.util.ArrayList;

public class HistoryImpl implements History {
	
	// composition of the class ArrayList
	private ArrayList<ArrayList<String>> snapshots = new ArrayList<ArrayList<String>>();
	// index pointer for the current position in the array list snapshots
	// initialized at -1 to start with 0 for the first entry
	private int currentSnap = -1;
	
	// takes a snapshot of the existing list before it gets changed
	@Override
	public void takeSnapshot(ArrayList<String> baseList) {
		currentSnap++;
		// makes a copy of the existing array baseList
		ArrayList<String> listToAdd = (ArrayList<String>) baseList.clone();
		// adds the copy to the snapshots array list
		snapshots.add(currentSnap, listToAdd);	
	}

	@Override
	public void undo(ArrayList<String> baseList) {
		// only runs if the list isn't empty
		if(currentSnap > -1){
			// finds the last entry in the snapshots array
			ArrayList<String> lastList = snapshots.get(currentSnap);
			// emptys the current list to be able to add the content of the last snapshot
			baseList.clear();
			baseList.addAll(lastList);
			currentSnap--;
		}	
	}
	


}
