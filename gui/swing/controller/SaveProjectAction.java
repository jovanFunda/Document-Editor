package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import gui.swing.view.MainFrame;
import model.repository.Project;


public class SaveProjectAction extends AbstractAction {

	
	private static final long serialVersionUID = 1L;

	public SaveProjectAction() {
		putValue(NAME, "Sacuvaj projekat");
		putValue(SHORT_DESCRIPTION, "Sacuvaj projekat");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new SlotFileFilter());
		
		if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Project) {
			
			Project project = (Project) MainFrame.getInstance().getTree().getSelectedNode().getNodeModel();
			File projectFile = project.getProjectFile();
			
			if (project.getProjectFile()==null){
			         if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
			                   projectFile=jfc.getSelectedFile();           	 
			         } else {
			        	return; 
			         }
			         
			}     
		      
			         
		    ObjectOutputStream os;
			try {
				os = new ObjectOutputStream(new FileOutputStream(projectFile));
				os.writeObject(project);
				project.setProjectFile(projectFile);
				project.setChanged(false);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}
}
