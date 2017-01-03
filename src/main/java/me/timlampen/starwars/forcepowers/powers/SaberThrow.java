package me.timlampen.starwars.forcepowers.powers;

import org.bukkit.entity.Player;

/**
 * Created by Timothy Lampen on 2017-01-01.
 * Copyright © 2017 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class SaberThrow extends ForcePower{
    @Override
    public void onInteract(Player player){

    }

    @Override
    public int getCooldownTime(){
        return 0;
    }

    @Override
    public String getName(){
        return "Saber Throw";
    }

    @Override
    public short getPaneColor(){
        return 10;
    }
}
