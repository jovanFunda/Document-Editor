package gui.swing.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import gui.swing.tree.controller.CustomTreeSelectionListener;

public class RenameDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	JLabel labelNaziv;
	JTextField unetText;
	JButton okButton;
	MainMenuBar bar;
	MainToolBar tool;
	public String name = "";
	
	public RenameDialog() {
		
		setTitle("Promeni naziv");
		setSize(300, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		labelNaziv = new JLabel("Unesi novi naziv selektovanog cvora: ");
		unetText = new JTextField(10);
		okButton = new JButton("Potvrdi");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				name = unetText.getText();
				MainFrame.getInstance().getTree().getSelectedNode().setName(name);
				MainFrame.getInstance().getWorkspaceTree().updateUI();
				setVisible(false);
			}
		});
		
		JPanel jp = new JPanel();
		
		FlowLayout fl = new FlowLayout();
		jp.setLayout(fl);
		
		jp.add(labelNaziv);
		jp.add(unetText);
		jp.add(okButton);
		add(jp);
		setVisible(true);
	}

	public String getName() {
		return name;
	}

}
