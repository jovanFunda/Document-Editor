package gui.swing.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import gui.swing.controller.BoldAction;
import gui.swing.controller.ItalicAction;
import gui.swing.controller.NewPictureAction;
import gui.swing.controller.UnderlineAction;
import model.repository.slot.ElementType;

public class ElementView extends JFrame {
	
	private JTextPane textPane;
	private JButton changeBtn;
	private JButton boldBtn;
	private JButton italicBtn;
	private JButton underlineBtn;
	private JTextPane pane;

	public ElementView() {
		if(MainFrame.getInstance().getLastSelectedSlot().getDevice().getSlot().getElement().getTip() == ElementType.Slika) {
			this.setSize(400,300);
			this.setLocationRelativeTo(null);
			
			JPanel vPanel = new JPanel();
			BoxLayout box = new BoxLayout(vPanel, BoxLayout.Y_AXIS);
			vPanel.setLayout(box);
	        
	        changeBtn = new JButton("Promeni sliku");
	        changeBtn.addActionListener(new NewPictureAction());
	        vPanel.add(changeBtn);
	        
	        JLabel label = new JLabel(new ImageIcon(MainFrame.getInstance().getLastSelectedSlot().getDevice().getSlot().getElement().getPath()));
	        vPanel.add(label);
	        
	        this.add(vPanel);

	        this.setVisible(true);
		}
		else if(MainFrame.getInstance().getLastSelectedSlot().getDevice().getSlot().getElement().getTip() == ElementType.Tekst) {
			this.setSize(400,300);
			this.setLocationRelativeTo(null);
			
			JPanel panel = new JPanel();
			GridLayout grid1 = new GridLayout(1, 3);
			panel.setLayout(grid1);
			
			GridLayout grid2 = new GridLayout(2, 1);
			pane = new JTextPane();
			
			boldBtn = new JButton("Bold");
			boldBtn.addActionListener(new BoldAction(pane));
			
			italicBtn = new JButton("Italic");
			italicBtn.addActionListener(new ItalicAction(pane));
			
			underlineBtn = new JButton("Underline");
			underlineBtn.addActionListener(new UnderlineAction(pane));
			
			panel.add(boldBtn);
			panel.add(italicBtn);
			panel.add(underlineBtn);
			this.setLayout(grid2);
			this.add(panel, grid2);
			this.add(pane, grid2);
			this.setVisible(true);
		}
		else if(MainFrame.getInstance().getLastSelectedSlot().getDevice().getSlot().getElement().getTip() == ElementType.Tekst) {
			this.setSize(400,300);
			this.setLocationRelativeTo(null);

			JPanel panel = new JPanel();
			GridLayout grid1 = new GridLayout(1, 3);
			panel.setLayout(grid1);

			GridLayout grid2 = new GridLayout(2, 1);
			pane = new JTextPane();

			boldBtn = new JButton("Bold");
			boldBtn.addActionListener(new BoldAction(pane));

			italicBtn = new JButton("Italic");
			italicBtn.addActionListener(new ItalicAction(pane));

			underlineBtn = new JButton("Underline");
			underlineBtn.addActionListener(new UnderlineAction(pane));

			panel.add(boldBtn);
			panel.add(italicBtn);
			panel.add(underlineBtn);
			this.setLayout(grid2);
			this.add(panel, grid2);
			this.add(pane, grid2);
			this.setVisible(true);
		}
	}
}
