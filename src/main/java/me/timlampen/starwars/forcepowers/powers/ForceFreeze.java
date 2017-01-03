package me.timlampen.starwars.forcepowers.powers;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Timothy Lampen on 2017-01-01.
 * Copyright © 2017 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class ForceFreeze extends ForcePower{
    @Override
    public void onInteract(Player player){
        findSelectedPlayer(player);
    }

    @Override
    public int getCooldownTime(){
        return 180;
    }

    @Override
    public String getName(){
        return "Force Freeze";
    }

    @Override
    public short getPaneColor(){
        return 8;
    }

    public void onCallback(Player player, Player target){
        if(player.getLocation().distance(target.getLocation())<=75){
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*30, Integer.MAX_VALUE));
        }
    }
}
