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
import graphics.model.elements.SlotDevice;
import graphics.model.elements.TriangleElement;
import gui.swing.view.MainFrame;
import main.Core;
import model.observer.IObserver;
import model.repository.slot.handler.SlotObserverNotifier;

public class TrianglePainter extends SlotPainter implements IObserver {

	TriangleElement triangle;
	SlotObserverNotifier obsHelper;
	ArrayList<AbstractCommand> obsHelper2;
	private double points[] = new double[6];
	private String selectedPoint = "none";
	private Dimension firstDimension;
	private Point2D firstPosition;
	
	public TrianglePainter(Point pos) {
		triangle = (TriangleElement) createDefault(pos, elemNo);
		setDevice(triangle);

		shape=new GeneralPath();
		
		((GeneralPath)shape).moveTo(triangle.getPosition().getX() + triangle.getSize().width / 2, triangle.getPosition().getY());
		
		((GeneralPath)shape).lineTo(triangle.getPosition().getX()+triangle.getSize().width,triangle.getPosition().getY()+triangle.getSize().height);
		
		((GeneralPath)shape).lineTo(triangle.getPosition().getX(),triangle.getPosition().getY()+triangle.getSize().height);
		
		((GeneralPath)shape).closePath();
		
		triangle.addSubscriber(this);
		
		points[0] = triangle.getPosition().getX() + triangle.getSize().getWidth() / 2;
		points[1] = triangle.getPosition().getY();
		points[2] = triangle.getPosition().getX() + triangle.getSize().getWidth();
		points[3] = triangle.getPosition().getY() + triangle.getSize().getHeight();
		points[4] = triangle.getPosition().getX();
		points[5] = triangle.getPosition().getY() + triangle.getSize().getHeight();
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
			
			return;
		}
		
		if(notification instanceof Color) {
			this.setFillPaint((Color)notification);
			return;
		}
		
		if(notification instanceof ArrayList) {
			obsHelper2 = (ArrayList<AbstractCommand>) notification;
			MainFrame.getInstance().getLastSelectedPage().getCommandManager().addCommand(obsHelper2);
			return;
		}
		
