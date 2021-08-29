package graphics.model.elements;

import java.awt.Color;

import model.repository.node.Node;
import model.repository.slot.Slot;

public abstract class SlotElement extends Slot{
	
	protected Color fillColor;
	protected String name;
	protected String description;
	
	
	public SlotElement(String name, Node page){
		super(name, page);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
