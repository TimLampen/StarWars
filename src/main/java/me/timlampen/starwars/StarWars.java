package me.timlampen.starwars;

import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;
import me.timlampen.starwars.forcepowers.PowerModule;
import me.timlampen.starwars.jetpack.JetpackModule;
import me.timlampen.starwars.lang.Lang;
import me.timlampen.starwars.module.Module;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class StarWars extends JavaPlugin{
    private Economy eco;
    private static StarWars instance;
    private final ArrayList<Module> modules = new ArrayList<>();
    private EffectManager effectManager;
    private Lang lang;

    public StarWars(){
        instance = this;
        registerModules(new PowerModule(), new JetpackModule());
    }

    @Override
    public void onLoad(){
        loadModules();
    }

    @Override
    public void onEnable(){
        effectManager = new EffectManager(EffectLib.instance());
        lang = new Lang();
        setupEconomy();
        enableModules();
    }

    @Override
    public void onDisable(){
        disableModules();
    }

    public static StarWars getInstance(){
        return instance;
    }

    private void loadModules(){
        for(Module module: modules){
            module.onLoad();
        }
    }

    private void enableModules(){
        for(Module module: modules){
            module.onEnable();
        }
    }

    private void disableModules(){
        for(Module module: modules){
            module.onDisable();
        }
    }

    private void registerModules(Module... modules){
        for(Module m : modules){
            this.modules.add(m);
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        eco = rsp.getProvider();
        return eco != null;
    }

    public static void registerListeners(Listener... listeners) {
        for (Listener l : listeners)
            StarWars.getInstance().getServer().getPluginManager().registerEvents(l, StarWars.getInstance());
    }


    public boolean isDebugEnabled(){
        return true;
    }

    public EffectManager getEffectManager(){
        return effectManager;
    }


}
