package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import gui.swing.view.MainFrame;
import main.Core;

public class ScaleAction extends AbstractAction {
	

	private static final long serialVersionUID = 1L;

	public ScaleAction() {
		putValue(SMALL_ICON, loadIcon("../../../slike/scale32.jfif"));
		putValue(NAME, "Scale");
		putValue(SHORT_DESCRIPTION, "Scale selected item");
	}
	
	public Icon loadIcon(String fileName){
		URL imageURL = getClass().getResource(fileName);
		Icon icon = null;
		
		if (imageURL != null) {                      
	        icon = new ImageIcon(imageURL);
	    } else {                                     
	        System.err.println("Resource not found: " + fileName);
	    }

		return icon;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(MainFrame.getInstance().getLastSelectedPage() != null) {
			if(MainFrame.getInstance().getLastSelectedPageView().getPage().getStateManager().getCurrentState().toString().equals("select")) {
				Core.getInstance().getSlotHandler().calculateOperation("scale");	
			} 
		} else {
			System.out.println("Nema izabrane stranice");
		}
	}

	
}