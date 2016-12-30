package me.timlampen.starwars;

import me.timlampen.starwars.commands.BasicCommand;
import me.timlampen.starwars.commands.CommandManager;
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
    private final CommandManager commandManager;

    public StarWars(){
        commandManager = new CommandManager();
    }

    @Override
    public void onLoad(){
        loadModules();
    }

    @Override
    public void onEnable(){
        instance = this;
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

    public void registerCommand(BasicCommand cmd){
        getCommandManager().registerCommand(cmd);
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public boolean isDebugEnabled(){
        return true;
    }


}
