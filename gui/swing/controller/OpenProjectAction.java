package gui.swing.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import graphics.model.elements.SlotDevice;
import graphics.view.painters.CirclePainter;
import graphics.view.painters.RectanglePainter;
import graphics.view.painters.TrianglePainter;
import gui.swing.view.DocumentView;
import gui.swing.view.MainFrame;
import gui.swing.view.PageView;
import model.repository.Document;
import model.repository.Project;
import model.repository.slot.Slot;
import model.repository.Page;

public class OpenProjectAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public OpenProjectAction(){
		putValue(NAME, "Otvori projekat");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new SlotFileFilter());
		
		if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
			try {
				ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));
				  
				Project p = null;
				
				try {
					p = (Project) os.readObject();
				} catch (ClassNotFoundException exception) {
					exception.printStackTrace();
				}
			     
				
		       MainFrame.getInstance().getTree().addProject(p);
		       
			   for (int i = 0; i < p.getChildren().size(); i++) {
				   
				   DocumentView view = new DocumentView((Document) p.getChildren().get(i));
				   
				   MainFrame.getInstance().getTree().setSelectedNode(p.getProjectTree());
				   MainFrame.getInstance().getTree().addDocument(view.getDocument());
				   
				   for(int j = 0; j < ((Document)p.getChildren().get(i)).getChildren().size(); j++) { 
					   
					   Document doc = (Document) ((Document)p.getChildren().get(i));
					   PageView pageView = new PageView((Page) ((Document) p.getChildren().get(i)).getChildren().get(j));
					   MainFrame.getInstance().getTree().setSelectedNode(((Document)p.getChildren().get(i)).getDocumentTree());
					   MainFrame.getInstance().getTree().addPage(pageView.getPage());
					   view.add(pageView);
					   
					   
					   for(int k = 0; k < ((Page)doc.getChildren().get(j)).getPageModel().getPageElements().size(); k++) {
							
						   SlotDevice device = ((Page)doc.getChildren().get(j)).getPageModel().getPageElements().get(k);
							
						   
						   if(device.getType().equals("rectangle")) {
							   
							   SlotDevice newDevice = RectanglePainter.createDefault(device.getPosition(), k);
							   newDevice.setSize(device.getSize());
							   newDevice.setPosition(device.getPosition());
							   newDevice.setAngle(device.getAngle());
							   
							   
							   RectanglePainter painter = new RectanglePainter((Point) newDevice.getPosition());
								 
							   painter.getDevice().setName("Recc " + k);
								 
							   Slot slot = new Slot(painter.getDevice().getName(), ((Page)doc.getChildren().get(j)));
							   painter.getDevice().setSlot(slot);
								 
							   MainFrame.getInstance().getTree().addSlot(slot, ((Page)doc.getChildren().get(j)));
		 
							   
							} else if(device.getType().equals("circle")) {
								
								System.out.println(device.getPosition() + " open project");
								SlotDevice newDevice = CirclePainter.createDefault(device.getPosition(), k);
								newDevice.setSize(device.getSize());
								newDevice.setPosition(device.getPosition());
								newDevice.setAngle(device.getAngle());								  
								   
								CirclePainter painter = new CirclePainter((Point) newDevice.getPosition());
									 
								painter.getDevice().setName("Circle " + k);
									 
								Slot slot = new Slot(painter.getDevice().getName(), ((Page)doc.getChildren().get(j)));
								painter.getDevice().setSlot(slot);					 

								MainFrame.getInstance().getTree().addSlot(slot, ((Page)doc.getChildren().get(j))); 

								   
							} else if(device.getType().equals("triangle")) {
								SlotDevice newDevice = TrianglePainter.createDefault((Point) device.getPosition(), k);
								   newDevice.setSize(device.getSize());
								   newDevice.setPosition(device.getPosition());
								   newDevice.setAngle(device.getAngle());
							}
					   }
				   }	   
			   }
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		
		}
	}
}
