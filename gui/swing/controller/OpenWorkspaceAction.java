package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import gui.swing.view.MainFrame;

public class OpenWorkspaceAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public OpenWorkspaceAction(){
		putValue(NAME, "Otvori workspace");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new WorkspaceFileFilter());
		
		if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
			try {
				ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));
				  
				String workspace = null;
				
				try {
					workspace = (String) os.readObject();
				} catch (ClassNotFoundException exception) {
					exception.printStackTrace();
				}
			     
		       System.out.println(workspace);
			   
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		
		}
	}
}