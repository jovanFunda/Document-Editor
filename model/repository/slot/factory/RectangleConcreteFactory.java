package model.repository.slot.factory;

import java.awt.Point;

import graphics.model.elements.RectangleElement;
import graphics.model.elements.SlotDevice;
import graphics.view.painters.RectanglePainter;
import model.repository.Page;

public class RectangleConcreteFactory extends AbstractSlotFactory{

	@Override
	public SlotDevice createSlot(Point point, Page page, int cntSlot) {
		return RectanglePainter.createDefault(point, cntSlot);
	}

}
