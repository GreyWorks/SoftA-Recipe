package de.grw.softA;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Fenster {

	// class variables for the two arrays, the controls to display the arrays and the undo button
	static ArrayListWithHistory baseComponents = new ArrayListWithHistory();
	static ArrayListWithHistory usedComponents = new ArrayListWithHistory();
	static JList<Object> listLeft = new JList<Object>();
	static JList<Object> listRight = new JList<Object>();
	static JButton btnUndo = new JButton("Undo");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// setup GUI in a seperate function
		initializeGUI();		
		
		// initialize the base components list to test functionality
		baseComponents.addNoHistory("Lemon");
		baseComponents.addNoHistory("Vodka");
		baseComponents.addNoHistory("Zucker");
		baseComponents.addNoHistory("askdfhkldsfa");
		
		updateList(listLeft, baseComponents);	
		
	}

	private static void initializeGUI() {
		
		// main window setup
		JFrame mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(300, 400);
		mainWindow.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
		
		// lists setup
		listLeft.setPreferredSize(new Dimension(130, 150));
		listLeft.setBackground(new Color(200, 210, 255));
		
		listRight.setPreferredSize(new Dimension(130, 200));
		listRight.setBackground(new Color(200, 255, 210));
		
		// add all controls to main window and set their position
		mainWindow.add(listLeft, BorderLayout.LINE_START);
		mainWindow.add(listRight, BorderLayout.LINE_END);
		mainWindow.add(btnUndo, BorderLayout.SOUTH);
		
		// as part of the GUI elements events need to be registered
		registerEvents(listLeft, listRight, btnUndo);
			
		mainWindow.setVisible(true);		
		
	}

	private static void registerEvents(JList<Object> listLeft2,
			JList<Object> listRight2, JButton btnUndo2) {
		// double click on a list element in the left list adds the clicked element
		// to the right list and removes it from the left
		listLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// mouse adapter only on double click
				if(e.getClickCount() == 2) {
					if(baseComponents.size()>0) {
						int index = listLeft.locationToIndex(e.getPoint());
						usedComponents.add(baseComponents.get(index));
						updateList(listRight, usedComponents);
						baseComponents.remove(index);
						updateList(listLeft, baseComponents);
					}
				}
			}
		});
		
		// double click on a list element in the right list adds the clicked element
		// to the left list and removes it from the right
		listRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// mouse adapter only on double click
				if(e.getClickCount() == 2) {
					if(usedComponents.size()>0) {
						int index = listRight.locationToIndex(e.getPoint());
						baseComponents.add(usedComponents.get(index));
						updateList(listLeft, baseComponents);
						usedComponents.remove(index);
						updateList(listRight, usedComponents);
					}
				}
			}
		});
		
		btnUndo.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				baseComponents.undo();
				usedComponents.undo();
				updateList(listLeft, baseComponents);
				updateList(listRight, usedComponents);	
			}
		});	
		
	}
	
	// refactored code since the update of the lists is used in several functions
	private static void updateList(JList<Object> guiList, ArrayListWithHistory dataList) {
		guiList.setListData(dataList.toArray());
	}
	
}
