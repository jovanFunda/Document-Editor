package model.repository;

import main.core.Repository;
import model.repository.slot.handler.SlotHandler;

public class RepositoryImplement implements Repository {

	private Workspace root;
	private SlotHandler slotHandler;

	public RepositoryImplement() {
		root = new Workspace("Workspace");
		slotHandler = new SlotHandler();
	}

	@Override
	public Workspace getWorkspace() {
		return root;
	}
	
	public SlotHandler getSlotHandler() {
		return slotHandler;
	}
}
