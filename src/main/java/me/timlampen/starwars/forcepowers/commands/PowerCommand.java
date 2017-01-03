package me.timlampen.starwars.forcepowers.commands;

import me.timlampen.starwars.forcepowers.PowerModule;
import me.timlampen.starwars.forcepowers.powers.ForcePower;
import me.timlampen.starwars.lang.Lang;
import me.timlampen.starwars.permissions.Perm;
import me.timlampen.starwars.permissions.PermChecker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Timothy Lampen on 2016-12-31.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class PowerCommand implements CommandExecutor{



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args){
        if(sender instanceof Player){
            Player player = (Player)sender;
            if(PermChecker.has(player, Perm.POWER_COMMAND)){
                if(args.length==1){
                    ForcePower power = PowerModule.getInstance().getForcePower(args[0]);

                    if(power!=null){
                        player.getInventory().addItem(power.getItem());
                        player.sendMessage(Lang.COMMAND_ADD_ITEM_TO_INV.replaceAll("%AMT%", "1").replaceAll("%TYPE%", power.getName()).replaceAll("%INV%", "your"));
                    }
                    else{
                        player.sendMessage(Lang.FORCEPOWER_NO_POWER);
                    }
                }
                else{
                    player.sendMessage(Lang.COMMAND_ARGUMENTS_MISSING.replaceAll("%CMD%", "/power <power>"));
                }
            }
            else{
                player.sendMessage(Lang.COMMAND_NO_PERM);
            }
        }
        else{
            sender.sendMessage(Lang.COMMAND_INVALID_SENDER);
        }
        return true;
    }
}
