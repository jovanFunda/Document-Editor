package gui.swing.tree.controller;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import model.repository.Document;
import model.repository.Page;
import model.repository.Project;
import model.repository.Workspace;
import model.repository.node.Node;
import model.repository.slot.Slot;
import gui.swing.tree.model.TreeItem;
import gui.swing.view.JTabbedPaneCloseButton;
import gui.swing.view.MainFrame;


public class CustomTreeSelectionListener implements TreeSelectionListener {

	private TreePath path;
	private int selectedType;
	private static CustomTreeSelectionListener instance;
	
	private CustomTreeSelectionListener() {}
	
	public static CustomTreeSelectionListener getInstance(){
        if(instance==null){
            instance = new CustomTreeSelectionListener();
            
        }
        return instance;
    }
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		setPath(e.getPath());
		
		MainFrame.getInstance().getTree().setSelectedNode((TreeItem)getPath().getLastPathComponent());
        
        selectedType = checkWhatType();
        
        if(selectedType == 2) {
        	
        	Document document = ((Document) MainFrame.getInstance().getTree().getSelectedNode().getNodeModel());
        	document.notifySubscribers(new Object());
        	
        } else if(selectedType == 1) {
        	
        	MainFrame.getInstance().getTabbedPane().removeAll();    
        	Project selectedProject = ((Project) MainFrame.getInstance().getTree().getSelectedNode().getNodeModel());
        	
        	for (Node doc : selectedProject.getChildren()) {
        		((Document)doc).notifySubscribers("Prikaci se na tabbedPane");
			}
        	
        	for (Node doc : selectedProject.getSharedDocuments()) {
        		((Document)doc).notifySubscribers("Prikaci se na tabbedPane");
			}
        	
        	if(selectedProject.getSharedDocuments().size() > 0) {
        		JTabbedPaneCloseButton tabbedPane = MainFrame.getInstance().getTabbedPane();
        		tabbedPane.setTitleAt(tabbedPane.getTabCount()-1, tabbedPane.getTitleAt(tabbedPane.getTabCount()-1) + "*");
        	}
        }
	}
	
	private int checkWhatType() {
		if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Workspace) {
			return 0;
		} else if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Project) {
			return 1;
		} else if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Document) {
			return 2;
		} else if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Page) {
			return 3;
		} else if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Slot) {
			return 4;
		} else {
			return -1;
		}
	}
	
	public int getSelectedType() {
		return selectedType;
	}
	
	public void setPath(TreePath path) {
		this.path = path;
	}
	
	public TreePath getPath() {
		return path;
	}
}

