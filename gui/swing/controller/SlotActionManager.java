package gui.swing.controller;

import javax.swing.Icon;

public class SlotActionManager {

	private MoveAction moveAction;
	private RotateAction rotateAction;
	private ScaleAction scaleAction;
	private DeleteSlotAction deleteSlotAction;
	private OpenElementAction addElementAction;

	public SlotActionManager() {
		initialiseActions();
	}
	
	void initialiseActions() {
		moveAction = new MoveAction();
		rotateAction = new RotateAction();
		scaleAction = new ScaleAction();
		deleteSlotAction = new DeleteSlotAction();
		addElementAction = new OpenElementAction();
	}

	public MoveAction getMoveAction() {
		return moveAction;
	}

	public void setMoveAction(MoveAction moveAction) {
		this.moveAction = moveAction;
	}

	public RotateAction getRotateAction() {
		return rotateAction;
	}

	public void setRotateAction(RotateAction rotateAction) {
		this.rotateAction = rotateAction;
	}

	public ScaleAction getScaleAction() {
		return scaleAction;
	}

	public void setScaleAction(ScaleAction scaleAction) {
		this.scaleAction = scaleAction;
	}

	public DeleteSlotAction getDeleteSlotAction() {
		return deleteSlotAction;
	}

	public void setDeleteSlotAction(DeleteSlotAction deleteSlotAction) {
		this.deleteSlotAction = deleteSlotAction;
	}

	public OpenElementAction getAddElementAction() {
		return addElementAction;
	}

	
	
}
