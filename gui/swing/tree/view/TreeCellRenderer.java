package gui.swing.tree.view;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import gui.swing.tree.model.TreeItem;
import model.repository.Document;
import model.repository.Page;
import model.repository.Project;
import model.repository.Workspace;
import model.repository.slot.Slot;

import java.awt.*;
import javax.swing.*;
import java.net.URL;

public class TreeCellRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);

        if (((TreeItem)value).getNodeModel() instanceof Workspace) {
            URL imageURL = getClass().getResource("images/tdiagram.gif");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);

        } else if (((TreeItem)value).getNodeModel() instanceof Project) {
            URL imageURL = getClass().getResource("images/tproject.gif");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);
        } else if (((TreeItem)value).getNodeModel() instanceof Document) {
            URL imageURL = getClass().getResource("images/tproject.gif");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);
        } else if (((TreeItem)value).getNodeModel() instanceof Page) {
        	URL imageURL = getClass().getResource("images/tdiagram.gif");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);
        } else if (((TreeItem)value).getNodeModel() instanceof Slot) {
        	/* Da bi imao default ikonicu
        	URL imageURL = getClass().getResource("images/tdiagram.gif");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);*/
        }
        return this;
    }

}
