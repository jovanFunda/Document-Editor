package model.repository.slot.handler;

public class SlotObserverNotifier {
	
	private String type;
	private double x,y;

	public SlotObserverNotifier(String type, double x, double y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	
	public double getX() {
		return x;
	}
	
	public String getType() {
		return type;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}