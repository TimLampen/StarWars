package me.timlampen.starwars.util;

import com.google.common.collect.HashBasedTable;

import java.text.DecimalFormat;
import java.util.UUID;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */
public class CooldownUtil{

    //make sure to not have generic names like 'cooldown' or 'time' maybe add a specifc identifier like STAFF_<cooldown name>
    private static HashBasedTable<UUID, String, Double> table = HashBasedTable.create();

    /**
     * @param uuid the uuid of the player on the cooldown
     * @param type the cooldown that the cooldown will be for
     * @param length the length of the cooldown (the time that it will take for the cooldown to complete) in seconds
     */
    public static void addCooldown(UUID uuid, String type, double length){
        table.put(uuid, type, System.currentTimeMillis()+(1000*length));
    }

    /**
     * @param uuid the uuid of the player that you are checking
     * @param type the type of thing the cooldown falls under
     * @return true if the player still has a cooldown.
     */
    public static boolean isOnCooldown(UUID uuid, String type){
        if(table.contains(uuid, type)){
            double expire = table.get(uuid, type);
            if(System.currentTimeMillis()>=expire){
                table.remove(uuid, type);
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * @param uuid the uuid of the player that has the cooldown
     * @param type the string identifier of the cooldown
     * @return a String formated #.## displaying the seconds remaining until expire
     */
    public static String getCooldown(UUID uuid, String type){
        DecimalFormat df = new DecimalFormat("#.#");
        double time = table.contains(uuid, type) ? table.get(uuid, type) : 0;
        time -= System.currentTimeMillis();
        time /= 1000;
        return df.format(time);
    }
}
