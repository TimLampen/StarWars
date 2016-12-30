package me.timlampen.starwars.configs;



import me.timlampen.starwars.StarWars;
import me.timlampen.starwars.configs.def.YMLClassConfig;

import java.io.File;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */
public class Config extends YMLClassConfig{

    public static boolean DEBUG = true;

    public static String MYSQL_HOST = "localhost";
    public static String MYSQL_DATABASE = "database";
    public static String MYSQL_USERNAME = "username";
    public static String MYSQL_PASSWORD = "password";

    public Config() {
        super(new File(StarWars.getInstance().getDataFolder() + "/config.yml"));
    }

}