		if(notification instanceof SlotObserverNotifier) {
			obsHelper = (SlotObserverNotifier)notification;
			
			if(obsHelper.getType().equals("mousePress")) {
				firstDimension = new Dimension();
				firstDimension.setSize(triangle.getSize().getWidth(), triangle.getSize().getHeight());
				firstPosition = new Point2D.Double(triangle.getPosition().getX(), triangle.getPosition().getY());
				
				if((obsHelper.getX() - points[0] <= 5 && obsHelper.getX() >= -5) && (obsHelper.getY() - points[1] <= 5 && obsHelper.getY() - points[1] >= -5)
						&& (obsHelper.getX() - points[0] <= 5 && obsHelper.getY() - points[1]>= -5) && (obsHelper.getY() - points[1] <=5 && obsHelper.getX() - points[0] >= -5)	) {
						selectedPoint = "first point";
				} else if((obsHelper.getX() - points[2] <= 5 && obsHelper.getX() >= -5) && (obsHelper.getY() - points[3] <= 5 && obsHelper.getY() - points[3] >= -5)
						&& (obsHelper.getX() - points[2] <= 5 && obsHelper.getY() - points[3]>= -5) && (obsHelper.getY() - points[3] <=5 && obsHelper.getX() - points[2] >= -5)	) {
						selectedPoint = "second point";
				} else if((obsHelper.getX() - points[4] <= 5 && obsHelper.getX() >= -5) && (obsHelper.getY() - points[5] <= 5 && obsHelper.getY() - points[5] >= -5)
						&& (obsHelper.getX() - points[4] <= 5 && obsHelper.getY() - points[5]>= -5) && (obsHelper.getY() - points[5] <=5 && obsHelper.getX() - points[4] >= -5)	) {
						selectedPoint = "third point";
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
			} else if(obsHelper.getType().equals("move")) {
				
				shape = new GeneralPath();
				
				((GeneralPath)shape).moveTo(obsHelper.getX() + getDevice().getSize().getWidth()/2 , getDevice().getPosition().getY());
				
				((GeneralPath)shape).lineTo(getDevice().getPosition().getX()+getDevice().getSize().width,getDevice().getPosition().getY()+getDevice().getSize().height);
				
				((GeneralPath)shape).lineTo(getDevice().getPosition().getX(),getDevice().getPosition().getY()+getDevice().getSize().height);
				
				((GeneralPath)shape).closePath();
				
				Dimension d = new Dimension();
	            d.setSize(shape.getBounds().getWidth(), shape.getBounds().getHeight());
	            device.setSize(d);
	            device.setPosition(new Point(shape.getBounds().x, shape.getBounds().y));
	            
	            points[0] = obsHelper.getX() + getDevice().getSize().getWidth()/2;
	            points[1] = getDevice().getPosition().getY();
	            points[2] = getDevice().getPosition().getX() + getDevice().getSize().getWidth();
	            points[3] = getDevice().getPosition().getY() + getDevice().getSize().getHeight();
	            points[4] = getDevice().getPosition().getX();
	            points[5] = getDevice().getPosition().getY() + getDevice().getSize().getHeight();
	            
			} else if(obsHelper.getType().equals("scale")) {
				
				if(selectedPoint.equals("first point")) {
					
					shape = new GeneralPath();
					
					((GeneralPath)shape).moveTo(points[0], points[1]);
					((GeneralPath)shape).lineTo(points[2], points[3]);
					((GeneralPath)shape).lineTo(points[4], points[5]);
					((GeneralPath)shape).closePath();
					
					Dimension d = new Dimension();
		            d.setSize(shape.getBounds().getWidth(), shape.getBounds().getHeight());
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setSize(d);
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setPosition(new Point(shape.getBounds().x, shape.getBounds().y));
		            
		            points[1] = obsHelper.getY();
					
				} else if(selectedPoint.equals("second point")) {
					
					shape=new GeneralPath();
					
					((GeneralPath)shape).moveTo(points[0], points[1]);	
					((GeneralPath)shape).lineTo(points[2], points[3]);
					((GeneralPath)shape).lineTo(points[4], points[5]);	
					((GeneralPath)shape).closePath();
					
					Dimension d = new Dimension();
		            d.setSize(shape.getBounds().getWidth(), shape.getBounds().getHeight());
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setSize(d);
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setPosition(new Point(shape.getBounds().x, shape.getBounds().y));
		            
		            points[0] = obsHelper.getX() - getDevice().getSize().getWidth()/2;
		            points[2] = obsHelper.getX();
					
				} else if(selectedPoint.equals("third point")) {
					
					shape=new GeneralPath();
					
					((GeneralPath)shape).moveTo(points[0], points[1]);
					((GeneralPath)shape).lineTo(points[2], points[3]);
					((GeneralPath)shape).lineTo(points[4], points[5]);
					((GeneralPath)shape).closePath();
					
					Dimension d = new Dimension();
		            d.setSize(shape.getBounds().getWidth(), shape.getBounds().getHeight());
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setSize(d);
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setPosition(new Point(shape.getBounds().x, shape.getBounds().y));
		            
		            points[0] = obsHelper.getX() + getDevice().getSize().getWidth()/2;
		            points[4] = obsHelper.getX();
		            
				}
				MainFrame.getInstance().getLastSelectedPageView().repaint();
				}
		}
	}
	
	public static SlotDevice createDefault(Point2D point2d, int elemNo){
		Point position = (Point) point2d.clone();
		
        TriangleElement triangleElement= new TriangleElement("", 
												        	 MainFrame.getInstance().getLastSelectedPage(), 
												        	 position, 
	    		                       					     new Dimension(80, 40));
        
		return triangleElement;
	}

}
