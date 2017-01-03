package me.timlampen.starwars.permissions;

import org.bukkit.permissions.Permissible;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

// To add a permission, simply add a line. The permission will be lowercase, replacing '_' with '.'.
public enum Perm {
    GLOBAL_ALL,

    POWER_COMMAND,
    JETPACK_COMMAND;



    public String getID(){
        return this.toString().toLowerCase().replaceAll("_", ".");
    }

    public boolean has(Permissible p){
        return PermChecker.has(p, this);
    }

}
