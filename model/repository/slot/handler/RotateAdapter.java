package model.repository.slot.handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import commands.AbstractCommand;
import commands.RotateDeviceCommand;
import graphics.model.elements.SlotDevice;
import graphics.view.painters.SlotPainter;
import gui.swing.view.MainFrame;

public class RotateAdapter extends MouseAdapter{
	
	private double angle;
	ArrayList<AbstractCommand> absCommand;
	
	public void mousePressed(MouseEvent e) {
		
		absCommand = new ArrayList<AbstractCommand>();
		
		for (SlotDevice painter : MainFrame.getInstance().getPageSelectionModel().getSelectionList()) {
			painter.setOldAngle(painter.getAngle());
		}
		
		MainFrame.getInstance().getLastSelectedPageView().getPage().getStateManager().setNullState();
	}
	
	public void mouseDragged(MouseEvent e) {
		calculateAngle(e.getX(), e.getY());
		MainFrame.getInstance().getLastSelectedPageView().repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
		MainFrame.getInstance().getLastSelectedPageView().removeMouseMotionListener(this);
		MainFrame.getInstance().getLastSelectedPageView().removeMouseListener(this);
		MainFrame.getInstance().getLastSelectedPageView().getPage().getStateManager().setSelectState();
	    MainFrame.getInstance().getPalette().getSelect().clearSelection();
	    
	    for (SlotDevice sp : MainFrame.getInstance().getPageSelectionModel().getSelectionList()) {
			absCommand.add(new RotateDeviceCommand(MainFrame.getInstance().getLastSelectedPage(), sp));
		}
	    
	    MainFrame.getInstance().getLastSelectedPage().getCommandManager().addCommand(absCommand);
	    
	}
	 
	public void calculateAngle(int x, int y) {
		
		if(MainFrame.getInstance().getLastSelectedSlot() != null) {
			if(MainFrame.getInstance().getLastSelectedSlot().getDevice() != null) {
				SlotDevice d = MainFrame.getInstance().getLastSelectedSlot().getDevice();
				double deltaX =  x - (d.getPosition().getX() + d.getSize().getWidth() / 2);
				double deltaY = y - (d.getPosition().getY() + d.getSize().getHeight() / 2);
			    angle = Math.toDegrees(Math.atan2(deltaY, deltaX));
			    d.setAngle(angle);
		    }
		}
		
		for (SlotDevice painter : MainFrame.getInstance().getPageSelectionModel().getSelectionList()) {
		    
			double deltaX =  x - (painter.getPosition().getX() + painter.getSize().getWidth() / 2);
			double deltaY = y - (painter.getPosition().getY() + painter.getSize().getHeight() / 2);
		    angle = Math.toDegrees(Math.atan2(deltaY, deltaX));
		    painter.setAngle(angle);
		}
		
		MainFrame.getInstance().getLastSelectedPageView().repaint();
	}
	
	@Override
	public String toString() {
		return "rotate";
	}
}