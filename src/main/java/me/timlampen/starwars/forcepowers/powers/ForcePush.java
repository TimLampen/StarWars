package me.timlampen.starwars.forcepowers.powers;

import jdk.nashorn.internal.codegen.CompilerConstants;
import me.timlampen.starwars.StarWars;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class ForcePush extends ForcePower{
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
        return "Force Push";
    }

    @Override
    public short getPaneColor(){
        return 1;
    }

    public void onCallback(Player player, Player target){
        if(player.getLocation().distance(target.getLocation())<=50){
            target.setVelocity(target.getVelocity().multiply(-1).add(new Vector(0, .75, 0)));
        }
    }


}
