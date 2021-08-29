package gui.swing.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.swing.view.ElementView;
import gui.swing.view.MainFrame;
import model.repository.slot.ElementType;

public class NewTextAction extends AbstractAction{
	private ElementView textView;

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getLastSelectedSlot().getDevice().getSlot().getElement().setTip(ElementType.Tekst);
		textView  = new ElementView();
		MainFrame.getInstance().getLastSelectedSlot().setElementView(textView);
	}

}
