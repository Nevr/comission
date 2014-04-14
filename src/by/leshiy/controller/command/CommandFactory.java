package by.leshiy.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.leshiy.controller.command.Impl.Logout;

public class CommandFactory {

    private CommandFactory instance;
    private Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
	commands.put("logout", new Logout());
    }

    public CommandFactory getInstance() {
	if (instance == null)
	    return instance = new CommandFactory();
	else
	    return instance;
    }

    public Command getCommand(String s) {
	return commands.get(s);
    }

    public void setCommands(Map<String, Command> commands) {
	this.commands = commands;
    }
}
