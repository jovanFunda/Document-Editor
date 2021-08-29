package gui.swing.tree.model;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import model.observer.IObserver;
import model.repository.Document;
import model.repository.Page;
import model.repository.Project;
import model.repository.node.Node;
import model.repository.node.NodeComposite;

public class TreeItem  extends DefaultMutableTreeNode implements IObserver {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
    private Node nodeModel;
    
	public TreeItem(String name, Node nodeModel) {
		this.name = name;
		this.nodeModel = nodeModel;
		nodeModel.addSubscriber(this);
	}
	
	public TreeItem(Node nodeModel) {
		this.nodeModel = nodeModel;
		this.name = nodeModel.getName();
	}
	
    @Override
    public boolean isLeaf() {
        if(nodeModel instanceof NodeComposite)
            return false;
        return true;
    }
    
    @Override
    public Enumeration children() {
        if(nodeModel instanceof NodeComposite)
            return (Enumeration) ((NodeComposite) nodeModel).getChildren();
        return null;
    } 
	
	private int findIndexByChild(TreeItem node) {
		if(this.nodeModel instanceof NodeComposite) {
			return ((NodeComposite)this.nodeModel).getChildren().indexOf(node.getNodeModel());
		}
		return -1;
	}
	
	public String getName() {
		return name;
	}

	public Node getNodeModel() {
		return nodeModel;
	}

	@Override
	public String toString() {
        return name;	
    }

    public void setName(String name) {
    	this.name = name;
	    this.nodeModel.setName(name);
	    if(nodeModel instanceof Document) {
	    	((Document)nodeModel).notifySubscribers(name);
	    } else if(nodeModel instanceof Page) {
	    	((Page)nodeModel).notifySubscribers(name);
	    } else if(nodeModel instanceof Project) {
	    	((Project)nodeModel).notifySubscribers(name);
	    }
    }

	@Override
	public void update(Object notification) {
		name = (String)notification;
	}

}
