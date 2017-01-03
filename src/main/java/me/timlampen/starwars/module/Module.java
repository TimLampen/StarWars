package me.timlampen.starwars.module;


import me.timlampen.starwars.StarWars;
import org.bukkit.event.Listener;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */
public abstract class Module{

    public abstract void onLoad();

    public abstract void onEnable();

    public abstract void onDisable();

    protected void registerListeners(Listener... listeners){
        StarWars.registerListeners(listeners);
    }



}