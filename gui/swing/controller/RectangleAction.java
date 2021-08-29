package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import gui.swing.view.MainFrame;

public class RectangleAction extends AbstractAction{

	private static final long serialVersionUID = 1L;

	public RectangleAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../../../slike/rectangle.jpg"));
		putValue(NAME, "Rectangle");
		putValue(SHORT_DESCRIPTION, "Rectangle");
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
		if(MainFrame.getInstance().getLastSelectedPage() != null)
			MainFrame.getInstance().getLastSelectedPage().startRectangleState();
	}

}
