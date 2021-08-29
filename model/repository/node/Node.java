package model.repository.node;

import java.io.Serializable;

import gui.swing.view.MainFrame;
import model.observer.ISubject;
import model.repository.Document;

public abstract class Node implements ISubject, Serializable {

	protected String name;
	protected Node parent;
	
	public Node(String n, Node p) {
		name = n;
		parent = p;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Node) {
			Node otherObj = (Node) obj;
//			if(this.getName() == null) {
//				return false;
//			}
			return this.getName().equals(otherObj.getName());
		}
		return false;
	}

	public String getName() {
		 return this.name;
	 }

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setName(String name) {
		if(this instanceof Document) {
			MainFrame.getInstance().getTabbedPane().updateUI();
		}
		this.name = name;
		notifySubscribers("promeni ime");
	}
}
