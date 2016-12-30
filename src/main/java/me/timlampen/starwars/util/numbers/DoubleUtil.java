package me.timlampen.starwars.util.numbers;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */
public class DoubleUtil {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean isDouble(String s){
        try{
            Double.parseDouble(s);
            return true;
        }catch (NumberFormatException | NullPointerException e){
            return false;
        }
    }

}