package gui.swing.tree.view;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import gui.swing.tree.controller.CustomTreeCellEditor;
import gui.swing.tree.controller.CustomTreeSelectionListener;

public class TreeView extends JTree{
	
	private static final long serialVersionUID = 1L;

	public TreeView(DefaultTreeModel defaultTreeModel) {
		setModel(defaultTreeModel);
        TreeCellRenderer TreeCellRenderer = new TreeCellRenderer();
        addTreeSelectionListener(CustomTreeSelectionListener.getInstance());
        setCellEditor(new CustomTreeCellEditor(this, TreeCellRenderer));
        setCellRenderer(TreeCellRenderer);
        setEditable(true);
	}

}
