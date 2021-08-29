package commands;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.Serializable;

import graphics.model.elements.SlotDevice;
import model.repository.Page;

public class ScaleDeviceCommand implements AbstractCommand, Serializable {

	Page page;
	Point2D lastPosition;
	Point2D firstPosition;
	Dimension size;
	Dimension firstSize;
	SlotDevice device;

	
	public ScaleDeviceCommand(Page model, SlotDevice device, Dimension size, Point2D lastPosition) {
		this.page = model;
		this.size = size;
		this.device = device;
		this.lastPosition = lastPosition;
	}

	@Override
	public void doCommand() {
		if(firstSize != null && firstPosition != null) {
			device.setPosition(firstPosition);
			device.setSize(firstSize);
			device.moveDevice(firstPosition.getX(), firstPosition.getY());
			device.scaleElement(firstSize.getWidth(), firstSize.getHeight());
		}
	}

	@Override
	public void undoCommand() {
		firstPosition = device.getPosition();
		firstSize = device.getSize();
		device.setPosition(lastPosition);
		device.setSize(size);
		device.moveDevice(lastPosition.getX(), lastPosition.getY());
		device.scaleElement(size.getWidth(), size.getHeight());
	}
}
