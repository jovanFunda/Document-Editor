package model.repository;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gui.swing.tree.model.TreeItem;
import model.observer.IObserver;
import model.repository.node.Node;
import model.repository.node.NodeComposite;

public class Project extends NodeComposite implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private transient List<IObserver> observeri;
	private transient TreeItem projectTree;
	private List<Document> sharedDocuments;
	private transient boolean changed; 
	private File projectFile;

	public Project(String name, Node workspace) {
		super(name, workspace);
		observeri = new ArrayList<IObserver>();
		sharedDocuments = new ArrayList<Document>(); 
	}
	
	private Object readResolve(){
		observeri = new ArrayList<IObserver>();	
		return this;
	}	

	@Override
	public void addChild(Node child) {
		if (child != null && child instanceof Document){
			Document document = (Document) child;
            if (!this.getChildren().contains(document)){
                this.getChildren().add(document);
            }
        }
	}
	
	public boolean isChanged() {
		return changed;
	}
	
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public void removeChild(Node child) {
		if(child != null && child instanceof Document) {
			Document document = (Document) child;
			if(this.getChildren().contains(document)) {
				this.getChildren().remove(document);
			}
		}
	}

	@Override
	public void addSubscriber(IObserver sub) {
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
			obs.update(new Object());
		}
	}
	
	public TreeItem getProjectTree() {
		return projectTree;
	}
	
	public void setProjectTree(TreeItem projectTree) {
		this.projectTree = projectTree;
	}
	
	public List<Document> getSharedDocuments() {
		if(sharedDocuments == null) {
			sharedDocuments = new ArrayList<Document>();
		}
		return sharedDocuments;
	}
	
	public void addSharedDocument(Document document) {
		sharedDocuments.add(document);
	}
	
	public void removeSharedDocument(Document document) {
		sharedDocuments.remove(document);
	}
	
	
	@Override
	public void setName(String name) {
		this.name = name;
		
		for (Node doc : getChildren()) {
			for (Node page : ((NodeComposite)doc).getChildren()) {
				page.notifySubscribers("Potrebna promena patha");
			}
			
		}
		notifySubscribers("promeni ime");
	}
	
	public File getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}

}
