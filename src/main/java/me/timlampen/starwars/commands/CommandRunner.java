package me.timlampen.starwars.commands;

import me.timlampen.starwars.StarWars;
import me.timlampen.starwars.lang.Lang;
import me.timlampen.starwars.permissions.PermChecker;
import me.timlampen.starwars.util.logger.Logger;
import me.timlampen.starwars.util.numbers.IntegerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */
public class CommandRunner implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Logger.debug(sender.getName() + " sent a request for " + command.getName());

        List<BasicCommand> commands = StarWars.getInstance().getCommandManager().getCommands();

        for (BasicCommand cmd : commands) {
            if (cmd.isValidCall(command.getName())) {
                Logger.debug("Found matching command for " + cmd.getName() + ", as request by " + sender.getName());
                ArrayList<String> argList = new ArrayList<>();
                argList.addAll(Arrays.asList(args));
                findAndExecuteCommand(cmd, sender, argList);
                return true;
            }
        }

        return true;
    }

    private void findAndExecuteCommand(BasicCommand base, CommandSender sender, ArrayList<String> args){
        if(!PermChecker.has(sender, base.getPermission())){
            sender.sendMessage(Lang.COMMAND_NO_PERM);
            return;
        }

        if(args.size() == 0){
            Logger.debug("Running command " + base.getName() + " for " + sender.getName());

            if(!base.run(sender, args))
                HelpGenerator.sendHelp(sender, base, 1);
        }else{
            BasicCommand potentialChild = getMatchingChild(base, args.get(0));

            if(potentialChild == null){
                if(args.get(0).equalsIgnoreCase("help") || args.get(0).equalsIgnoreCase("?")){
                    int page;

                    if(args.size() >= 2){
                        page = IntegerUtil.getInt(args.get(1), 1);
                    }else{
                        page = 1;
                    }

                    HelpGenerator.sendHelp(sender, base, page);
                }else if(!base.run(sender, args)){
                    HelpGenerator.sendHelp(sender, base, 1);
                }
            }else{
                Logger.debug("Found child " + potentialChild.getName() + " for command " + base.getName());
                args.remove(0);

                findAndExecuteCommand(potentialChild, sender, args);
            }
        }
    }

    private BasicCommand getMatchingChild(BasicCommand cmd, String childName){
        if(cmd.getChildCommands() == null || cmd.getChildCommands().isEmpty())
            return null;

        List<BasicCommand> childs = cmd.getChildCommands();
        for(BasicCommand child: childs)
            if(child.isValidCall(childName))
                return child;

        return null;
    }

}