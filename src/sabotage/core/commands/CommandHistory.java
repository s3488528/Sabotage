package sabotage.core.commands;

import java.util.Stack;

public class CommandHistory {
	
	private Stack<Command> undoStack;
	
	public CommandHistory() {
		undoStack = new Stack<Command>();
	}
	
	/***
	 * Executes a command and then stores it in the undo command stack
	 * @param command	The command to execute and store
	 */
	public void executeCommand(Command command) {
		command.execute();
		undoStack.push(command);
	}

	/***
	 * Pops the most recent item off the undo command stack and runs its undo function
	 */
	public void undoLast() {		
		Command command = undoStack.pop();
		command.undo();
	}
	
	/***
	 * Gets the count of commands in the undo stack
	 * @return Returns the count of commands in the undo stack as int
	 */
	public int getUndoStackCount() {
		return undoStack.size();
	}
	
}
