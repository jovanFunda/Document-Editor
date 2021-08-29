package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.swing.view.MainFrame;
import model.repository.Project;

public class NewProjectAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private static int cntPrj = 1;
	
	public NewProjectAction(){
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(NAME, "Novi projekat");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Project temp = new Project("Projekat" + cntPrj, MainFrame.getInstance().getDocumentRepository().getWorkspace());
		
        MainFrame.getInstance().getTree().addProject(temp);
        MainFrame.getInstance().getWorkspaceTree().updateUI();

        cntPrj++;
     
	}

}
