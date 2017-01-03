package me.timlampen.starwars.jetpack.commands;

import me.timlampen.starwars.jetpack.JetpackModule;
import me.timlampen.starwars.lang.Lang;
import me.timlampen.starwars.permissions.Perm;
import me.timlampen.starwars.permissions.PermChecker;
import me.timlampen.starwars.util.numbers.IntegerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Timothy Lampen on 2017-01-01.
 * Copyright © 2017 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class JetpackCommand implements CommandExecutor{
    @Override//jetpack fuel/pack [fuel]
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        if(sender instanceof Player){
            Player player = (Player)sender;
            if(PermChecker.has(player, Perm.JETPACK_COMMAND)){
                if(args.length > 0){
                    int amt = args.length == 2 ? (IntegerUtil.isInteger(args[1]) ? Integer.parseInt(args[1]) : -1) : 99;
                    if(amt <= 0){
                        player.sendMessage(Lang.COMMAND_ARGUMENTS_INVALID.replaceAll("%TYPE%", "integer").replaceAll("%ARG%", "[amount of fuel]"));
                        return false;
                    }
                    if(args[0].equalsIgnoreCase("fuel")){
                        amt = (amt == 99 ? 1 : amt);
                        player.getInventory().addItem(JetpackModule.generateFuel(amt));
                        player.sendMessage(Lang.COMMAND_ADD_ITEM_TO_INV.replaceAll("%AMT%", amt + "").replaceAll("%TYPE%", "fuel").replaceAll("%INV%", "your"));

                    }else if(args[0].equalsIgnoreCase("pack")){
                        amt = amt == 99 ? 30 : amt;
                        player.getInventory().addItem(JetpackModule.generateJetpack(amt));
                        player.sendMessage(Lang.COMMAND_ADD_ITEM_TO_INV.replaceAll("%AMT%", amt + "").replaceAll("%TYPE%", "jetpack(s)").replaceAll("%INV%", "your"));
                    }else{
                        player.sendMessage(Lang.COMMAND_ARGUMENTS_MISSING.replaceAll("%CMD%", "/jetpack <fuel:pack> [amount of fuel]"));
                    }
                }
                else{
                    player.sendMessage(Lang.COMMAND_ARGUMENTS_MISSING.replaceAll("%CMD%", "/jetpack <fuel:pack> [amount of fuel]"));
                }
            }
            else{
                player.sendMessage(Lang.COMMAND_NO_PERM);
            }
        }
        else{
            sender.sendMessage(Lang.COMMAND_INVALID_SENDER);
        }
        return false;
    }
}
