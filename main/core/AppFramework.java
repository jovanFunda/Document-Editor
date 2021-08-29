package main.core;

import commands.ICommandManager;
import exceptions.IErrorHandler;
import model.repository.slot.handler.SlotHandler;

public abstract class AppFramework {
	
	protected Gui gui;
	protected Repository rep;
	protected SlotHandler slotHandler;
	protected IErrorHandler errorHandler;
	protected ICommandManager commandManager;
	
	public abstract void run();
	
	public void init(Gui gui, Repository rep, IErrorHandler errorHandler) {
		this.gui = gui;
		this.rep = rep;
		this.slotHandler = rep.getSlotHandler();
		this.errorHandler = errorHandler;
		this.errorHandler.addSubscriber(gui);
	}
	
	public SlotHandler getSlotHandler() {
		return slotHandler;
	}
}