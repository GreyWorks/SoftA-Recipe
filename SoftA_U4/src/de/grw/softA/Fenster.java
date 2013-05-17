package de.grw.softA;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Fenster {

	static ArrayListWithHistory baseComponents = new ArrayListWithHistory();
	static ArrayListWithHistory usedComponents = new ArrayListWithHistory();
	static JList<Object> listLeft = new JList<Object>();
	static JList<Object> listRight = new JList<Object>();
	static JButton btnUndo = new JButton("Undo");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(300, 400);
		mainWindow.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);

		listLeft.setPreferredSize(new Dimension(150, 200));
		listLeft.setBackground(new Color(200, 210, 255));
		listRight.setPreferredSize(new Dimension(150, 200));
		listRight.setBackground(new Color(200, 255, 210));
		
		
		baseComponents.addNoHistory("Lemon");
		baseComponents.addNoHistory("Vodka");
		baseComponents.addNoHistory("Zucker");
		baseComponents.addNoHistory("askdfhkldsfa");
		
		updateList(listLeft, baseComponents);
		mainWindow.add(listLeft, BorderLayout.LINE_START);
		mainWindow.add(listRight, BorderLayout.LINE_END);
		mainWindow.add(btnUndo, BorderLayout.SOUTH);
		
		listLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		
		listRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		
		
		
		
		
		mainWindow.setVisible(true);
	}

	private static void updateList(JList<Object> guiList, ArrayListWithHistory dataList) {
		guiList.setListData(dataList.toArray());
	}
	
}
