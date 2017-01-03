package me.timlampen.starwars.forcepowers.powers;

import org.bukkit.Sound;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;

/**
 * Created by Timothy Lampen on 2016-12-31.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class ForceLightning extends ForcePower{
    @Override
    public void onInteract(Player player){
        findSelectedPlayer(player);
    }

    @Override
    public int getCooldownTime(){
        return 60;
    }

    @Override
    public String getName(){
        return "Force Lightning";
    }

    @Override
    public short getPaneColor(){
        return 4;
    }

    public void onCallback(Player player, Player target){
        if(player.getLocation().distance(target.getLocation())<=75){
            player.getWorld().strikeLightningEffect(target.getLocation());
            target.setHealth(target.getHealth()<5 ? 0 : target.getHealth()-5);
            target.getWorld().playSound(target.getLocation(), Sound.ENTITY_PLAYER_HURT, 10, 10);
        }
    }
}
