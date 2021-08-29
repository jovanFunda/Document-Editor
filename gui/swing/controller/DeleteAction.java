package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import exceptions.ErrorType;
import gui.swing.tree.controller.CustomTreeSelectionListener;
import gui.swing.tree.model.TreeItem;
import gui.swing.view.MainFrame;
import main.Core;
import model.repository.Document;
import model.repository.Page;
import model.repository.Project;
import model.repository.node.Node;

public class DeleteAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public DeleteAction(){
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(NAME, "Delete");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		TreeItem selectedItem = MainFrame.getInstance().getTree().getSelectedNode();
		
		if(selectedItem == null) {
			Core.getInstance().getErrorHandler().generateError(ErrorType.node_nothing_selected);
		} else if(selectedItem.getNodeModel() instanceof Document) {
			
			MainFrame.getInstance().getTree().removeDocument((Document)selectedItem.getNodeModel());
			MainFrame.getInstance().getTabbedPane().removeAll();
			
			Project proj = (Project) selectedItem.getNodeModel().getParent();
			
			for (Node doc : proj.getChildren()) {
				doc.notifySubscribers("Prikaci se na tabbedPane");
			}
			
			MainFrame.getInstance().getTree().setSelectedNode(null);
			
		} else if(selectedItem.getNodeModel() instanceof Project) {
			
			MainFrame.getInstance().getTree().removeProject((Project)selectedItem.getNodeModel());
			MainFrame.getInstance().getTabbedPane().removeAll();
			MainFrame.getInstance().getTabbedPane().updateUI();
			MainFrame.getInstance().getTree().setSelectedNode(null);
			
		} else if(selectedItem.getNodeModel() instanceof Page) {
			
			MainFrame.getInstance().getTree().removePage((Page) selectedItem.getNodeModel());
			
			Page p = (Page)selectedItem.getNodeModel();
			p.notifySubscribers("Obrisan page");
			
			MainFrame.getInstance().getTree().setSelectedNode(null);
		}
		
	}

}
