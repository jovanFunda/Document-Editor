package model.repository.slot.handler;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import graphics.view.painters.CirclePainter;
import graphics.view.painters.SlotPainter;
import graphics.view.painters.RectanglePainter;
import graphics.view.painters.TrianglePainter;
import gui.swing.view.MainFrame;

class ScaleAdapter extends MouseAdapter {

    private int x, y;
    private SlotPainter painter = MainFrame.getInstance().getLastSelectedSlot();

    public void mousePressed(MouseEvent e) {
    	x = e.getX();
    	y = e.getY();
    	
    	painter = MainFrame.getInstance().getLastSelectedSlot();
    	
    	if(painter instanceof RectanglePainter) {
    		painter = (RectanglePainter)MainFrame.getInstance().getLastSelectedSlot();
	      } else if(painter instanceof CirclePainter) {
	    	  painter = (CirclePainter)MainFrame.getInstance().getLastSelectedSlot();
	      } else if(painter instanceof TrianglePainter) {
	    	  painter = (TrianglePainter)MainFrame.getInstance().getLastSelectedSlot();
	      }
    	
    	MainFrame.getInstance().getLastSelectedPageView().getPage().getStateManager().setNullState();
    	
    	painter.getDevice().notifySubscribers(new SlotObserverNotifier("mousePress", x, y));
    }

    public void mouseDragged(MouseEvent e) {
    	
	      int dx = e.getX() - x;
	      int dy = e.getY() - y;
	      
	      painter = MainFrame.getInstance().getLastSelectedSlot();
	      
	      if(painter instanceof RectanglePainter) {
	    	   painter = (RectanglePainter)MainFrame.getInstance().getLastSelectedSlot();
		  } else if(painter instanceof CirclePainter) {
			   painter = (CirclePainter)MainFrame.getInstance().getLastSelectedSlot();
		  } else if(painter instanceof TrianglePainter) {
			   painter = (TrianglePainter)MainFrame.getInstance().getLastSelectedSlot();
		  }
	      
	      painter.getDevice().notifySubscribers(new SlotObserverNotifier("scale", x, y));
	      painter.setFillPaint(Color.WHITE);
	      MainFrame.getInstance().getLastSelectedPageView().repaint();
	      
	      x += dx;
	      y += dy;	    
	      
	    }
	    
	    @Override
	    public void mouseReleased(MouseEvent e) {
	    	MainFrame.getInstance().getLastSelectedPageView().removeMouseMotionListener(this);
		    MainFrame.getInstance().getLastSelectedPageView().removeMouseListener(this);
		    MainFrame.getInstance().getLastSelectedPageView().getPage().getStateManager().setSelectState();
		    MainFrame.getInstance().getPalette().getSelect().clearSelection();
		    painter.getDevice().notifySubscribers(new SlotObserverNotifier("mouseReleasedScale", x, y));
	    }
	    
	    @Override
	    public String toString() {
	    	return "scale";
	    }
  }