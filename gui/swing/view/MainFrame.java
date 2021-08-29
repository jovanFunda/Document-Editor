package gui.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;

import exceptions.MyError;
import graphics.model.PageSelectionModel;
import graphics.view.painters.SlotPainter;
import gui.swing.controller.ActionManager;
import gui.swing.controller.SlotActionManager;
import gui.swing.tree.Tree;
import gui.swing.tree.view.TreeImplementation;
import main.core.Repository;
import model.repository.Page;

public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private static MainFrame instance;
	
	private Repository documentRepository;
	private JTree workspaceTree;
	private Tree tree;
	private JTabbedPaneCloseButton tabbedPane; 
	private JSplitPane split;
	private Palette palette;
	private ActionManager actionManager;
	private SlotActionManager slotAction;
	private Page lastSelectedPage;
	private SlotPainter lastSelectedSlot;
	private PageView lastSelectedPageView;
	private PageSelectionModel pageSelectionModel;
	
	
	private MainFrame() {
		actionManager = new ActionManager();
		slotAction = new SlotActionManager();
	}
	
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	public void initialiseWorkspaceTree() {
		tree = new TreeImplementation();
		workspaceTree = tree.generateTree(documentRepository.getWorkspace());
		initialiseGUI();
	}
	
	public void initialiseGUI() {
		Dimension d = new Dimension(1200, 900);
		setSize(d);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Rukovalac dokumenata");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		MainMenuBar menuBar = new MainMenuBar(this);
		setJMenuBar(menuBar);

		MainToolBar toolBar = new MainToolBar();
		add(toolBar, BorderLayout.NORTH);

		tabbedPane = new JTabbedPaneCloseButton();
		
		palette = new Palette();
		getContentPane().add(palette,BorderLayout.EAST);

		JScrollPane scroll = new JScrollPane(workspaceTree);
		scroll.setMinimumSize(new Dimension(200,150));
		
		pageSelectionModel = new PageSelectionModel();
		
		split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, tabbedPane);
		getContentPane().add(split,BorderLayout.CENTER);
		split.setDividerLocation(250);
		split.setOneTouchExpandable(true);
	}
	
	public void showError(MyError e) {
		JOptionPane.showMessageDialog(MainFrame.getInstance(), e.getErrorMessage());
	}
	
	public void setDocumentRepository(Repository documentRepository) {
        this.documentRepository = documentRepository;
    }
		
	public Repository getDocumentRepository() {
		return documentRepository;
	}

	public JFrame getJFrame() {
		return this;
	}
	
	public JTree getWorkspaceTree() {
		return workspaceTree;
	}

	public void setWorkspaceTree(JTree workspaceTree) {
		this.workspaceTree = workspaceTree;
	}

	public Tree getTree() {
		return tree;
	}
	
	public JTabbedPaneCloseButton getTabbedPane() {
		return tabbedPane;
	}
	
	public Palette getPalette() {
		return palette;
	}
	
	public Page getLastSelectedPage() {
		return lastSelectedPage;
	}
	
	public void setLastSelectedPage(Page page) {
		lastSelectedPage = page;
	}
	
	public ActionManager getActionManager() {
		return actionManager;
	}
	
	public SlotActionManager getSlotHandler() {
		return slotAction;
	}
	
	public PageSelectionModel getPageSelectionModel() {
		return pageSelectionModel;
	}

	public SlotPainter getLastSelectedSlot() {
		return lastSelectedSlot;
	}

	public void setLastSelectedSlot(SlotPainter lastSelectedSlot) {
		this.lastSelectedSlot = lastSelectedSlot;
	}

	public PageView getLastSelectedPageView() {
		return lastSelectedPageView;
	}

	public void setLastSelectedPageView(PageView lastSelectedPageView) {
		this.lastSelectedPageView = lastSelectedPageView;
		setLastSelectedPage(lastSelectedPageView.getPage());
	}
}
