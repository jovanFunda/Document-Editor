package main;

import commands.CommandManager;
import commands.ICommandManager;
import exceptions.ErrorHandler;
import exceptions.IErrorHandler;
import gui.swing.SwingGUI;
import main.core.AppFramework;
import main.core.Gui;
import main.core.Repository;
import model.repository.RepositoryImplement;

public class Core extends AppFramework {
	
	private static Core instance;
	private static IErrorHandler errorHandler;
	private static Gui gui;
	private static Repository rep;
	
	public static Core getInstance() {
		
		if(instance == null) {
			instance = new Core();
		}
		
		return instance;
	}
	
	public void run() {
		gui.start();
	}
	
	public static void main(String[] args) {
		rep = new RepositoryImplement();
		gui = new SwingGUI(rep);
		errorHandler = new ErrorHandler();
		
		
		AppFramework core = Core.getInstance();
		core.init(gui, rep, errorHandler);
		core.run();
	}
	
	public IErrorHandler getErrorHandler() {
		return errorHandler;
	}
	
	public static Gui getGui() {
		return gui;
	}
	
	public ICommandManager newCommandManager() {
		return new CommandManager();
	}
	
	
	public static Repository getRep() {
		return rep;
	}

}