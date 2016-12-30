package me.timlampen.starwars.commands.args;


import me.timlampen.starwars.util.StringUtil;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */
public enum ArgRequirement {
    INTEGER,
    DOUBLE,
    PLAYER,
    BOOLEAN,
    OFFLINE_PLAYER,
    UUID;

    public String getDisplayName(){
        return StringUtil.capitalizeFirst(this.toString().toLowerCase());
    }

}