package commands;

import java.io.Serializable;

import graphics.model.elements.SlotDevice;
import model.repository.Page;
import model.repository.slot.handler.SlotObserverNotifier;

public class RotateDeviceCommand implements AbstractCommand, Serializable {

	Page page;
	double angle;
	double firstAngle = -1;
	SlotDevice device;

	
	public RotateDeviceCommand(Page model, SlotDevice device) {
		this.page = model;
		this.device = device;
		this.angle = device.getOldAngle();
	}

	@Override
	public void doCommand() {
		if(firstAngle != -1) {
			device.setAngle(firstAngle);
		}
	}

	@Override
	public void undoCommand() {
		firstAngle = device.getAngle();
		device.setAngle(angle);
		device.rotate();
	}
}