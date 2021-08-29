package main.core;

import model.repository.Workspace;
import model.repository.slot.handler.SlotHandler;

public interface Repository {
	
	Workspace getWorkspace();
	SlotHandler getSlotHandler();

}
