package graphics.model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import graphics.model.elements.SlotDevice;
import model.observer.IObserver;
import model.observer.ISubject;


public class PageModel implements ISubject, Serializable  {
	
	private static final long serialVersionUID = 1L;

	private transient String name;
	
	protected ArrayList<SlotDevice> pageElements = new ArrayList<SlotDevice>();
	private transient List<IObserver> observeri = new ArrayList<IObserver>();
	
	private Object readResolve(){
		observeri = new ArrayList<IObserver>();	
		return this;
	}	
	
	public int getElementCount(){
		return pageElements.size();
	}
	
	public ArrayList<SlotDevice> getPageElements() {
		return pageElements;
	}
	
	public Iterator<SlotDevice> getDeviceIterator(){
		return pageElements.iterator();
	}
	
	public void addSlotElements(SlotDevice device){
		if(pageElements.contains(device)) {
			return;
		}
		pageElements.add(device);
		notifySubscribers(this);
	}	
	 
	public int getDeviceAtPosition(Point point) {
		for(int i=0;i<getDeviceCount();i++){
			SlotDevice device = getDeviceAt(i);
			if(device != null) {
				
				device.notifySubscribers(point);
				if(device.isElementAt()) {
					return i;
				}
				
			}
		}
		return -1;
	}	
	
	public int getDeviceCount(){
		return pageElements.size();
	}
	
	public SlotDevice getDeviceAt(int i){
		return pageElements.get(i);
	}
	
	public void removeElement(SlotDevice painter) {
        pageElements.remove(painter);
    }

	@Override
	public void addSubscriber(IObserver obs) {
		if(obs == null)
			return;
		if(this.observeri == null)
			readResolve();
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
		
		if(observeri == null) {
			observeri = new ArrayList<IObserver>();
		}
		
		for(int i = observeri.size()-1; i > 0; i--) {
			observeri.get(i).update((PageModel)notification);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
