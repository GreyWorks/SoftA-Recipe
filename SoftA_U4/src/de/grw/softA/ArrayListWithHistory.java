package de.grw.softA;

import java.util.ArrayList;
import java.util.List;

public class ArrayListWithHistory extends ArrayList<String>{

	History hist = new HistoryImpl();
	


	public void undo() {
		hist.undo(this);
	}

	@Override
	public boolean add(String s) {
		hist.snapshot(this);
		return super.add(s);
	}
	
	public boolean addNoHistory(String s) {
		return super.add(s);
	}

	@Override
	public String remove(int index) {
		hist.snapshot(this);
		return super.remove(index);
	}
	

	
	

}
