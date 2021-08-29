package gui.swing.tree.view;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;

import gui.swing.tree.Tree;
import gui.swing.tree.model.TreeItem;
import model.repository.Document;
import model.repository.Page;
import model.repository.Project;
import model.repository.Workspace;
import model.repository.node.Node;
import model.repository.slot.Slot;

public class TreeImplementation implements Tree {
	
    private TreeView treeView;
    private DefaultTreeModel treeModel;
    private TreeItem selectedNode;

	@Override
	public JTree generateTree(Workspace workspace) {
		
		TreeItem root = new TreeItem(workspace);
        treeModel = new DefaultTreeModel(root);
        treeView = new TreeView(treeModel);
        return treeView;
		
	}

	@Override
	public void addProject(Project project) {
		Node nodeModel = ((TreeItem)treeModel.getRoot()).getNodeModel();
		TreeItem projectTree;
		((TreeItem)treeModel.getRoot()).add(projectTree = new TreeItem(project));
		project.setProjectTree(projectTree);
		((Workspace) nodeModel).addChild(project);
		SwingUtilities.updateComponentTreeUI(treeView);
	}
	
	@Override
	public void addDocument(Document document) {
		Node nodeDocument = ((Node)document);
		Node nodeProject = nodeDocument.getParent();
		TreeItem documentTree;
		selectedNode.add(documentTree = new TreeItem(document));
		document.setDocumentTree(documentTree);
		Project project = (Project)nodeProject;
		project.addChild(document);
		SwingUtilities.updateComponentTreeUI(treeView);
	}
	
	@Override
	public void addOpenDocument(Document document, Project project) {
		(project.getProjectTree()).add(document.getDocumentTree());
		SwingUtilities.updateComponentTreeUI(treeView);
	}
	
	@Override
	public void removeDocument(Document document) {
		Node nodeDocument = ((Node)document);
		Node parent = nodeDocument.getParent();
		((Project) parent).removeChild(nodeDocument);
		SwingUtilities.updateComponentTreeUI(treeView);
	}
	
	@Override
	public void removeProject(Project project) {
		Node nodeProject = ((Node)project);
		Node parent = nodeProject.getParent();
		((Workspace) parent).removeChild(nodeProject);
		SwingUtilities.updateComponentTreeUI(treeView);
	}

	@Override
	public void removePage(Page page) {
		Node nodePage = ((Node)page);
		Node parent = nodePage.getParent();
		((Document) parent).removeChild(nodePage);
		SwingUtilities.updateComponentTreeUI(treeView);
	}
	
	@Override
	public void addPage(Page page) {
		Node nodePage = ((Node)page);
		Node nodeDocument = nodePage.getParent();
		Document document = (Document)nodeDocument;
		TreeItem pageTree;
		selectedNode.add(pageTree = new TreeItem(page));
		page.setPageTree(pageTree);
		document.addChild(page);
		SwingUtilities.updateComponentTreeUI(treeView);
	}
	
	
	public TreeView getTreeView() {
		return treeView;
	}

	public void setTreeView(TreeView treeView) {
		this.treeView = treeView;
	}

	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}

	public void setTreeModel(DefaultTreeModel treeModel) {
		this.treeModel = treeModel;
	}

	@Override
	public void addSlot(Slot slot, Page page) {
		page.getPageTree().add(new TreeItem(slot));
		page.addChild(slot);
		SwingUtilities.updateComponentTreeUI(treeView);
	}

	@Override
	public TreeItem getSelectedNode() {
		return selectedNode;
	}

	@Override
	public void setSelectedNode(TreeItem item) {
		selectedNode = item;
	}

	
}
