package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import exceptions.ErrorType;
import gui.swing.tree.controller.CustomTreeSelectionListener;
import gui.swing.tree.model.TreeItem;
import gui.swing.view.DocumentView;
import gui.swing.view.MainFrame;
import main.Core;
import model.repository.Document;
import model.repository.Project;

public class NewDocumentAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	
	private static int cntDoc = 1;
	
	public NewDocumentAction(){
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(NAME, "Novi dokument");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		TreeItem selectedItem = MainFrame.getInstance().getTree().getSelectedNode();
		
		if(selectedItem == null) {
			Core.getInstance().getErrorHandler().generateError(ErrorType.node_nothing_selected);
			return;
		}
		
		if(selectedItem.getNodeModel() instanceof Project) {
					
			Document temp = new Document("Dokument" + cntDoc, (Project)selectedItem.getNodeModel());
			DocumentView view = new DocumentView(temp);
			
			MainFrame.getInstance().getTabbedPane().add(view);
			MainFrame.getInstance().getTree().addDocument(temp);			
					
			MainFrame.getInstance().getWorkspaceTree().updateUI();
			MainFrame.getInstance().getTabbedPane().updateUI();
			
			cntDoc++;
		} 
	}
}
