package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.swing.tree.controller.CustomTreeSelectionListener;
import gui.swing.tree.model.TreeItem;
import gui.swing.view.MainFrame;
import gui.swing.view.PageView;
import model.repository.Document;
import model.repository.Page;

public class NewPageAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	static public int cntPage = 1;
	
	public NewPageAction(){
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(NAME, "Nova stranica");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		TreeItem selectedItem = MainFrame.getInstance().getTree().getSelectedNode();
		
		if(selectedItem.getNodeModel() instanceof Document) {
				
			Page temp = new Page("Stranica" + cntPage, selectedItem.getNodeModel());	
			PageView view = new PageView(temp);
			
			temp.notifySubscribers(new String("Kreirana stranica"));
			
			MainFrame.getInstance().getTree().addPage(temp);
				
			cntPage++;
			
		} else {
			//generateError("Nije selektovan dokument za dodavanje stranice");
		}
	}

}
