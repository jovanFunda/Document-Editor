package model.repository.slot.handler;

import gui.swing.view.MainFrame;

public class SlotHandler {

	MoveAdapter moveAdapter;
	ScaleAdapter scaleAdapter;
	RotateAdapter rotateAdapter;
	
	public SlotHandler() {
		moveAdapter = new MoveAdapter();
		scaleAdapter = new ScaleAdapter();
		rotateAdapter = new RotateAdapter();
	}
	
	public void calculateOperation(String type) {
		if(type.equals("move")) {
			startMoveListener();
		} else if(type.equals("scale")) {
			startScaleListener();
		} else if(type.equals("rotate")) {
			startRotateListener();
		}
	}

	public void startMoveListener() {
		MainFrame.getInstance().getLastSelectedPageView().addMouseListener(moveAdapter);
		MainFrame.getInstance().getLastSelectedPageView().addMouseMotionListener(moveAdapter);
	}
	
	public void startScaleListener() {
		MainFrame.getInstance().getLastSelectedPageView().addMouseListener(scaleAdapter);
		MainFrame.getInstance().getLastSelectedPageView().addMouseMotionListener(scaleAdapter);
	}
	
	public void startRotateListener() {
		MainFrame.getInstance().getLastSelectedPageView().addMouseListener(rotateAdapter);
		MainFrame.getInstance().getLastSelectedPageView().addMouseMotionListener(rotateAdapter);
	}
}
