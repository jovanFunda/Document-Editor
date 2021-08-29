package gui.swing.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import gui.swing.controller.ShareDocumentAction;
import model.repository.Document;
import model.repository.Project;
import model.repository.node.Node;

public class ShareDocumentDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	ShareDocumentDialog dialog = this;

	public ShareDocumentDialog(Document doc) {
		
		setSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        setTitle("Podeli dokument");
        setResizable(false);
        
        JLabel chooseProject = new JLabel("Izaberi projekat kome zelis podeliti dokument");
      
        JComboBox<Project> comboBox = new JComboBox<Project>();
  
        for (Node project : MainFrame.getInstance().getDocumentRepository().getWorkspace().getChildren()) {
        	if(!(doc.getParent() == (Project)project)) {
        		comboBox.addItem((Project)project);
        	}
		}
        
        JButton submitButton = new JButton("Potvrdi");
        submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShareDocumentAction(doc, (Project)comboBox.getSelectedItem());
				dialog.setVisible(false);
			}
		});
        
        GridLayout bl = new GridLayout(3, 1);
        
        setLayout(bl);
        add(chooseProject, bl);
        add(comboBox, bl);
        add(submitButton, bl);
        setVisible(true);
	}
	
	
}
