package gui.swing.controller;

import commands.MyCommand;

public class ActionManager {

	private RectangleAction rectangleAction;
	private TriangleAction triangleAction;
	private CircleAction circleAction;
	private SelectAction selectAction;
	private UndoAction undoAction;
	private RedoAction redoAction;
	private NewProjectAction newProjectAction;
	private DeleteAction deleteAction;
	private DeleteSlotAction deleteSlotAction;
	private MoveAction moveAction;
	private NewDocumentAction newDocumentAction;
	private NewPageAction newPageAction;
	private RenameAction renameAction;
	private RotateAction rotateAction;
	private ScaleAction scaleAction;
	private ShareDocumentAction shareDocumentAction;
	private SlotActionManager slotActionManager;
	private SaveProjectAction saveProjectAction;
	private OpenProjectAction openProjectAction;
	private SaveWorkspaceAction saveWorkspaceAction;
	private OpenWorkspaceAction openWorkspaceAction;

	public ActionManager() {
		initialiseActions();
	}
	
	void initialiseActions() {
		rectangleAction = new RectangleAction();
		newProjectAction = new NewProjectAction();
		triangleAction = new TriangleAction();
		circleAction = new CircleAction();
		selectAction = new SelectAction();
		undoAction = new UndoAction();
		redoAction = new RedoAction();
		deleteAction = new DeleteAction();
		deleteSlotAction = new DeleteSlotAction();
		moveAction = new MoveAction();
		newDocumentAction = new NewDocumentAction();
		newPageAction = new NewPageAction();
		renameAction = new RenameAction();
		rotateAction = new RotateAction();
		scaleAction = new ScaleAction();
		selectAction = new SelectAction();
		slotActionManager = new SlotActionManager();
		saveProjectAction = new SaveProjectAction();
		openProjectAction = new OpenProjectAction();
		saveWorkspaceAction = new SaveWorkspaceAction();
		openWorkspaceAction = new OpenWorkspaceAction();
	}

	public RectangleAction getRectangleAction() {
		return rectangleAction;
	}
	
	public OpenWorkspaceAction getOpenWorkspaceAction() {
		return openWorkspaceAction;
	}
	
	public SaveWorkspaceAction getSaveWorkspaceAction() {
		return saveWorkspaceAction;
	}

	public void setRectangleAction(RectangleAction rectangleAction) {
		this.rectangleAction = rectangleAction;
	}
	
	public OpenProjectAction getOpenProjectAction() {
		return openProjectAction;
	}
	
	public void setOpenProjectAction(OpenProjectAction openProjectAction) {
		this.openProjectAction = openProjectAction;
	}

	public TriangleAction getTriangleAction() {
		return triangleAction;
	}

	public void setTriangleAction(TriangleAction triangleAction) {
		this.triangleAction = triangleAction;
	}

	public CircleAction getCircleAction() {
		return circleAction;
	}
	
	public SaveProjectAction getSaveProjectAction() {
		return saveProjectAction;
	}

	public void setCircleAction(CircleAction circleAction) {
		this.circleAction = circleAction;
	}

	public SelectAction getSelectAction() {
		return selectAction;
	}
	
	public RedoAction getRedoAction() {
		return redoAction;
	}
	
	public UndoAction getUndoAction() {
		return undoAction;
	}

	public NewProjectAction getNewProjectAction() {
		return newProjectAction;
	}

	public void setNewProjectAction(NewProjectAction newProjectAction) {
		this.newProjectAction = newProjectAction;
	}

	public DeleteAction getDeleteAction() {
		return deleteAction;
	}

	public void setDeleteAction(DeleteAction deleteAction) {
		this.deleteAction = deleteAction;
	}

	public DeleteSlotAction getDeleteSlotAction() {
		return deleteSlotAction;
	}

	public void setDeleteSlotAction(DeleteSlotAction deleteSlotAction) {
		this.deleteSlotAction = deleteSlotAction;
	}

	public MoveAction getMoveAction() {
		return moveAction;
	}

	public void setMoveAction(MoveAction moveAction) {
		this.moveAction = moveAction;
	}

	public NewDocumentAction getNewDocumentAction() {
		return newDocumentAction;
	}

	public void setNewDocumentAction(NewDocumentAction newDocumentAction) {
		this.newDocumentAction = newDocumentAction;
	}

	public NewPageAction getNewPageAction() {
		return newPageAction;
	}

	public void setNewPageAction(NewPageAction newPageAction) {
		this.newPageAction = newPageAction;
	}

	public RenameAction getRenameAction() {
		return renameAction;
	}

	public void setRenameAction(RenameAction renameAction) {
		this.renameAction = renameAction;
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

	public ShareDocumentAction getShareDocumentAction() {
		return shareDocumentAction;
	}

	public void setShareDocumentAction(ShareDocumentAction shareDocumentAction) {
		this.shareDocumentAction = shareDocumentAction;
	}

	public SlotActionManager getSlotActionManager() {
		return slotActionManager;
	}

	public void setSlotActionManager(SlotActionManager slotActionManager) {
		this.slotActionManager = slotActionManager;
	}

	public void setSelectAction(SelectAction selectAction) {
		this.selectAction = selectAction;
	}

	public void setUndoAction(UndoAction undoAction) {
		this.undoAction = undoAction;
	}

	public void setRedoAction(RedoAction redoAction) {
		this.redoAction = redoAction;
	}

	public void changeProperties(MyCommand notification) {
		if(notification.getAction().equals("undo")) {
			if(notification.getEnable().equals("enable")) {
				getUndoAction().setEnabled(true);
			} else {
				getUndoAction().setEnabled(false);
			}
		} else if(notification.getAction().equals("redo")) {
			if(notification.getEnable().equals("enable")) {
				getRedoAction().setEnabled(true);
			} else {
				getRedoAction().setEnabled(false);
			}
		}
	}
}