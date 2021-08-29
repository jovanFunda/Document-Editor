package model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gui.swing.tree.model.TreeItem;
import gui.swing.view.PageView;
import model.observer.IObserver;
import model.observer.helpClasses.ObserverHelperOne;
import model.repository.node.Node;
import model.repository.node.NodeComposite;

public class Document extends NodeComposite implements Serializable {
	
	private transient List<IObserver> observeri;
	private transient TreeItem documentTree;
	
	private Object readResolve(){
		observeri = new ArrayList<IObserver>();	
		return this;
	}	

	public Document(String name, Node project) {
		super(name, project);
		observeri = new ArrayList<IObserver>();
	}

	@Override
	public void addChild(Node child) {
		if (child != null &&  child instanceof Page){
			Page page = (Page) child;
			if (!this.getChildren().contains(page)){
				this.getChildren().add(page);
			}
		}	
	}

	@Override
	public void removeChild(Node child) {
		if(child != null && child instanceof Page) {
			Page page = (Page) child;
			if(this.getChildren().contains(page)) {
				this.getChildren().remove(page);
			}
		}
	}

	@Override
	public void addSubscriber(IObserver sub) {
		if(observeri == null) {
			observeri = new ArrayList<IObserver>();
		}
		if(!observeri.contains(sub)) {
			observeri.add(sub);
		}
		
	}

	@Override
	public void removeSubscriber(IObserver sub) {
		if(observeri.contains(sub)) {
			observeri.remove(sub);
		}
		
	}

	@Override
	public void notifySubscribers(Object notification) {
		
		for (IObserver obs : observeri) {
			
			if(notification instanceof ObserverHelperOne) {
				obs.update((ObserverHelperOne)notification);
			} else if(notification instanceof PageView) {
				obs.update((PageView)notification);		
			} else if(notification.equals("Prikaci se na tabbedPane")) {
				obs.update("Prikaci se na tabbedPane");
			} else if (notification instanceof String && !notification.equals("Prikaci se na tabbedPane")){
				//  Ovako menjamo naziv DocumentViewa / Taba
				obs.update((String)notification);
			}
		}
	}
	
	public TreeItem getDocumentTree() {
		return documentTree;
	}
	
	public void setDocumentTree(TreeItem documentTree) {
		this.documentTree = documentTree;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
		notifySubscribers("promeni ime");
	}
}
