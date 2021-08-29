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
import gui.swing.view.MainFrame;
import model.observer.IObserver;
import model.repository.slot.handler.SlotObserverNotifier;
import graphics.model.elements.RectangleElement;
import graphics.model.elements.SlotDevice;



public class RectanglePainter extends SlotPainter implements IObserver {

	SlotObserverNotifier obsHelper;
	ArrayList<AbstractCommand> obsHelper2;
	RectangleElement rectangle;
	private double points[] = new double[8];
	private String selectedPoint = "none";
	private Dimension firstDimension;
	private Point2D firstPosition;
	
	public RectanglePainter(Point pos) {
		createPainter(pos);	
	}
	
	@Override
	public void update(Object notification) {
		
		if(notification instanceof String) {
			if(notification.equals("kreiranje")) {
				createPainter(getDevice().getPosition());
			} else if(notification.equals("add slot element")) {
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
		
			if(obsHelper.getType().equals("mousePress")) {
				firstDimension = new Dimension();
				firstDimension.setSize(rectangle.getSize().getWidth(), rectangle.getSize().getHeight());
				firstPosition = new Point2D.Double(rectangle.getPosition().getX(), rectangle.getPosition().getY());
				if((obsHelper.getX() - points[0] <= 5 && obsHelper.getX() >= -5) && (obsHelper.getY() - points[1] <= 5 && obsHelper.getY() - points[1] >= -5)
						&& (obsHelper.getX() - points[0] <= 5 && obsHelper.getY() - points[1]>= -5) && (obsHelper.getY() - points[1] <=5 && obsHelper.getX() - points[0] >= -5)	) {
						selectedPoint = "first point";
				} else if((obsHelper.getX() - points[2] <= 5 && obsHelper.getX() >= -5) && (obsHelper.getY() - points[3] <= 5 && obsHelper.getY() - points[3] >= -5)
						&& (obsHelper.getX() - points[2] <= 5 && obsHelper.getY() - points[3]>= -5) && (obsHelper.getY() - points[3] <=5 && obsHelper.getX() - points[2] >= -5)	) {
						selectedPoint = "second point";
				} else if((obsHelper.getX() - points[4] <= 5 && obsHelper.getX() >= -5) && (obsHelper.getY() - points[5] <= 5 && obsHelper.getY() - points[5] >= -5)
						&& (obsHelper.getX() - points[4] <= 5 && obsHelper.getY() - points[5]>= -5) && (obsHelper.getY() - points[5] <=5 && obsHelper.getX() - points[4] >= -5)	) {
						selectedPoint = "third point";
				} else if((obsHelper.getX() - points[6] <= 5 && obsHelper.getX() >= -5) && (obsHelper.getY() - points[7] <= 5 && obsHelper.getY() - points[7] >= -5)
						&& (obsHelper.getX() - points[6] <= 5 && obsHelper.getY() - points[7]>= -5) && (obsHelper.getY() - points[7] <=5 && obsHelper.getX() - points[6] >= -5)	) {
						selectedPoint = "fourth point";
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
			}
			
			
			if(obsHelper.getType().equals("move")) {
				
				shape=new GeneralPath();
				((GeneralPath)shape).moveTo(device.getPosition().getX(), device.getPosition().getY());
				
				((GeneralPath)shape).lineTo(device.getPosition().getX()+ device.getSize().width, device.getPosition().getY());
				
				((GeneralPath)shape).lineTo(device.getPosition().getX() + device.getSize().width, device.getPosition().getY()+device.getSize().height);
				
				((GeneralPath)shape).lineTo(device.getPosition().getX(), device.getPosition().getY() + device.getSize().height);
				
				((GeneralPath)shape).closePath();
				
				Dimension d = new Dimension();
	            d.setSize(shape.getBounds().getWidth(), shape.getBounds().getHeight());
	            this.getDevice().setSize(d);
	            this.getDevice().setPosition(new Point(shape.getBounds().x, shape.getBounds().y));
	            
	            points[0] = device.getPosition().getX();
	            points[1] = device.getPosition().getY();
	            points[2] = device.getPosition().getX() + device.getSize().getWidth();
	            points[3] = device.getPosition().getY();
	            points[4] = device.getPosition().getX() + device.getSize().getWidth();
	            points[5] = device.getPosition().getY() + device.getSize().getHeight();
	            points[6] = device.getPosition().getX();
	            points[7] = device.getPosition().getY() + device.getSize().getHeight();
	            
			} else if(obsHelper.getType().equals("scale")) {
				
				if(selectedPoint.equals("first point")) {
					shape=new GeneralPath();
					((GeneralPath)shape).moveTo(obsHelper.getX(), obsHelper.getY());
					
					((GeneralPath)shape).lineTo(points[2], obsHelper.getY());
					
					((GeneralPath)shape).lineTo(points[4], points[5]); 
					
					((GeneralPath)shape).lineTo(obsHelper.getX(), points[7]);
					
					((GeneralPath)shape).closePath();
					
					Dimension d = new Dimension();
		            d.setSize(shape.getBounds().getWidth(), shape.getBounds().getHeight());
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setSize(d);
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setPosition(new Point(shape.getBounds().x, shape.getBounds().y));
		            
		            points[0] = obsHelper.getX();
		            points[1] = obsHelper.getY();
		            points[3] = obsHelper.getY();
		            points[6] = obsHelper.getX();
		            
				} else if(selectedPoint.equals("second point")) {
					shape=new GeneralPath();
					((GeneralPath)shape).moveTo(points[0], obsHelper.getY());
					
					((GeneralPath)shape).lineTo(obsHelper.getX(), obsHelper.getY());
					
					((GeneralPath)shape).lineTo(obsHelper.getX(), points[5]); 
					
					((GeneralPath)shape).lineTo(points[6], points[7]);
					
					((GeneralPath)shape).closePath();
					
					Dimension d = new Dimension();
		            d.setSize(shape.getBounds().getWidth(), shape.getBounds().getHeight());
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setSize(d);
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setPosition(new Point(shape.getBounds().x, shape.getBounds().y));
		            
		            points[1] = obsHelper.getY();
		            points[2] = obsHelper.getX();
		            points[3] = obsHelper.getY();
		            points[4] = obsHelper.getX();
				} else if(selectedPoint.equals("third point")) {
					shape=new GeneralPath();
					((GeneralPath)shape).moveTo(points[0], points[1]);
					
					((GeneralPath)shape).lineTo(obsHelper.getX(), points[3]);
					
					((GeneralPath)shape).lineTo(obsHelper.getX(), obsHelper.getY()); 
					
					((GeneralPath)shape).lineTo(points[6], obsHelper.getY());
					
					((GeneralPath)shape).closePath();
					
					Dimension d = new Dimension();
		            d.setSize(shape.getBounds().getWidth(), shape.getBounds().getHeight());
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setSize(d);
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setPosition(new Point(shape.getBounds().x, shape.getBounds().y));
		            
		            points[2] = obsHelper.getX();
		            points[4] = obsHelper.getX();
		            points[5] = obsHelper.getY();
		            points[7] = obsHelper.getY();
				} else if(selectedPoint.equals("fourth point")) {
					
					shape=new GeneralPath();
					((GeneralPath)shape).moveTo(obsHelper.getX(), points[1]);
					
					((GeneralPath)shape).lineTo(points[2], points[3]);
					
					((GeneralPath)shape).lineTo(points[4], obsHelper.getY()); 
					
					((GeneralPath)shape).lineTo(obsHelper.getX(), obsHelper.getY());
					
					((GeneralPath)shape).closePath();
					
					Dimension d = new Dimension();
		            d.setSize(shape.getBounds().getWidth(), shape.getBounds().getHeight());
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setSize(d);
		            MainFrame.getInstance().getLastSelectedSlot().getDevice().setPosition(new Point(shape.getBounds().x, shape.getBounds().y));
		            
		            points[0] = obsHelper.getX();
		            points[5] = obsHelper.getY();
		            points[6] = obsHelper.getX();
		            points[7] = obsHelper.getY();
				}
			}
			
			MainFrame.getInstance().getLastSelectedPageView().repaint();
		}
	}
	
	public static SlotDevice createDefault(Point2D lastPosition, int elemNo){
		Point position = (Point) lastPosition.clone();
		
        RectangleElement rectangleElement = 
        		new RectangleElement("", MainFrame.getInstance().getLastSelectedPage(), 
        		position, 
        		new Dimension(80,40));
        
		return rectangleElement;
	}
	
	public String getSelectedPoint() {
		return selectedPoint;
	}

	public RectangleElement getRectangle() {
		return rectangle;
	}
	
	private void createPainter(Point2D pos) {
		rectangle = (RectangleElement) createDefault(pos, elemNo);
		setDevice(rectangle);
		rectangle.addSubscriber(this);

		shape=new GeneralPath();
		((GeneralPath)shape).moveTo(rectangle.getPosition().getX(),rectangle.getPosition().getY());
		
		((GeneralPath)shape).lineTo(rectangle.getPosition().getX()+rectangle.getSize().width,rectangle.getPosition().getY());
		
		((GeneralPath)shape).lineTo(rectangle.getPosition().getX()+rectangle.getSize().width,rectangle.getPosition().getY()+rectangle.getSize().height);
		
		((GeneralPath)shape).lineTo(rectangle.getPosition().getX(),rectangle.getPosition().getY()+rectangle.getSize().height);
		
		((GeneralPath)shape).closePath();
		
		
		points[0] = rectangle.getPosition().getX();
		points[1] = rectangle.getPosition().getY();
		points[2] = rectangle.getPosition().getX() + rectangle.getSize().getWidth();
		points[3] = rectangle.getPosition().getY();
		points[4] = rectangle.getPosition().getX() + rectangle.getSize().getWidth();
		points[5] = rectangle.getPosition().getY() + rectangle.getSize().getHeight();
		points[6] = rectangle.getPosition().getX();
		points[7] = rectangle.getPosition().getY() + rectangle.getSize().getHeight();
	}
	
}
