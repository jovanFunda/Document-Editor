package gui.swing.view;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;


public class Palette extends JToolBar{

	private static final long serialVersionUID = 1L;

	private JToggleButton selectButton;
	private JToggleButton rectangleButton;
	private JToggleButton triangleButton;
	private JToggleButton circleButton;
	private JToggleButton moveBtn;
	private JToggleButton scaleBtn;
	private JToggleButton rotateBtn;
	private JToggleButton deleteBtn;
	private JToggleButton addElementBtn;
	private ButtonGroup gp;
	private ButtonGroup select;
	
	public Palette() {
		super(JToolBar.VERTICAL);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		gp = new ButtonGroup();
		select = new ButtonGroup();
		
		selectButton = new JToggleButton(MainFrame.getInstance().getActionManager().getSelectAction());
		gp.add(selectButton);
		add(selectButton);
		addSeparator();
		
		rectangleButton = new JToggleButton(MainFrame.getInstance().getActionManager().getRectangleAction());
		gp.add(rectangleButton);
		add(rectangleButton);
		addSeparator();
		
		triangleButton = new JToggleButton(MainFrame.getInstance().getActionManager().getTriangleAction());
		gp.add(triangleButton);
		add(triangleButton);
		addSeparator();
		
		circleButton = new JToggleButton(MainFrame.getInstance().getActionManager().getCircleAction());
		gp.add(circleButton);
		add(circleButton);
		
		addSeparator();
		addSeparator();
		
		moveBtn = new JToggleButton(MainFrame.getInstance().getSlotHandler().getMoveAction());
		addSeparator();
		select.add(moveBtn);
		add(moveBtn);
		
		scaleBtn = new JToggleButton(MainFrame.getInstance().getSlotHandler().getScaleAction());
		addSeparator();
		select.add(scaleBtn);
		add(scaleBtn);
		
		rotateBtn = new JToggleButton(MainFrame.getInstance().getSlotHandler().getRotateAction());
		addSeparator();
		select.add(rotateBtn);
		add(rotateBtn);
		
		deleteBtn = new JToggleButton(MainFrame.getInstance().getSlotHandler().getDeleteSlotAction());
		addSeparator();
		select.add(deleteBtn);
		add(deleteBtn);
		
		addElementBtn = new JToggleButton(MainFrame.getInstance().getSlotHandler().getAddElementAction());
		addSeparator();
		select.add(addElementBtn);
		add(addElementBtn);
		
	}

	public JToggleButton getSelectButton() {
		return selectButton;
	}

	public JToggleButton getRectangleButton() {
		return rectangleButton;
	}

	public JToggleButton getTriangleButton() {
		return triangleButton;
	}

	public JToggleButton getCircleButton() {
		return circleButton;
	}

	public JToggleButton getMoveBtn() {
		return moveBtn;
	}

	public JToggleButton getScaleBtn() {
		return scaleBtn;
	}

	public JToggleButton getRotateBtn() {
		return rotateBtn;
	}
	
	public ButtonGroup getGp() {
		return gp;
	}
	
	public ButtonGroup getSelect() {
		return select;
	}

	public JToggleButton getDeleteBtn() {
		return deleteBtn;
	}
}
