package graphics.model;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultSingleSelectionModel;

import graphics.model.elements.SlotDevice;
import model.observer.IObserver;
import model.observer.ISubject;

public class PageSelectionModel extends DefaultSingleSelectionModel implements ISubject {

	private static final long serialVersionUID = 1L;
	
	private List<IObserver> observeri = new ArrayList<IObserver>();
	private ArrayList<SlotDevice> selectionList = new ArrayList<SlotDevice>();
	
	
	public void addToSelectionList(SlotDevice element) {
		selectionList.add(element);
		notifySubscribers(this);
		
		for (SlotDevice sp : selectionList) {
			if(sp != null) {
				sp.notifySubscribers(new Color(180, 180, 180));
			}
		}
	}
	
	
	public void addToSelectionList(ArrayList<SlotDevice> list) {
		selectionList.addAll(list);
		notifySubscribers(this);
	}
	
	
	public int getSelectionListSize() {
		return selectionList.size();
	}
	
	
	public SlotDevice getElementFromSelectionListAt(int index) {
		return (SlotDevice)selectionList.get(index);
	}
	
	
	public int getIndexByObject(SlotDevice element) {
		return selectionList.indexOf(element);
	}
	
	
	public void removeFromSelectionList(SlotDevice element) {
		selectionList.remove(element);
		notifySubscribers(this);
	}
	
	
	public void removeAllFromSelectionList() {
		for (SlotDevice sp : selectionList) {
			if(sp != null) {
				sp.notifySubscribers(Color.WHITE);
			}
		}
		selectionList.clear();
		notifySubscribers(this);
	}
	
	
	public ArrayList<SlotDevice>  getSelectionList() {
		return selectionList;
	}
	
	public Iterator<SlotDevice> getSelectionListIterator(){
		return selectionList.iterator();
	}
	
	public boolean isElementSelected(SlotDevice element){
		return selectionList.contains(element);
		
	}
	
	
	public void selectElements(Rectangle2D rec,ArrayList<SlotDevice> elements){
		Iterator<SlotDevice> it = elements.iterator();
		while(it.hasNext()){
			SlotDevice element=it.next();
			if (element instanceof SlotDevice) {
				SlotDevice device= (SlotDevice)element;
				if(rec.intersects(device.getPosition().getX(), device.getPosition().getY(),
						device.getSize().getWidth(), device.getSize().getHeight())){
					if(!isElementSelected(element)) {
						selectionList.add(element);
						element.notifySubscribers(new Color(180, 180, 180));
					}
				}
			}
	}

			
	}
	
	public ArrayList<SlotDevice> getSelected() {
		ArrayList<SlotDevice> selected=new ArrayList<SlotDevice>();
		selected.addAll(selectionList);
		
		return selected;
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
		for(int i = observeri.size()-2; i>=0; i-=2) {
			observeri.get(i+1).update(notification);
		}	    	
	}  
}