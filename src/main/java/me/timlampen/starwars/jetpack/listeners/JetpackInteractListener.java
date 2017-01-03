package me.timlampen.starwars.jetpack.listeners;

import me.timlampen.starwars.jetpack.JetpackModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Timothy Lampen on 2017-01-01.
 * Copyright © 2017 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class JetpackInteractListener implements Listener{

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getAction().toString().contains("RIGHT")){
            if(event.getItem()!=null && JetpackModule.isJetpack(event.getItem())){
                ItemStack is = event.getItem();
                event.setCancelled(true);
                if(JetpackModule.getJetpackFuel(is)>0){
                  //  JetpackModule.setJetpackFuel(is, )
                }
            }
        }
    }
}
