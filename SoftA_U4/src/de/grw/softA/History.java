package de.grw.softA;

import java.util.ArrayList;

public interface History {
	
	public void takeSnapshot(ArrayList<String> baseList);
	
	public void undo(ArrayList<String> baseList);


}
