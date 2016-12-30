package me.timlampen.starwars.util.logger;

import me.timlampen.starwars.StarWars;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */
public class Logger {

    private static final String ERR = LColor.RED + "[ERROR] ";
    private static final String DEBUG = LColor.CYAN + "[DEBUG] " + LColor.RESET;

    public static void log(Object msg){
        print("[Core] " + msg + LColor.RESET);
    }

    public static void debug(Object msg) {
        if (StarWars.getInstance().isDebugEnabled())
            print(DEBUG + "[Core] " + msg + LColor.RESET);
    }

    public static void error(Object msg){
        print(ERR + "[Core] " + msg + LColor.RESET);
    }

    public static void println(Object msg){
        System.out.println(msg);
    }

    public static void print(Object msg){
        System.out.print(msg);
    }

}
