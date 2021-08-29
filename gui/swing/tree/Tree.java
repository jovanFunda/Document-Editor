package gui.swing.tree;

import javax.swing.JTree;

import gui.swing.tree.model.TreeItem;
import model.repository.Document;
import model.repository.Page;
import model.repository.Project;
import model.repository.Workspace;
import model.repository.slot.Slot;

public interface Tree {

	JTree generateTree(Workspace workspace);
    void addProject(Project project);
    void removeProject(Project project);
    void addDocument(Document document);
    void removeDocument(Document document);
    void removePage(Page page);
    void addPage(Page page);
    void addSlot(Slot slot, Page page);
    void addOpenDocument(Document document, Project project);
    TreeItem getSelectedNode();
    void setSelectedNode(TreeItem item);
}
