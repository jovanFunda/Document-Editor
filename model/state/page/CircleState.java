 package model.state.page;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import commands.AbstractCommand;
import commands.AddDeviceCommand;
import graphics.view.painters.CirclePainter;
import gui.swing.view.MainFrame;
import model.repository.Page;
import model.repository.slot.Slot;
import model.repository.slot.factory.AbstractSlotFactory;
import model.repository.slot.factory.CircleConcreteFactory;
import model.repository.slot.factory.RectangleConcreteFactory;
import model.repository.slot.factory.TriangleConcreteFactory;

public class CircleState extends State{

	int cntCircle = 1;
	
	private Page page; 
	public CircleState(Page page) {
	    this.page = page;
	}
	
	public void mousePressed(MouseEvent e) {
		
		Point position = e.getPoint();
		
		if (e.getButton()==MouseEvent.BUTTON1){
			 if (page.getPageModel().getDeviceAtPosition(position)==-1){
				 
				if(MainFrame.getInstance().getLastSelectedPageView().getPage().getPageModel().getDeviceAtPosition(e.getPoint()) != -1) {
					return;
				}
				
				AbstractSlotFactory abFactory = factoryCheck(((Page) MainFrame.getInstance().getLastSelectedPageView().getPage()).getStateManager().getCurrentState().toString());
				
				abFactory.chooseSlot(e, MainFrame.getInstance().getLastSelectedPageView().getPage(), cntCircle);
			
				MainFrame.getInstance().getTree().setSelectedNode(MainFrame.getInstance().getLastSelectedPageView().getPage().getPageTree());	

				 
				 CirclePainter painter = new CirclePainter(position);
				 
				 painter.getDevice().setName("Circle " + cntCircle);
				 
				 Slot slot = new Slot(painter.getDevice().getName(), page);
				 painter.getDevice().setSlot(slot);
				 
				 MainFrame.getInstance().getTree().addSlot(slot, page);
				 
				 cntCircle++;
				 
				 ArrayList<AbstractCommand> absCommand = new ArrayList<AbstractCommand>();
				 absCommand.add(new AddDeviceCommand(page, painter.getDevice(), (Point2D) e.getPoint()));
				 MainFrame.getInstance().getLastSelectedPage().getCommandManager().addCommand(absCommand);
			 }
		}
	}
	
	private AbstractSlotFactory factoryCheck(String type) {
		
		if(type.equals("rectangle")) {
			return new RectangleConcreteFactory();
		} else if(type.equals("circle")) {
			return new CircleConcreteFactory();
		} else if(type.equals("triangle")) {
			return new TriangleConcreteFactory();
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return "circle";
	}
}
