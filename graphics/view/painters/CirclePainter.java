package graphics.view.painters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import commands.AbstractCommand;
import commands.MoveDeviceCommand;
import commands.ScaleDeviceCommand;
import graphics.model.elements.CircleElement;
import graphics.model.elements.SlotDevice;
import gui.swing.view.MainFrame;
import model.observer.IObserver;
import model.repository.slot.handler.SlotObserverNotifier;

public class CirclePainter extends SlotPainter implements IObserver {

	SlotObserverNotifier obsHelper;
	ArrayList<AbstractCommand> obsHelper2;
	CircleElement or;
	private double points[] = new double[8];
	private String selectedPoint = "none";
	private Dimension firstDimension;
	private Point2D firstPosition;
	
	public CirclePainter(Point pos) {
		
		or = (CircleElement) createDefault(pos, elemNo);
		setDevice(or);

		shape=new GeneralPath();
		
		((GeneralPath)shape).moveTo(or.getPosition().getX()+or.getSize().getWidth()/2,or.getPosition().getY());
		
		((GeneralPath)shape).quadTo(or.getPosition().getX()+or.getSize().getWidth(), or.getPosition().getY(), 
									or.getPosition().getX()+or.getSize().getWidth(), or.getPosition().getY()+or.getSize().getHeight()/2);
		
		((GeneralPath)shape).quadTo(or.getPosition().getX()+or.getSize().getWidth(), or.getPosition().getY()+or.getSize().getHeight(),
									or.getPosition().getX()+or.getSize().getWidth()/2, or.getPosition().getY()+or.getSize().getHeight());
		
		((GeneralPath)shape).quadTo(or.getPosition().getX(), or.getPosition().getY()+or.getSize().getHeight(),
				or.getPosition().getX(), or.getPosition().getY()+or.getSize().getHeight()/2);


		((GeneralPath)shape).quadTo(or.getPosition().getX(), or.getPosition().getY(),
				or.getPosition().getX()+or.getSize().getWidth()/2,or.getPosition().getY());

		or.addSubscriber(this);
		
		points[0] = or.getPosition().getX() + or.getSize().getWidth() / 2;
		points[1] = or.getPosition().getY();
		points[2] = or.getPosition().getX() + or.getSize().getWidth();
		points[3] = or.getPosition().getY() + or.getSize().getHeight() / 2;
		points[4] = or.getPosition().getX()+or.getSize().getWidth()/2;
		points[5] = or.getPosition().getY()+or.getSize().getHeight();
		points[6] = or.getPosition().getX();
		points[7] = or.getPosition().getY()+or.getSize().getHeight()/2;
	}

