package model.state.page;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import graphics.model.elements.SlotDevice;
import graphics.view.painters.SlotPainter;
import gui.swing.view.MainFrame;
import model.repository.Page;

public class SelectState extends State {

	private Page page; 
	private List<Integer> mousePositions = new ArrayList<Integer>();
	private Integer x, y, dx, dy;
	private boolean selectedItem = false;
	
	public SelectState(Page page) {
		this.page = page;
	}
	
	
	public void mousePressed(MouseEvent e) {
		
		if (e.getButton()==MouseEvent.BUTTON1){
			
			x = e.getX();
			y = e.getY();
			
			deselectAll();
			
			Point p = e.getPoint();
			
			int pos = MainFrame.getInstance().getLastSelectedPageView().getPage().getPageModel().getDeviceAtPosition(p);
			
			if(pos != -1) {
				
				if(MainFrame.getInstance().getLastSelectedSlot() != null) {
					MainFrame.getInstance().getLastSelectedSlot().setFillPaint(Color.WHITE);
				}
				
				selectedItem = true;
				
				MainFrame.getInstance().getLastSelectedPageView().getPage().getPageModel().getDeviceAt(pos).notifySubscribers("set last slot selected");
				MainFrame.getInstance().getLastSelectedSlot().setFillPaint(new Color(180, 180, 180));
				MainFrame.getInstance().getPageSelectionModel().getSelectionList().add(MainFrame.getInstance().getLastSelectedSlot().getDevice());
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		
		if(selectedItem == true) {
			return;
		}
		
		dx = e.getX() - x;
		dy = e.getY() - y;
		
		mousePositions.add((Integer)x);
		mousePositions.add((Integer)y);
		mousePositions.add((Integer)dx);
		mousePositions.add((Integer)dy);
		
		MainFrame.getInstance().getLastSelectedPageView().update(mousePositions);

		mousePositions.remove((Integer)x);
		mousePositions.remove((Integer)y);
		mousePositions.remove((Integer)dx);
		mousePositions.remove((Integer)dy);
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
		if(selectedItem == true) {
			selectedItem = false;
			return;
		}
		
		Iterator<SlotDevice> it;
		MainFrame.getInstance().getPageSelectionModel().removeAllFromSelectionList();
		
		if(MainFrame.getInstance().getLastSelectedPageView().getPage().getPageModel() != null) {
			it = MainFrame.getInstance().getLastSelectedPageView().getPage().getPageModel().getDeviceIterator();
			
			while(it.hasNext()){
				SlotDevice sp = it.next();
				if(sp.getPosition().getX() >= x && sp.getPosition().getX() <= e.getX() &&
				   sp.getPosition().getY() >= y && sp.getPosition().getY() <= e.getY()) {
					
					MainFrame.getInstance().getPageSelectionModel().addToSelectionList(sp);
				
				}
			}
		}
	}
	
	
	@Override
	public String toString() {
		return "select";
	}
	
	private void deselectAll() {
		if(MainFrame.getInstance().getPageSelectionModel().getSelectionListSize() != 0) {
			Iterator<SlotDevice> it = MainFrame.getInstance().getLastSelectedPageView().getPage().getPageModel().getDeviceIterator();
			MainFrame.getInstance().getPageSelectionModel().removeAllFromSelectionList();
				
			while(it.hasNext()){
				SlotDevice sp = it.next();
				sp.notifySubscribers(new Color(255, 255, 255));
			}
		}
	}
}
