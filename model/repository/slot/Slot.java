package model.repository.slot;

import java.io.Serializable;

import model.observer.IObserver;
import model.repository.node.Node;

public class Slot extends Node implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Element element;
	
	public Slot(String name, Node page) {
		super(name, page);
		this.name = name;
		
		
		element = new Element();
	}
	
	
	public String toString(){
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Element getElement() {
		return element;
	}

	@Override
	public void notifySubscribers(Object notification) {
		// TODO Auto-generated method stub
	}


	@Override
	public void addSubscriber(IObserver sub) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSubscriber(IObserver sub) {
		// TODO Auto-generated method stub
		
	}
}
