package commands;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.observer.IObserver;
import model.observer.ISubject;


public class CommandManager implements ISubject, ICommandManager, Serializable {
	
	private static final long serialVersionUID = 1L;

	private transient List<IObserver> observeri;
	
	private ArrayList<ArrayList<AbstractCommand>> commands = new ArrayList<ArrayList<AbstractCommand>>();
	private int currentCommand = 0;
	
	public CommandManager() {
		observeri = new ArrayList<IObserver>();
		
	}
	
	@Override
	public void addCommand(ArrayList<AbstractCommand> lista){
		while(currentCommand < commands.size())
			commands.remove(currentCommand);
		commands.add(lista);
		doCommand();
	}
	
	@Override
	public void doCommand(){
		// Ovde bismo preko MainFrame proveravali izabranu stranicu i listu njenih komandi
		if(currentCommand < commands.size()){
			for (AbstractCommand komanda : commands.get(currentCommand)) {
				komanda.doCommand();
			}
			currentCommand++;
			generateCommand(CommandType.undo_enable);
		}
		if(currentCommand==commands.size()){
			generateCommand(CommandType.redo_disable);
		}
	}

	@Override
	public void undoCommand(){
		// Ovde bismo preko MainFrame proveravali izabranu stranicu i listu njenih komandi
		if(currentCommand > 0){
			currentCommand--;
			for (AbstractCommand komanda : commands.get(currentCommand)) {
				komanda.undoCommand();
			}
			generateCommand(CommandType.redo_enable);
		}
		if(currentCommand==0){
			generateCommand(CommandType.undo_disable);
		}
	}

	@Override
	public void addSubscriber(IObserver obs) {
		if(obs == null)
			return;
		if(this.observeri == null)
			this.observeri = new ArrayList<>();
		if(this.observeri.contains(obs))
			return;
		this.observeri.add(obs);
	}

	@Override
	public void removeSubscriber(IObserver obs) {
		if(obs == null || this.observeri == null || !this.observeri.contains(obs))
			return;
		this.observeri.remove(obs);
	}

	@Override
	public void notifySubscribers(Object notification) {
		
		if(observeri == null) {
			observeri = new ArrayList<IObserver>();
		}
		
		for (IObserver obs : observeri) {
			obs.update((MyCommand)notification);
		}
	}

	@Override
	public void generateCommand(CommandType type) {
		if(type == CommandType.undo_disable) {
			notifySubscribers(new MyCommand("undo", "disable"));
		} else if(type == CommandType.undo_enable) {
			notifySubscribers(new MyCommand("undo", "enable"));
		} else if(type == CommandType.redo_disable) {
			notifySubscribers(new MyCommand("redo", "disable"));
		} else if(type == CommandType.redo_enable) {
			notifySubscribers(new MyCommand("redo", "enable"));
		}
	}
}