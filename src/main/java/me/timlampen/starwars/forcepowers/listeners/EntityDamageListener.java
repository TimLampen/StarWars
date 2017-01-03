package me.timlampen.starwars.forcepowers.listeners;

import me.timlampen.starwars.forcepowers.PowerModule;
import me.timlampen.starwars.forcepowers.powers.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class EntityDamageListener implements Listener{

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getDamager().hasMetadata("no-use")){
            event.setCancelled(true);
        }
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            Player damager = (Player)event.getDamager();
            Player defender = (Player)event.getEntity();
            ForceTrick trick = ((ForceTrick) PowerModule.getInstance().getForcePower("forcetrick"));
            if(trick.getInvinPlayers(defender.getUniqueId())!=null && trick.getInvinPlayers(defender.getUniqueId()).contains(damager.getUniqueId())){
                event.setCancelled(true);
            }
        }
        if(event.getEntity() instanceof Player && event.getDamager().getType()== EntityType.ARROW && ((Arrow)event.getDamager()).getShooter() instanceof Player && event.getDamager().hasMetadata("no-use")){
            Player damager = (Player)((Arrow)event.getDamager()).getShooter();
            Player defender = (Player)event.getEntity();
            String id = damager.getMetadata("no-use").get(0).asString();
            switch(id){
                case "Force Push":
                    ((ForcePush)PowerModule.getInstance().getForcePower(id)).onCallback(damager, defender);
                    break;
                case "Force Pull":
                    ((ForcePull)PowerModule.getInstance().getForcePower(id)).onCallback(damager, defender);
                    break;
                case "Force Lightning":
                    ((ForceLightning)PowerModule.getInstance().getForcePower(id)).onCallback(damager, defender);
                    break;
                case "Force Choke":
                    ((ForceChoke)PowerModule.getInstance().getForcePower(id)).onCallback(damager, defender);
                    break;
                case "Force Freeze":
                    ((ForceFreeze)PowerModule.getInstance().getForcePower(id)).onCallback(damager, defender);
            }
        }
    }
}
