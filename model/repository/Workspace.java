package model.repository;

import java.io.File;

import model.observer.IObserver;
import model.repository.node.Node;
import model.repository.node.NodeComposite;

public class Workspace extends NodeComposite {
	
	private File workspaceFile;

	public Workspace(String name) {
		super(name, null);
		
	}

	@Override
	public void addChild(Node child) {
		if (child != null && child instanceof Project) {
			Project project = (Project) child;
			if (!this.getChildren().contains(project)) {
				this.getChildren().add(project);
			}
		}
	}
	
	@Override
	public void removeChild(Node child) {
		if(child != null && child instanceof Project) {
			Project project = (Project) child;
			if(this.getChildren().contains(project)) {
				this.getChildren().remove(project);
			}
		}
	}

	@Override
	public void addSubscriber(IObserver sub) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSubscriber(IObserver sub) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifySubscribers(Object notification) {
		// TODO Auto-generated method stub
		
	}

	public File getWorkspaceFile() {
		return workspaceFile;
	}
	
	public void setWorkspaceFile(File workspaceFile) {
		this.workspaceFile = workspaceFile;
	}
}
