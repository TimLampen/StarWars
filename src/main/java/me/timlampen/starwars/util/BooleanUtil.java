package me.timlampen.starwars.util;

/**
 * Created by Timothy Lampen on 11/15/2016.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com
 */
public class BooleanUtil {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean isBoolean(String s){
        if(s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false")){
            return true;
        }
        return false;
    }

}
