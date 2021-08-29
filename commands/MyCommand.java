package commands;

public class MyCommand {

	String action;
	String enable;
	
	public MyCommand(String action, String enable) {
		this.action = action;
		this.enable = enable;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	
	
}
