package gui.swing.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.swing.tree.controller.CustomTreeSelectionListener;
import gui.swing.tree.model.TreeItem;
import gui.swing.view.MainFrame;
import model.repository.Document;
import model.repository.Project;
import model.repository.SharedDocument;
import model.repository.node.Node;
import model.repository.Page;

public class ShareDocumentAction {

	private static final long serialVersionUID = 1L;
	private Document document;
	private Project project;

	public ShareDocumentAction(Document document, Project shareTo) {
		System.out.println(shareTo.getName());
		this.document = document;
		this.project = shareTo;
		actionPerformed();
	}
	
	public void actionPerformed() {
		
		//System.out.println("prolaz");
		Document doc = new SharedDocument(document.getName() + "*", project);
		TreeItem docItem = new TreeItem(doc);
		
		project.getProjectTree().add(docItem);
		
		project.addSharedDocument(document);
		
		MainFrame.getInstance().getTree().addDocument(doc);
		MainFrame.getInstance().getTree().setSelectedNode(docItem);
		
		for (Node page : document.getChildren()) {
			Page pa = new Page(page.getName() + "*", doc);
			docItem.add(new TreeItem((Page)pa));
			MainFrame.getInstance().getTree().addPage((Page)pa);
		}
		
		MainFrame.getInstance().getWorkspaceTree().updateUI();
		
	}
}
