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
import model.repository.Workspace;
import model.repository.node.Node;

public class SaveWorkspaceAction extends AbstractAction {

	public SaveWorkspaceAction(){
		putValue(NAME, "Sacuvaj workspace");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new WorkspaceFileFilter());
		
		if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Workspace) {
			
			Workspace workspace = (Workspace) MainFrame.getInstance().getTree().getSelectedNode().getNodeModel();
			File workspaceFile = workspace.getWorkspaceFile();	
			
			if (workspace.getWorkspaceFile() == null){
			         if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
			                   workspaceFile = jfc.getSelectedFile();           	 
			         } else {
			        	return; 
			         }		         
			}     
		      
			         
		    ObjectOutputStream os;
		    
			try {
				
				os = new ObjectOutputStream(new FileOutputStream(workspaceFile));
				
				for (Node project : workspace.getChildren()) {
					
					System.out.println(project.getName());
					
					Project p = (Project)project;
					os.writeObject(p.getProjectFile().getAbsolutePath());
					
				}
				
				workspace.setWorkspaceFile(workspaceFile);
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}
}
