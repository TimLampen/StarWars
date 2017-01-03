package me.timlampen.starwars.jetpack.listeners;

import me.timlampen.starwars.jetpack.JetpackModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Timothy Lampen on 2017-01-01.
 * Copyright © 2017 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class RefuelListener implements Listener{

    @EventHandler
    public void onRefuel(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked();
        if(event.getClickedInventory()!=null && event.getCurrentItem()!=null && event.getCursor()!=null){
            ItemStack is = event.getCurrentItem();
            ItemStack fuel = event.getCursor();
            player.sendMessage("");
            if(JetpackModule.isJetpack(is) && JetpackModule.isFuel(fuel)){
                event.setCancelled(true);
                if(JetpackModule.getJetpackFuel(is)>=30){
                    return;
                }
                if(fuel.getAmount()>1){
                    fuel.setAmount(fuel.getAmount()-1);
                    player.setItemOnCursor(fuel);
                }
                else{
                    player.setItemOnCursor(null);
                }

                is = JetpackModule.setJetpackFuel(is, 30);
                event.setCurrentItem(is);
                player.updateInventory();
            }
        }
    }
}
