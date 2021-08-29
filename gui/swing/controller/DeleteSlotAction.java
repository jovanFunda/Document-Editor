package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import commands.AbstractCommand;
import commands.DeleteDeviceCommand;
import graphics.model.elements.SlotDevice;
import gui.swing.view.MainFrame;

public class DeleteSlotAction extends AbstractAction{

	private static final long serialVersionUID = 1L;

	public DeleteSlotAction() {
		putValue(SMALL_ICON, loadIcon("../../../slike/delete32.jpg"));
		putValue(NAME, "Delete");
		putValue(SHORT_DESCRIPTION, "Delete selected item");
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
		
		ArrayList<AbstractCommand> komande = new ArrayList<AbstractCommand>();
		
		for (SlotDevice painter : MainFrame.getInstance().getPageSelectionModel().getSelectionList()) {
			MainFrame.getInstance().getLastSelectedPageView().getPage().removeChild(painter);
			MainFrame.getInstance().getLastSelectedPageView().getPage().getPageModel().removeElement(painter);
			komande.add(new DeleteDeviceCommand(MainFrame.getInstance().getLastSelectedPage(), painter));
		}
		
		MainFrame.getInstance().getLastSelectedPage().getCommandManager().addCommand(komande);
		if(MainFrame.getInstance().getLastSelectedSlot() == null) {
			return;
		}
        MainFrame.getInstance().getLastSelectedPageView().getPage().getPageModel().removeElement(MainFrame.getInstance().getLastSelectedSlot().getDevice());
        MainFrame.getInstance().getWorkspaceTree().updateUI();
        MainFrame.getInstance().getPalette().getSelect().clearSelection();
        MainFrame.getInstance().getLastSelectedPageView().repaint();
		
	}

}
