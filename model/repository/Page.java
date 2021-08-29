package model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import commands.ICommandManager;
import graphics.model.PageModel;
import gui.swing.tree.model.TreeItem;
import gui.swing.view.MainFrame;
import main.Core;
import model.observer.IObserver;
import model.repository.node.Node;
import model.repository.node.NodeComposite;
import model.repository.slot.Slot;
import model.state.page.StateManager;

public class Page extends NodeComposite implements Serializable {

	private transient List<IObserver> observeri;
	private transient String ceoString;
	private PageModel pageModel = new PageModel();
	private StateManager stateManager;
	private ICommandManager commandManager;
	
	private transient Slot selectedSlot;
	private transient TreeItem pageTree;
	
	private Object readResolve(){
		observeri = new ArrayList<IObserver>();	
		return this;
	}	
	
	public Slot getSelectedSlot() {
		return selectedSlot;
	}
	
	public void setSelectedSlot(Slot selectedSlot) {
		this.selectedSlot = selectedSlot;
	}
	
	public TreeItem getPageTree() {
		return pageTree;
	}

	public void setPageTree(TreeItem pageTree) {
		this.pageTree = pageTree;
	}
	
	
	public PageModel getPageModel() {
		return pageModel;
	}
			
	public Page(String name, Node document) {
		super(name, document);
		
		stateManager = new StateManager(this);
		commandManager = Core.getInstance().newCommandManager();
		
		String imeStranice = getName();
		String imeDokumenta = document.getName();
		String imeProjekta = document.getParent().getName();
		ceoString = imeProjekta + " - " + imeDokumenta + " - " + imeStranice;
		
		observeri = new ArrayList<IObserver>();
	}

	@Override
	public void addChild(Node child) {
		if (child != null &&  child instanceof Slot){
			Slot slot = (Slot) child;
			if (!this.getChildren().contains(slot)){
				this.getChildren().add(slot);
			}
		}
	}
	
	@Override
	public void removeChild(Node child) {
		if(child != null && child instanceof Slot) {
			Slot slot = (Slot) child;
			if(this.getChildren().contains(slot)) {
				this.getChildren().remove(slot);
			}
		}
	}

	@Override
	public void addSubscriber(IObserver obs) {
		if(obs == null)
			return;
		if(this.observeri == null)
			this.observeri = new ArrayList<>();
		if(this.observeri.contains(obs))
			return;
		this.observeri.add(obs);
	}

	@Override
	public void removeSubscriber(IObserver obs) {
		if(obs == null || this.observeri == null || !this.observeri.contains(obs))
			return;
		this.observeri.remove(obs);
	}

	@Override
	public void notifySubscribers(Object notification) {
		
		if(notification == null || this.observeri == null || this.observeri.isEmpty())
			return;
		
		if(notification.equals("Obrisan page")) {
			for (IObserver listener : observeri) {
				listener.update("Obrisan page");	
			}
		} else if(notification.equals("Potrebna promena patha")) {
			for (IObserver listener : observeri) {
					if(((String)notification).equals("Potrebna promena patha")) {
						listener.update("Potrebna promena patha");
					}	
			}
			
			MainFrame.getInstance().getTabbedPane().removeAll();  
			
			if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Document) {
				Project selectedProject = ((Project) MainFrame.getInstance().getTree().getSelectedNode().getNodeModel().getParent());
        	
        		for (Node doc : selectedProject.getChildren()) {
        			((Document)doc).notifySubscribers("Prikaci se na tabbedPane");
				}
			}
			
		} else {
			// notification predstavlja novo ime stranice, pozvano je iz TreeView-a
			for (IObserver listener : observeri) {
				if(notification instanceof String) {
					if(((String)notification).equals("Kreirana stranica")) {
						listener.update((String)notification);
					}
				} else if(notification instanceof Page) {
					listener.update((Page)notification);
				}
				
			}
		}
	}

	public List<IObserver> getObserveri() {
		return observeri;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		this.ceoString = this.parent.getParent().getName() + " - " + this.parent.getName() + " - " + name;
		notifySubscribers(this);
	}
	
	public void startCircleState() {
		stateManager.setCircleState();
	}
	
	public void startSelectState() {
		stateManager.setSelectState();
	}
	
	public void startRectangleState(){
		stateManager.setRectangleState();
	}	
	
	public void startTriangleState(){
		stateManager.setTriangleState();
	}		
	
	public void startNullState() {
		stateManager.setNullState();
	}
	
	public StateManager getStateManager() {
		return stateManager;
	}
	
	public ICommandManager getCommandManager() {
		return commandManager;
	}
}

