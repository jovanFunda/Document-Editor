package gui.swing.view;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InvalidSelectionDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public InvalidSelectionDialog(String greska) {
		setSize(new Dimension(300, 100));
        setLocationRelativeTo(null);
        setTitle("Greska!");
        setResizable(false);
        
        JPanel panel = new JPanel();
        
        JLabel label = new JLabel(greska);
        
        panel.add(label);
        
        add(panel);
        
        setVisible(true);
		
	}

}
