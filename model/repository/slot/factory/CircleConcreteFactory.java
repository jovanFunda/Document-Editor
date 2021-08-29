package model.repository.slot.factory;

import java.awt.Point;

import graphics.model.elements.SlotDevice;
import graphics.view.painters.CirclePainter;
import model.repository.Page;

public class CircleConcreteFactory extends AbstractSlotFactory {

	@Override
	public SlotDevice createSlot(Point position, Page page, int cntSlot) {
		return CirclePainter.createDefault(position, cntSlot);
	}

}
