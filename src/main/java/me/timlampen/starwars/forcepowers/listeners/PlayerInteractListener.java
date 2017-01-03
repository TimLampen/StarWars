package me.timlampen.starwars.forcepowers.listeners;

import me.timlampen.starwars.forcepowers.PowerModule;
import me.timlampen.starwars.forcepowers.powers.ForcePower;
import me.timlampen.starwars.lang.Lang;
import me.timlampen.starwars.util.CooldownUtil;
import me.timlampen.starwars.util.logger.Logger;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

/**
 * Created by Timothy Lampen on 2016-12-31.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class PlayerInteractListener implements Listener{

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction().toString().contains("RIGHT")){
            Player player = event.getPlayer();
            ItemStack is = player.getInventory().getItemInMainHand();
            if(is!=null && is.getType()== Material.STAINED_GLASS_PANE && is.hasItemMeta() && is.getItemMeta().hasEnchant(Enchantment.DURABILITY)){
                String id = ChatColor.stripColor(is.getItemMeta().getDisplayName()).replaceAll(" ", "").toLowerCase();
                ForcePower power = PowerModule.getInstance().getForcePower(id);
                int cooldown = power.getCooldownTime();

                if(!CooldownUtil.isOnCooldown(player.getUniqueId(), id)){
                    power.onInteract(player);
                    CooldownUtil.addCooldown(player.getUniqueId(), id, cooldown);
                }
                else{
                    player.sendMessage(Lang.FORCEPOWER_COOLDOWN.replaceAll("%COOLDOWN%", CooldownUtil.getCooldown(player.getUniqueId(), id)));
                }
            }
        }
    }
}
