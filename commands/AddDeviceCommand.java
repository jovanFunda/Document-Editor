package commands;

import java.awt.geom.Point2D;
import java.io.Serializable;

import graphics.model.elements.SlotDevice;
import model.repository.Page;


public class AddDeviceCommand implements AbstractCommand, Serializable {

	private static final long serialVersionUID = 1L;
	
	Page page;
	Point2D lastPosition;
	SlotDevice device;

	public AddDeviceCommand(Page model, SlotDevice device, Point2D lastPosition) {
		this.page = model;
		this.lastPosition = lastPosition;
		this.device = device;
	}

	@Override
	public void doCommand() {
		device.addSlot();
	}

	@Override
	public void undoCommand() {
		device.removeSlot();
	}
}