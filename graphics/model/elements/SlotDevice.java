package graphics.model.elements;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.observer.IObserver;
import model.observer.ISubject;
import model.repository.node.Node;
import model.repository.slot.Slot;
import model.repository.slot.handler.SlotObserverNotifier;


public abstract class SlotDevice extends SlotElement implements ISubject, Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Dimension size;
	protected Point2D position;
	protected String type;
	public Slot slot;
	private transient boolean elementAt;
	private transient List<IObserver> observeri;
	protected double angle = 0;
	private transient double xStart, yStart, oldAngle;
	
	private Object readResolve(){
		observeri = new ArrayList<IObserver>();	
		return this;
	}	
	
	
	public Slot getSlot() {
		return slot;
	}
	
	public boolean isElementAt() {
		return elementAt;
	}
	
	public void setElementAt(boolean elementAt) {
		this.elementAt = elementAt;
	}
	
	public void setSlot(Slot slot) {
		this.slot = slot;
	}
	
	public SlotDevice(String name, Node page, Point2D position, Dimension size){
		super(name, page);
		this.size = size;
		this.position = position;
		if(this instanceof RectangleElement) {
			type = "rectangle";
		} else if(this instanceof CircleElement) {
			type = "circle";
		} else if(this instanceof TriangleElement) {
			type = "triangle";
		}
		
		notifySubscribers("kreiranje");
	}
	
	public void removeElement() {
		notifySubscribers("remove element");
	}
	
	public void scaleElement(double x, double y) {
		notifySubscribers(new SlotObserverNotifier("scale", x, y));
	}
	
	public void moveDevice(double x, double y) {
		notifySubscribers(new SlotObserverNotifier("move", x, y));
	}
	
	public void rotate() {
		notifySubscribers(new SlotObserverNotifier("rotate", 0, 0));
	}
	
	public void addSlot() {
		this.notifySubscribers("add slot element");
	}
	
	public void removeSlot() {
		this.notifySubscribers("remove element");
	}
	
	public double getxStart() {
		return xStart;
	}
	
	public void setxStart(double xStart) {
		this.xStart = xStart;
	}
	
	public void setyStart(double yStart) {
		this.yStart = yStart;
	}
	
	public void setOldAngle(double oldAngle) {
		this.oldAngle = oldAngle;
	}
	
	public double getOldAngle() {
		return oldAngle;
	}
	
	public double getyStart() {
		return yStart;
	}
	
	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D firstPosition) {
		this.position = firstPosition;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public void addSubscriber(IObserver obs) {
		if(obs == null) {
			return;
		}
		System.out.println(this.observeri);
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
		
		if(notification == null || this.observeri == null || this.observeri.isEmpty())
			return;
		
		for (IObserver iObserver : observeri) {
			iObserver.update(notification);
		}
		
	}

	public List<IObserver> getObserveri() {
		return observeri;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	

	
}