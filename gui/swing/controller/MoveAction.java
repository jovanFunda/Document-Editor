package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import exceptions.ErrorType;
import gui.swing.view.MainFrame;
import main.Core;

public class MoveAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public MoveAction() {
		putValue(SMALL_ICON, loadIcon("../../../slike/moveElement32.png"));
		putValue(NAME, "Move");
		putValue(SHORT_DESCRIPTION, "Move selected slot");
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
			if(MainFrame.getInstance().getLastSelectedPage().getStateManager().getCurrentState().toString().equals("select")) {
				Core.getInstance().getSlotHandler().calculateOperation("move");
				MainFrame.getInstance().getLastSelectedPageView().getPage().getStateManager().setNullState();
			} 
		} else {
			Core.getInstance().getErrorHandler().generateError(ErrorType.element_not_selected);
			return;
		}
	}
}