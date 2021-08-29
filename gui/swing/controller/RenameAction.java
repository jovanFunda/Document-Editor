package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.swing.tree.controller.CustomTreeSelectionListener;
import gui.swing.view.MainFrame;
import gui.swing.view.RenameDialog;

public class RenameAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public RenameAction(){
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(NAME, "Preimenuj cvor");
		putValue(SHORT_DESCRIPTION, "Preimenuj cvor");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(MainFrame.getInstance().getTree().getSelectedNode() == null) 
			return;
			//generateError("Nije selektovan cvor za preimenovanje");
		
		RenameDialog renameDialog = new RenameDialog();
	}
}

