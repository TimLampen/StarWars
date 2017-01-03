package me.timlampen.starwars.jetpack.listeners;

import me.timlampen.starwars.jetpack.JetpackModule;
import me.timlampen.starwars.lang.Lang;
import me.timlampen.starwars.util.CooldownUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Created by Timothy Lampen on 2017-01-01.
 * Copyright © 2017 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class ToggleShiftListener implements Listener{

    @EventHandler
    public void onShift(PlayerToggleSneakEvent event){
        Player player = event.getPlayer();
        if(event.isSneaking()){
            if(player.getInventory().getChestplate()!=null && JetpackModule.isJetpack(player.getInventory().getChestplate())){

                if(!CooldownUtil.isOnCooldown(player.getUniqueId(), "jetpack")){
                    CooldownUtil.addCooldown(player.getUniqueId(), "jetpack", 30);
                    ItemStack chestplate = player.getInventory().getChestplate();
                    int fuel = JetpackModule.getJetpackFuel(chestplate);

                    if(fuel > 0){
                        JetpackModule.setJetpackFuel(chestplate, JetpackModule.getJetpackFuel(chestplate));
                        player.updateInventory();

                        player.setVelocity(player.getVelocity().add(new Vector(0, 3.1, 0)));
                    }
                }
                else{
                    player.sendMessage(Lang.JETPACK_ON_COOLDOWN.replaceAll("%COOLDOWN%", CooldownUtil.getCooldown(player.getUniqueId(), "jetpack")));
                }
            }
        }
    }
}
