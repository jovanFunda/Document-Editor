package gui.swing;

import commands.MyCommand;
import exceptions.MyError;
import gui.swing.view.MainFrame;
import main.core.Gui;
import main.core.Repository;

public class SwingGUI implements Gui {

	private MainFrame instance;
	private Repository documentRepository;
	
	public SwingGUI(Repository documentRepository) {
		this.documentRepository = documentRepository;
	}

	@Override
	public void start() {
		instance = MainFrame.getInstance();
		instance.setDocumentRepository(documentRepository);
		instance.initialiseWorkspaceTree();
		instance.setVisible(true);
	}

	@Override
	public void update(Object notification) {
		
		if(notification instanceof MyError) {
			MainFrame.getInstance().showError((MyError)notification);
		}
		
		if(notification instanceof MyCommand) {
			MainFrame.getInstance().getActionManager().changeProperties((MyCommand)notification);
		}
	}
}
