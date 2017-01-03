package me.timlampen.starwars.jetpack.listeners;

import me.timlampen.starwars.jetpack.JetpackModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by Timothy Lampen on 2017-01-01.
 * Copyright © 2017 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class FallListener implements Listener{

    @EventHandler
    public void onFall(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player)event.getEntity();
            if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
                if(player.getInventory().getChestplate()!=null && JetpackModule.isJetpack(player.getInventory().getChestplate())){
                    event.setCancelled(true);
                }
            }
        }
    }
}
