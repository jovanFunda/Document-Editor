package model.repository.slot;

import java.io.Serializable;

public class Element implements Serializable {
	
	private ElementType tip;
	private String path;

	public ElementType getTip() {
		return tip;
	}

	public void setTip(ElementType tip) {
		this.tip = tip;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
