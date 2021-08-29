package gui.swing.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import gui.swing.view.ElementView;
import gui.swing.view.MainFrame;
import model.repository.slot.ElementType;

public class NewPictureAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private ElementView pictureView;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			MainFrame.getInstance().getLastSelectedSlot().getDevice().getSlot().getElement().setTip(ElementType.Slika);
			MainFrame.getInstance().getLastSelectedSlot().getDevice().getSlot().getElement().setPath(jfc.getSelectedFile().getAbsolutePath());

			System.out.println(MainFrame.getInstance().getLastSelectedSlot().getDevice().getSlot().getElement().getPath());

			pictureView = new ElementView();
			MainFrame.getInstance().getLastSelectedSlot().setElementView(pictureView);
			//System.out.println(MainFrame.getInstance().getLastSelectedSlot().getDevice().getSlot().getElement().getPath());
		}
	}
}