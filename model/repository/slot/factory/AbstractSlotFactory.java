package model.repository.slot.factory;

import java.awt.Point;
import java.awt.event.MouseEvent;

import graphics.model.elements.SlotDevice;
import model.repository.Page;

public abstract class AbstractSlotFactory {

	
	public void chooseSlot(MouseEvent e, Page page, int cntSlot) {
	
		createSlot(e.getPoint(), page, cntSlot);
		
	};
	
	
	public abstract SlotDevice createSlot(Point point, Page page, int cntSlot);
	
	
}
