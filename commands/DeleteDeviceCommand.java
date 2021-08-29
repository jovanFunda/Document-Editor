package commands;

import java.io.Serializable;

import graphics.model.elements.SlotDevice;
import model.repository.Page;

public class DeleteDeviceCommand implements AbstractCommand, Serializable {

	Page page;
	SlotDevice device;

	public DeleteDeviceCommand(Page model, SlotDevice device) {
		this.page = model;
		this.device = device;
	}

	@Override
	public void doCommand() {
		device.removeElement();
	}

	@Override
	public void undoCommand() {
		device.addSlot();
	}
}