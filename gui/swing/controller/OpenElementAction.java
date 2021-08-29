package gui.swing.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.swing.view.OpenElementDialog;
import gui.swing.view.MainFrame;

public class OpenElementAction extends AbstractAction{
	
	private static final long serialVersionUID = 1L;
	
	public OpenElementAction() {
		putValue(NAME, "Add Element");
		putValue(SHORT_DESCRIPTION, "Add to selected item");
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if((MainFrame.getInstance().getLastSelectedSlot() != null) 
				&& (MainFrame.getInstance().getLastSelectedSlot().getDevice().getSlot().getElement().getTip() == null)) {
			new OpenElementDialog();
		}
		else {
			MainFrame.getInstance().getLastSelectedSlot().getElementView().setVisible(true);
		}
		
	}

}
