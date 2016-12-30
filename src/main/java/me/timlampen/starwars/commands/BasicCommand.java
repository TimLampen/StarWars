package me.timlampen.starwars.commands;

import me.timlampen.starwars.commands.args.ArgInfo;
import me.timlampen.starwars.commands.args.ArgType;
import me.timlampen.starwars.lang.Lang;
import me.timlampen.starwars.permissions.Perm;
import me.timlampen.starwars.permissions.PermChecker;
import me.timlampen.starwars.util.logger.LColor;
import me.timlampen.starwars.util.logger.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */
public abstract class BasicCommand{

    // SETUP
    private String name = "";
    private String description = "No Description Set";
    private final List<String> aliases = new ArrayList<>();
    private final List<BasicCommand> childCommands = new ArrayList<>();
    private final HashMap<Integer, ArgInfo> arguments = new HashMap<>();
    private Perm permission = Perm.GLOBAL_ALL;
    private Class senderType = CommandSender.class;

    /**
     * Constructor for a BasicCommand
     *
     * @param name The name of the command. This is used when executing the command.
     */
    public BasicCommand(String name) {
        setName(name);
    }

    final boolean run(CommandSender sender, List<String> args) {
        if (!(senderType.isInstance(sender))) {
            sender.sendMessage(Lang.COMMAND_INVALID_SENDER.replaceAll("%TYPE%", senderType.getName()));
            return false;
        }

        if (!PermChecker.has(sender, getPermission())) {
            sender.sendMessage(Lang.COMMAND_NO_PERM);
            return false;
        }

        for (int i : getArguments().keySet()) {
            ArgInfo argInfo = getArguments().get(i);

            if (args.size() > i) {
                if (argInfo.getType() == ArgType.OPTIONAL) {
                    if (argInfo.getDefault() != null || !argInfo.getDefault().equals("")) {
                        args.set(i, argInfo.getDefault());
                    }

                    // Else is ignored, so that if the command wants to branch out the command in parts according to the args length, it can.
                    // For example, /gamemode <mode> [Player]. The plugin could branch out so that if args.length == 2, it can get a player.
                } else {
                    sender.sendMessage(Lang.COMMAND_ARGUMENTS_MISSING);
                    return false;
                }
            }

            if (argInfo.validate(args.get(i))) {
                sender.sendMessage(Lang.COMMAND_ARGUMENTS_INVALID.replaceAll("%ARG%", argInfo.getName()).replaceAll("%TYPE%", argInfo.formatRequirements()));
                return false;
            }
        }

        Logger.debug(LColor.GREEN + "Command '" + this.getName() + "' is valid. Executing...");
        execute(sender, args);
        return true;
    }

    protected void execute(CommandSender sender, List<String> args) {
        HelpGenerator.sendHelp(sender, this, 1);
    }

    private void setName(String name) {
        this.name = name;
    }

    protected final void setDescription(String description) {
        this.description = description;
    }

    protected final void setPermission(Perm permission) {
        this.permission = permission;
    }

    public final String getDescription() {
        return description;
    }

    public final String getName() {
        return name;
    }

    public final List<String> getAliases() {
        return aliases;
    }

    protected final void addAliases(String... aliases) {
        for (String s : aliases)
            this.aliases.add(s.toLowerCase());
    }

    public final List<BasicCommand> getChildCommands() {
        return childCommands;
    }

    protected final void addChild(BasicCommand command) {
        childCommands.add(command);
        Logger.debug("Registered child '" + command.getName() + "' for command " + this.getName());
    }

    private HashMap<Integer, ArgInfo> getArguments() {
        return arguments;
    }

    protected final int addArgument(ArgInfo arg) {
        int id = getNextArgumentId();

        arguments.put(id, arg);

        return id;
    }

    private int getNextArgumentId() {
        return arguments.size();
    }

    public final Perm getPermission() {
        return permission;
    }

    public final boolean isForcePlayerExecuter() {
        return senderType == Player.class;
    }

    /**
     * The c#ommand will only execute if the CommandSender is an instance of <code>senderType</code>
     *
     * @param senderType The instance of CommandSender that you are expecting
     */
    protected final void setSenderType(Class senderType) {
        this.senderType = senderType;
    }

    public final boolean isVisibleFor(CommandSender sender) {
        return !(senderType.isInstance(sender)) || PermChecker.has(sender, getPermission());
    }

    public final String getUsage() {
        if (description.equals("") && getArguments().isEmpty())
            return "empty";

        if (!description.equals("") && getArguments().isEmpty())
            return "description";

        String res = getName();

        if (getArguments() == null || getArguments().values().isEmpty())
            return res;

        for (int i = 0; i < getArguments().values().size(); i++)
            res += " " + getArguments().get(i).format();

        return res;
    }

    /**
     * @param call String sent by the CommandSender
     * @return True if call equalsIgnoreCase the name of the command, or one of the aliases.
     */
    public boolean isValidCall(String call) {
        return name.equalsIgnoreCase(call) || aliases.contains(call.toLowerCase());
    }

}