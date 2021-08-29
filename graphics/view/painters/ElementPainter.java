package graphics.view.painters;

import java.awt.Graphics2D;
import java.awt.Point;

import graphics.model.elements.SlotDevice;
import graphics.model.elements.SlotElement;


public abstract class ElementPainter {
	
	protected SlotDevice device;
	protected int elemNo = 1;
	
	public ElementPainter() {	}

	public abstract void paint(Graphics2D g, SlotElement element);
	
	public abstract boolean elementAt(Point pos);

}
