package commands;

import java.util.ArrayList;

import model.observer.ISubject;

public interface ICommandManager extends ISubject {
	
	void generateCommand(CommandType type);
	void doCommand();
	void undoCommand();
	void addCommand(ArrayList<AbstractCommand> lista);

}