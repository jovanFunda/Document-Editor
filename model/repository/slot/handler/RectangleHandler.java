package model.repository.slot.handler;

import java.awt.geom.Point2D;

import graphics.model.elements.SlotDevice;

public class RectangleHandler extends SlotHandler{
	
	SlotDevice element;
	Point2D position;
	
	public RectangleHandler(SlotDevice element, Point2D position) {
		this.element = element;
		this.position = position;
	}
}
