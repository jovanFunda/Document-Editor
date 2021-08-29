package gui.swing.view;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import gui.swing.controller.NewPictureAction;
import gui.swing.controller.NewTextAction;

public class OpenElementDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton pictureBtn;
	private JButton textBtn;

	public OpenElementDialog() {

		setSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setTitle("Dodaj element");
        setResizable(false);
        
        JPanel hPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(hPanel, BoxLayout.X_AXIS);
        hPanel.setLayout(boxLayout);

        pictureBtn = new JButton("Otvori sliku");
        pictureBtn.addActionListener(new NewPictureAction());
        hPanel.add(pictureBtn);

        textBtn = new JButton("Otvori tekst");
        textBtn.addActionListener(new NewTextAction());

        textBtn = new JButton("Otvori tekst");
        textBtn.addActionListener(new NewTextAction());
        
        hPanel.add(pictureBtn);
        hPanel.add(textBtn);
        add(hPanel);

        setVisible(true);
        
	}
}