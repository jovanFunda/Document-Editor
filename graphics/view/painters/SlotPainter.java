package graphics.view.painters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import graphics.model.elements.SlotDevice;
import graphics.model.elements.SlotElement;
import gui.swing.view.ElementView;
import model.observer.IObserver;
import model.observer.ISubject;



public class SlotPainter extends ElementPainter implements ISubject {
	
	protected Shape shape;
	protected Color fillColor;
	private  List<IObserver> observeri = new ArrayList<IObserver>();
	protected ElementView elementView;
	
	
	public SlotPainter(){
		fillColor = Color.white;
	}
	
	public void paint(Graphics2D g, SlotElement element){
		
		g.setPaint(Color.RED);
		g.setStroke(getStroke());
		g.draw(getShape());
		g.setPaint(getFillPaint());
		g.fill(getShape());
		
		if (element instanceof SlotDevice){
			g.setPaint(Color.BLACK);
			SlotDevice device=(SlotDevice) element;
			g.drawString(device.getName(), (int)device.getPosition().getX()+10, (int)device.getPosition().getY()+10);
		}
	}
	
	public ElementView getElementView() {
		return elementView;
	}

	public void setElementView(ElementView elementView) {
		this.elementView = elementView;
	}
	
	public boolean elementAt(Point pos){
		return getShape().contains(pos);
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public Paint getFillPaint() {
		return fillColor;
	}
	
	public void setFillPaint(Color color) {
		fillColor = color;
	}
	
	public Stroke getStroke() {
		return (Stroke) new BasicStroke((float)(2), BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL);
	}
	
	public SlotDevice getDevice() {
		return device;
	}
	
	public void setDevice(SlotDevice device) {
		this.device = device;
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
		
		for (IObserver iObserver : observeri) {
			iObserver.update(notification);
		}
		
	}

	public List<IObserver> getObserveri() {
		return observeri;
	}
}
