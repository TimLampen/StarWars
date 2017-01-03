package me.timlampen.starwars.forcepowers.powers;

import me.timlampen.starwars.StarWars;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public abstract class ForcePower{

    public abstract void onInteract(Player player);

    public abstract int getCooldownTime();

    public abstract String getName();

    public abstract short getPaneColor();

    public ItemStack getItem(){
        ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1, getPaneColor());
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.DARK_BLUE + getName());
        im.addEnchant(Enchantment.DURABILITY, 1, false);

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Soulbound");
        im.setLore(lore);

        is.setItemMeta(im);

        return is;

    }

    public void findSelectedPlayer(Player player){
        final Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setMetadata("no-use", new FixedMetadataValue(StarWars.getInstance(), getName()));
        arrow.setGravity(false);
        arrow.setVelocity(arrow.getVelocity().multiply(10));

        new BukkitRunnable(){
            @Override
            public void run(){
                if(!arrow.isValid() || !arrow.isOnGround() || !arrow.isDead()){
                    arrow.remove();
                    cancel();
                }
            }
        }.runTaskTimer(StarWars.getInstance(), 0, 5);
    }


}
