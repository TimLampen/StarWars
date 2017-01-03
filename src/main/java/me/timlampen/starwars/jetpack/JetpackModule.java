package me.timlampen.starwars.jetpack;

import me.timlampen.starwars.StarWars;
import me.timlampen.starwars.jetpack.commands.JetpackCommand;
import me.timlampen.starwars.jetpack.listeners.FallListener;
import me.timlampen.starwars.jetpack.listeners.RefuelListener;
import me.timlampen.starwars.jetpack.listeners.ToggleShiftListener;
import me.timlampen.starwars.module.Module;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timothy Lampen on 2017-01-01.
 * Copyright © 2017 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class JetpackModule extends Module{

    private static JetpackModule instance;

    public JetpackModule(){
        instance = this;
    }

    @Override
    public void onLoad(){

    }

    @Override
    public void onEnable(){
        registerListeners(new FallListener(), new ToggleShiftListener(), new RefuelListener());
        StarWars.getInstance().getCommand("jetpack").setExecutor(new JetpackCommand());
    }

    @Override
    public void onDisable(){

    }

    public static ItemStack generateJetpack(int fuel){
        ItemStack is = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Jetpack");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Jetpack Fuel:");
        lore.add(ChatColor.GOLD + "" + fuel + "" + ChatColor.GRAY + "/" + ChatColor.GOLD + "30");
        im.setLore(lore);
        is.setItemMeta(im);

        return is;
    }

    public static boolean isJetpack(ItemStack is){
        return is!=null && is.hasItemMeta() && is.getItemMeta().hasLore() && is.getItemMeta().getLore().get(0).equals(ChatColor.GRAY + "Jetpack Fuel:");
    }

    public static ItemStack setJetpackFuel(ItemStack is, int amt){
        ItemMeta im = is.getItemMeta();
        if(isJetpack(is)){
            List<String> lore = im.getLore();
            lore.set(1, ChatColor.GOLD + "" + amt + "" + ChatColor.GRAY + "/" + ChatColor.GOLD + "30");
            im.setLore(lore);
            is.setItemMeta(im);
            return is;
        }
        return null;
    }

    public static int getJetpackFuel(ItemStack is){
        if(isJetpack(is)){
            String fuelString = is.getItemMeta().getLore().get(1);
            fuelString = ChatColor.stripColor(fuelString);
            return Integer.parseInt(fuelString.split("/")[0]);
        }
        return 0;
    }

    public static JetpackModule getInstance(){
        return instance;
    }

    public static ItemStack generateFuel(int amt){
        ItemStack is = new ItemStack(Material.COAL, amt);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.RED + "Jetpack Fuel");
        is.setItemMeta(im);

        return is;
    }

    public static boolean isFuel(ItemStack is){
        return is!=null && is.hasItemMeta() && is.getItemMeta().hasDisplayName() && is.getItemMeta().getDisplayName().equals(ChatColor.RED + "Jetpack Fuel");
    }
}
