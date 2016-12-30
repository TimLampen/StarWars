package me.timlampen.starwars.lang;

import me.timlampen.starwars.StarWars;
import me.timlampen.starwars.configs.def.YMLClassConfig;
import me.timlampen.starwars.util.logger.Logger;
import net.md_5.bungee.api.ChatColor;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

// To add a Language entry, simply add a line and give it a default value.
// Do not modify String from others, unless for minor mistakes such as spelling.
public class Lang extends YMLClassConfig{

    /*COLORS FOR ARGUMENTS (like things you replace with .replace(%BLAWBLAW%, object)
    player = &6
    number = &b
    other = &6

    COLORS FOR CMDS
    successful cmd = &a
    not successful cmd = &c
    update / change  = &7
    */
    public static String

    PREFIX = "&dStarWars&8&l> ",

    // DEFAULTS FOR COMMANDS//
    COMMAND_NO_PERM = PREFIX + "&cYou are not allowed to run this command.",
    COMMAND_ARGUMENTS_MISSING = PREFIX + "&cYou are missing arguments.",
    COMMAND_ARGUMENTS_INVALID = PREFIX + "&cPlease use a &6%TYPE% &cfor argument &6%ARG%&7.",
    COMMAND_INVALID_SENDER = PREFIX +"&cYou must be a &6%TYPE% to be able to use this command.",
    COMMAND_HELP_NONE = PREFIX + "&cThere is no help &7for this page",
    COMMAND_HELP_NO_PERM = PREFIX + "&cYou are not allowed to view the help for this command.";

//
    public Lang(){
        super(new File(StarWars.getInstance().getDataFolder() + "/lang.yml"));
    }

    @Override
    protected void loadFromFile(){
        try {
            for (Field f : getClass().getDeclaredFields()) {
                if (!getConfig().isSet(toID(f.getName())))
                    getConfig().set(toID(f.getName()), f.get(getClass()));
                else
                    f.set(getClass(), ChatColor.translateAlternateColorCodes('&', getConfig().getString(toID(f.getName()))));
            }
        }catch (IllegalAccessException e){
            Logger.error("An error occured while loading the config.yml: ");
            e.printStackTrace();
        }
    }

}