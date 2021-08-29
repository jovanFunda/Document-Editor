package commands;

public interface AbstractCommand {
	
	public abstract void doCommand();
	
	public abstract void undoCommand();

}
