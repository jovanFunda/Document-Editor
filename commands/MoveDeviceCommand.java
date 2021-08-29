package commands;

import java.awt.geom.Point2D;
import java.io.Serializable;

import graphics.model.elements.SlotDevice;
import gui.swing.view.MainFrame;
import model.repository.Page;

public class MoveDeviceCommand implements AbstractCommand, Serializable {

	Page page;
	Point2D lastPosition;
	Point2D firstPosition;
	SlotDevice device;

	
	public MoveDeviceCommand(Page model, SlotDevice device, Point2D lastPosition) {
		this.page = model;
		this.lastPosition = lastPosition;
		this.device = device;
	}

	@Override
	public void doCommand() {
		if(firstPosition != null) {
			device.setPosition(firstPosition);
			device.moveDevice(firstPosition.getX(), firstPosition.getY());
		}
	}

	@Override
	public void undoCommand() {
		firstPosition = device.getPosition();
		device.setPosition(lastPosition);
		device.moveDevice(lastPosition.getX(), lastPosition.getY());
		MainFrame.getInstance().getLastSelectedPageView().repaint();
	}
}