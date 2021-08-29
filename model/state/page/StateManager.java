package model.state.page;

import java.io.Serializable;

import model.repository.Page;

public class StateManager implements Serializable {

	private State currentState;
	
	CircleState circleState; 
	RectangleState rectangleState;
	TriangleState triangleState;
	SelectState selectState;
	
	public StateManager(Page page)
	{
		circleState = new CircleState(page); 
		triangleState = new TriangleState(page); 
		rectangleState = new RectangleState(page);
		selectState = new SelectState(page);
     	currentState = selectState;
	}
	
	public void setCircleState() { currentState = circleState; }
	public void setRectangleState(){ currentState = rectangleState; }
	public void setSelectState(){ currentState = selectState; }
	public void setTriangleState() { currentState = triangleState; }
	public void setNullState() { currentState = null; }
	
	
	public State getCurrentState() {
		return currentState;
	}
	
	@Override
	public String toString() {
		if(currentState == circleState) {
			return "circle";
		} else if(currentState == triangleState) {
			return "triangle";
		} else if(currentState == rectangleState) {
			return "rectangle";
		} else if(currentState == selectState) {
			return "select";
		}
		
		return "null";
	}
	
}
