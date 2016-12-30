package me.timlampen.starwars.commands;

import me.timlampen.starwars.StarWars;
import me.timlampen.starwars.StarWars;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */
public class CommandManager {

    private List<BasicCommand> commands;
    private CommandRunner runner;

    public CommandManager() {
        commands = new ArrayList<>();
        runner = new CommandRunner();
    }

    public void registerCommand(BasicCommand command){
        StarWars.getInstance()
                .getCommand(
                        command.getName()
                ).setExecutor(
                        runner);
        commands.add(command);
    }

    public List<BasicCommand> getCommands() {
        return commands;
    }
}