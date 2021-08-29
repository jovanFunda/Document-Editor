package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.swing.view.MainFrame;

public class UndoAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public UndoAction(){
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undo");
	}
	
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getLastSelectedPage().getCommandManager().undoCommand();
	}

}
