package gui.swing.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gui.swing.tree.controller.CustomTreeSelectionListener;
import model.observer.IObserver;
import model.observer.helpClasses.ObserverHelperOne;
import model.repository.Document;
import model.repository.Page;
import model.repository.Project;
import model.repository.node.Node;
import model.repository.node.NodeComposite;

public class DocumentView extends JPanel implements IObserver{

	private static final long serialVersionUID = 1L; 
	
	private JPanel topPanel;
	private Document document;
	
	public DocumentView(Document document) {
		
		this.document = document;
		this.document.addSubscriber(this);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		setName(document.getName());
		topPanel = new JPanel();
		updateUI();
		add(topPanel);
	}
	
	public void saveDocumentState() {
		//System.out.println("\"" + getName() + "\" dokument je zatvoren.");
	}

	@Override
	public void update(Object notification) {		
		
		if(notification instanceof ObserverHelperOne && ((ObserverHelperOne) notification).name.equals("Obrisan page")) {
			
			topPanel.remove(((ObserverHelperOne)notification).pageView);
			updateUI();
			
		} else if(notification instanceof PageView) {
			
			topPanel.add((PageView)notification);
			setName(((PageView)notification).getPage().getParent().getName());
			topPanel.updateUI();
			
		} else if (notification.equals("Prikaci se na tabbedPane")) {
			
			// Prikaci sve tabove prilikom klika na PROJEKAT
			Project proj = (Project) this.document.getParent();
			for (Node doc : proj.getChildren() ) {
				MainFrame.getInstance().getTabbedPane().add(this);
			}
			
			MainFrame.getInstance().getTabbedPane().updateUI();
			
		} else if(notification instanceof String && !notification.equals("Prikaci se na tabbedPane")){
			// Menjamo naziv DocumentViewa / Taba
			this.setName((String)notification);
			
			NodeComposite docm = (NodeComposite) getDocument();
			
			for (Node page : docm.getChildren()) {
				Page p = (Page) page;
				p.notifySubscribers("Potrebna promena patha");
			}
			
			MainFrame.getInstance().getTabbedPane().removeAll();    
        	Project selectedProject = ((Project) MainFrame.getInstance().getTree().getSelectedNode().getNodeModel().getParent());
        	
        	for (Node doc : selectedProject.getChildren()) {
        		System.out.println("ovo ");
        		((Document)doc).notifySubscribers("Prikaci se na tabbedPane");
			}
		}
	}
	
	public Document getDocument() {
		return document;
	}
	
	public JPanel getPanel() {
		return topPanel;
	}
}