package me.timlampen.starwars.forcepowers;

import me.timlampen.starwars.StarWars;
import me.timlampen.starwars.forcepowers.commands.PowerCommand;
import me.timlampen.starwars.forcepowers.listeners.EntityDamageListener;
import me.timlampen.starwars.forcepowers.listeners.PlayerInteractListener;
import me.timlampen.starwars.forcepowers.powers.*;
import me.timlampen.starwars.module.Module;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class PowerModule extends Module{

    private static PowerModule instance;
    private HashMap<String, ForcePower> powers = new HashMap<>();

    public PowerModule(){
        instance = this;

        powers.put("forcepush", new ForcePush());
        powers.put("forcepull", new ForcePull());
        powers.put("forcechoke", new ForceChoke());
        powers.put("forcefreeze", new ForceFreeze());
        powers.put("forcelightning", new ForceLightning());
        powers.put("forceshield", new ForceShield());
        powers.put("forcetrick", new ForceTrick());
    }

    @Override
    public void onLoad(){

    }

    @Override
    public void onEnable(){
        registerListeners(new EntityDamageListener(), new PlayerInteractListener());
        StarWars.getInstance().getCommand("power").setExecutor(new PowerCommand());
    }

    @Override
    public void onDisable(){

    }

    public static PowerModule getInstance(){
        return instance;
    }

    public ForcePower getForcePower(String id){
        return powers.containsKey(id) ? powers.get(id) : null;
    }


}