	@Override
	public void update(Object notification) {
		
		if(notification instanceof String) {
			if(notification.equals("add slot element")) {
				MainFrame.getInstance().getLastSelectedPage().getPageModel().addSlotElements(this.getDevice());
				MainFrame.getInstance().getLastSelectedPageView().repaint();
			} else if(notification.equals("remove element")) {
				MainFrame.getInstance().getLastSelectedPage().getPageModel().removeElement(this.getDevice());
				MainFrame.getInstance().getLastSelectedPageView().repaint();
			} else if(notification.equals("set last slot selected")) {
				MainFrame.getInstance().setLastSelectedSlot(this);
			}
			
			return;
		}
		
		if(notification instanceof Graphics2D) {
			this.paint((Graphics2D)notification, this.getDevice());
		}
		
		if(notification instanceof Point) {
			
			if(this.elementAt((Point)notification)) {
				this.getDevice().setElementAt(true);
			} else {
				this.getDevice().setElementAt(false);
			}
			
		}
		
		if(notification instanceof Color) {
			this.setFillPaint((Color)notification);
		}
		
		if(notification instanceof ArrayList) {
			obsHelper2 = (ArrayList<AbstractCommand>) notification;
			MainFrame.getInstance().getLastSelectedPage().getCommandManager().addCommand(obsHelper2);
			return;
		}
		
		if(notification instanceof SlotObserverNotifier) {

			obsHelper = (SlotObserverNotifier)notification;
			int size = 10;
			
			if(obsHelper.getType().equals("mousePress")) {
				firstDimension = new Dimension();
				firstDimension.setSize(or.getSize().getWidth(), or.getSize().getHeight());
				firstPosition = new Point2D.Double(or.getPosition().getX(), or.getPosition().getY());
				
				if((obsHelper.getX() - points[0] <= size && obsHelper.getX() >= -size) && (obsHelper.getY() - points[1] <= size && obsHelper.getY() - points[1] >= -size)
						&& (obsHelper.getX() - points[0] <= size && obsHelper.getY() - points[1]>= -size) && (obsHelper.getY() - points[1] <=size && obsHelper.getX() - points[0] >= -size)	) {
						selectedPoint = "first point";
				} else if((obsHelper.getX() - points[2] <= size && obsHelper.getX() >= -size) && (obsHelper.getY() - points[3] <= size && obsHelper.getY() - points[3] >= -size)
						&& (obsHelper.getX() - points[2] <= size && obsHelper.getY() - points[3]>= -size) && (obsHelper.getY() - points[3] <= size && obsHelper.getX() - points[2] >= -size)	) {
						selectedPoint = "second point";
				} 
				
			} else if(obsHelper.getType().equals("mouseReleasedMove")) {
				selectedPoint = "none";
				Point2D p = new Point2D.Double(obsHelper.getX(), obsHelper.getY());
				ArrayList<AbstractCommand> absCommand = new ArrayList<AbstractCommand>();
				absCommand.add(new MoveDeviceCommand(MainFrame.getInstance().getLastSelectedPage(), this.getDevice(), p));
				MainFrame.getInstance().getLastSelectedPage().getCommandManager().addCommand(absCommand);
			} else if(obsHelper.getType().equals("mouseReleasedScale")) {
				selectedPoint = "none";
				ArrayList<AbstractCommand> absCommand = new ArrayList<AbstractCommand>();
				absCommand.add(new ScaleDeviceCommand(MainFrame.getInstance().getLastSelectedPage(), this.getDevice(), firstDimension, firstPosition));
				MainFrame.getInstance().getLastSelectedPage().getCommandManager().addCommand(absCommand);
			} else if(obsHelper.getType().equals("mouseReleased")) {
				
				selectedPoint = "none";
				
			} else if(obsHelper.getType().equals("move")) {
				
				shape=new GeneralPath();
				
				((GeneralPath)shape).moveTo(obsHelper.getX() + getDevice().getSize().getWidth()/2, obsHelper.getY());
				
				((GeneralPath)shape).quadTo(obsHelper.getX() + getDevice().getSize().getWidth(), obsHelper.getY(), 
						obsHelper.getX() + getDevice().getSize().getWidth(), obsHelper.getY() + getDevice().getSize().getHeight()/2);
				
				((GeneralPath)shape).quadTo(obsHelper.getX() + getDevice().getSize().getWidth(), obsHelper.getY() + getDevice().getSize().getHeight(),
						obsHelper.getX() + getDevice().getSize().getWidth()/2, obsHelper.getY() + getDevice().getSize().getHeight());
				
				((GeneralPath)shape).quadTo(obsHelper.getX(), obsHelper.getY() + getDevice().getSize().getHeight(),
						obsHelper.getX(), obsHelper.getY() + getDevice().getSize().getHeight()/2);
				
				
				((GeneralPath)shape).quadTo(obsHelper.getX(), obsHelper.getY(),
						obsHelper.getX() + getDevice().getSize().getWidth()/2, obsHelper.getY());
				
				points[0] = obsHelper.getX() + getDevice().getSize().getWidth()/2;
				points[1] = obsHelper.getY();
				points[2] = obsHelper.getX() + getDevice().getSize().getWidth();
				points[3] = obsHelper.getY() + getDevice().getSize().getHeight()/2;
				points[4] = obsHelper.getX() + getDevice().getSize().getWidth()/2;
				points[5] = obsHelper.getY() + getDevice().getSize().getHeight();
				points[6] = obsHelper.getX();
				points[7] = obsHelper.getY() + getDevice().getSize().getHeight()/2;
				
				
			} else if(obsHelper.getType().equals("scale")) {
				
				if(selectedPoint.equals("first point")) {
					
					shape=new GeneralPath();
					
					((GeneralPath)shape).moveTo(points[0], points[1]); 
					
					((GeneralPath)shape).quadTo(points[0] + getDevice().getSize().getWidth()/2, obsHelper.getY(), 
					points[2], points[3]); 
					
					((GeneralPath)shape).quadTo(points[0] + getDevice().getSize().getWidth()/2, obsHelper.getY() + getDevice().getSize().getHeight(),
					points[4], points[5]);  
					
					((GeneralPath)shape).quadTo(points[0] - getDevice().getSize().getWidth()/2, obsHelper.getY() + getDevice().getSize().getHeight(),
					points[6], points[7]); 
					
					((GeneralPath)shape).quadTo(points[0] - getDevice().getSize().getWidth()/2, obsHelper.getY(),
					points[0], points[1]);
					
					Dimension d = new Dimension();
		            d.setSize(getDevice().getSize().getWidth(), points[5] - obsHelper.getY());
		            getDevice().setSize(d);
		            getDevice().setPosition(new Point(shape.getBounds().x, shape.getBounds().y));
					
					points[1] = obsHelper.getY();
					points[3] = (points[5] + obsHelper.getY()) / 2;
					points[7] = (obsHelper.getY() + points[5]) / 2;
				} else if(selectedPoint.equals("second point")) {
					
					shape=new GeneralPath();
					
					((GeneralPath)shape).moveTo(points[0], points[1]); 
					
					((GeneralPath)shape).quadTo(obsHelper.getX(), points[1], 
					points[2], points[3]); 
					
					((GeneralPath)shape).quadTo(obsHelper.getX(), points[1] + device.getSize().getHeight(),
					points[4], points[5]);  
					
					((GeneralPath)shape).quadTo(obsHelper.getX() - device.getSize().getWidth(), points[1] + device.getSize().getHeight(),
					points[6], points[7]); 
					
					((GeneralPath)shape).quadTo(obsHelper.getX() - device.getSize().getWidth(), points[1],
					points[0], points[1]);
					
					Dimension d = new Dimension();
		            d.setSize(obsHelper.getX() - points[6], device.getSize().getHeight());
		            getDevice().setSize(d);
		            getDevice().setPosition(new Point(shape.getBounds().x, shape.getBounds().y));
					
					points[0] = obsHelper.getX() - device.getSize().getWidth()/2;
					points[2] = obsHelper.getX();
					points[4] = obsHelper.getX() - device.getSize().getWidth()/2;
				}
			}
			
			MainFrame.getInstance().getLastSelectedPageView().repaint();
		}
	}
	
	public static SlotDevice createDefault(Point2D pos, int elemNo){
		Point position = (Point) pos.clone();
        
	    CircleElement or = new CircleElement("", 
	    									MainFrame.getInstance().getLastSelectedPage(), 
	    									position, 
	    									new Dimension(50,50));
	    
		return or;
	}

}
