package me.timlampen.starwars.configs.def;

import me.timlampen.starwars.util.logger.Logger;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */
public class YMLClassConfig extends YMLConfig {

    public YMLClassConfig(File file) {
        super(file, true);
    }

    @Override
    protected void loadFromFile(){
        try {
            for (Field f : getClass().getDeclaredFields()) {
                if (!getConfig().isSet(toID(f.getName())))
                    getConfig().set(toID(f.getName()), f.get(getClass())); // TODO check for wrappers
                else
                    f.set(getClass(), getConfig().get(toID(f.getName())));
            }
        }catch (IllegalAccessException e){
            Logger.error("An error occured while loading the config.yml: ");
            e.printStackTrace();
        }
    }

    public String toID(String in){
        return in.toLowerCase().replaceAll("_", ".");
    }

}