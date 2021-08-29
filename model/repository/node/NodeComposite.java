package model.repository.node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class NodeComposite extends Node implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Node> children;

	public NodeComposite(String n, Node p) {
		super(n, p);
		this.children = new ArrayList<>();
	}

	public NodeComposite(String n, Node p, List<Node> ch) {
		super(n, p);
		this.children = ch;
	}

	public abstract void addChild(Node child);
	public abstract void removeChild(Node child);
	
	public Node getChildNode(String name) {

		for(Node child:this.getChildren()) {
			if (name.equals(child.getName())) {
				return child;
			}
		}
		
		return null;
		
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}
	
	

}
