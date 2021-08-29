package model.repository.slot.factory;

import java.awt.Point;

import graphics.model.elements.SlotDevice;
import graphics.model.elements.TriangleElement;
import graphics.view.painters.TrianglePainter;
import model.repository.Page;

public class TriangleConcreteFactory extends AbstractSlotFactory {

	@Override
	public SlotDevice createSlot(Point point, Page page, int cntSlot) {
		return TrianglePainter.createDefault(point, cntSlot);
	}

}
