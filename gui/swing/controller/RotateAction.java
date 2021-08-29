package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import gui.swing.view.MainFrame;
import main.Core;

public class RotateAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public RotateAction() {
	//	putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
	//	        KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../../../slike/rotate32.png"));
		putValue(NAME, "Rotate");
		putValue(SHORT_DESCRIPTION, "Rotate selected item");
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
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getLastSelectedPage() != null) {
			if(MainFrame.getInstance().getLastSelectedPageView().getPage().getStateManager().getCurrentState().toString().equals("select")) {
				Core.getInstance().getSlotHandler().calculateOperation("rotate");	
				MainFrame.getInstance().getLastSelectedPageView().getPage().getStateManager().setNullState();
			} 
		} else {
			System.out.println("Nema izabrane stranice");
		}
		
	}

}
