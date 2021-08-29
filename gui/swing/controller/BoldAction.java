package gui.swing.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class BoldAction extends AbstractAction{
	private JTextPane pane;

	public BoldAction(JTextPane pane) {
		this.pane = pane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		 StyledDocument doc = (StyledDocument) pane.getDocument();
		    int selectionEnd = pane.getSelectionEnd();
		    int selectionStart = pane.getSelectionStart();
		    if (selectionStart == selectionEnd) {
		      return;
		    }
		    Element element = doc.getCharacterElement(selectionStart);
		    AttributeSet as = element.getAttributes();

		    MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
		    StyleConstants.setBold(asNew, !StyleConstants.isBold(as));
		    doc.setCharacterAttributes(selectionStart, pane.getSelectedText()
		        .length(), asNew, true);
		    
		
	}

}
