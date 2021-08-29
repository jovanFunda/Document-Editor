package gui.swing.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import model.repository.Document;

public class MainToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;
	
	public MainToolBar() {
		
		JButton podeliDokument = new JButton("Podeli dokument");
		podeliDokument.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			if(MainFrame.getInstance().getTree().getSelectedNode() == null) {
				return;
			} else if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Document) {
					new ShareDocumentDialog((Document) MainFrame.getInstance().getTree().getSelectedNode().getNodeModel());
				}
			}
		});
		
		add(MainFrame.getInstance().getActionManager().getNewProjectAction());
		add(MainFrame.getInstance().getActionManager().getNewDocumentAction());
		add(MainFrame.getInstance().getActionManager().getNewPageAction());
		add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
		add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
		add(MainFrame.getInstance().getActionManager().getRenameAction());
		add(MainFrame.getInstance().getActionManager().getDeleteAction());
		add(podeliDokument);
		add(MainFrame.getInstance().getActionManager().getUndoAction());
		add(MainFrame.getInstance().getActionManager().getRedoAction());
		add(MainFrame.getInstance().getActionManager().getSaveWorkspaceAction());
		add(MainFrame.getInstance().getActionManager().getOpenWorkspaceAction());
	}	
}