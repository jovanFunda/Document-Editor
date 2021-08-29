package model.repository.slot.handler;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import commands.AbstractCommand;
import commands.MoveDeviceCommand;
import graphics.model.elements.SlotDevice;
import graphics.view.painters.SlotPainter;
import gui.swing.view.MainFrame;

	
public	class MoveAdapter extends MouseAdapter {

	    private double x, y;
	    ArrayList<AbstractCommand> absCommand;
	    
	    public void mousePressed(MouseEvent e) {
	    	
	    	absCommand = new ArrayList<AbstractCommand>();
	    	
	    	for (SlotDevice painter : MainFrame.getInstance().getPageSelectionModel().getSelectionList()) {
				painter.setxStart(painter.getPosition().getX());
				painter.setyStart(painter.getPosition().getY());
				painter.notifySubscribers(new SlotObserverNotifier("mousePress", x, y));
			}
	    	
	    	x = e.getX();
	    	y = e.getY();
	    	
	    	MainFrame.getInstance().getLastSelectedPageView().getPage().getStateManager().setNullState();
	    }

	    public void mouseDragged(MouseEvent e) {
	    	
	      double dx = e.getX() - x;
	      double dy = e.getY() - y;

	      String type = "move";
	      
	      for (SlotDevice painter : MainFrame.getInstance().getPageSelectionModel().getSelectionList()) {
	    	  
	    	  double newX = (painter.getPosition().getX() + dx);
	    	  double newY = (painter.getPosition().getY() + dy);
	    	  
	    	  Point newPosition = new Point();
	    	  newPosition.setLocation(newX, newY);
	    	  
	    	  painter.setPosition(newPosition);
	    	  painter.notifySubscribers(Color.WHITE);
	    	  
	    	  painter.notifySubscribers(new SlotObserverNotifier(type, newX, newY));
	    	  
	    	  MainFrame.getInstance().getPalette().getSelectButton().setSelected(false);
	    	  MainFrame.getInstance().getLastSelectedPageView().repaint();
		      
	      }
	      
	      
	      x += dx;
	      y += dy;
	    
	    }
	    
	    @Override
	    public void mouseReleased(MouseEvent e) {
	    	
	    	MainFrame.getInstance().getLastSelectedPageView().removeMouseMotionListener(this);
		    MainFrame.getInstance().getLastSelectedPageView().removeMouseListener(this);
		    MainFrame.getInstance().getLastSelectedPageView().getPage().getStateManager().setSelectState();
		    MainFrame.getInstance().getPalette().getSelect().clearSelection();
		    
		    for (SlotDevice painter : MainFrame.getInstance().getPageSelectionModel().getSelectionList()) {
		    	Point2D points = new Point2D.Double(painter.getxStart(), painter.getyStart());
		    	absCommand.add(new MoveDeviceCommand(MainFrame.getInstance().getLastSelectedPage(), painter, points));
		    	painter.notifySubscribers("set last slot selected");
			}
		    
		    MainFrame.getInstance().getLastSelectedSlot().getDevice().notifySubscribers(absCommand);
	    }
	    
	    @Override
	    public String toString() {
	    	return "move";
	    }
}