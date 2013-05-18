package de.grw.softA;

import java.util.ArrayList;
import java.util.List;

public class ArrayListWithHistory extends ArrayList<String>{

	// composition of the interface 'History'
	History history = new HistoryImpl();

	public void undo() {
		history.undo(this);
	}

	@Override
	public boolean add(String s) {
		// take a snapshot of the existing array (this) before adding to it
		history.takeSnapshot(this);
		return super.add(s);
	}
	
	// extra add function to initialize the array without creating a history
	// redundant after full implementation of the program
	public boolean addNoHistory(String s) {
		return super.add(s);
	}

	@Override
	public String remove(int index) {
		// take a snapshot of the existing array (this) before removing anything
		history.takeSnapshot(this);
		return super.remove(index);
	}
}
