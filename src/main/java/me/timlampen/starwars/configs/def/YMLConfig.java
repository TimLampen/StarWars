package me.timlampen.starwars.configs.def;


import me.timlampen.starwars.StarWars;
import me.timlampen.starwars.util.logger.LColor;
import me.timlampen.starwars.util.logger.Logger;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */
public class YMLConfig {

    private final File file;
    private FileConfiguration config;

    private final boolean loadFromFile;

    public YMLConfig(File file, boolean loadFromFile) {
        this.file = file;
        this.loadFromFile = loadFromFile;

        setup();
    }

    private void setup(){
        if(!StarWars.getInstance().getDataFolder().exists())
            StarWars.getInstance().getDataFolder().mkdirs();

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Logger.debug(LColor.GREEN + "Created config file " + file.getName());
        }

        load();
        save();

        if(loadFromFile) {
            loadFromFile();
            save();

            Logger.debug(LColor.GREEN + "Loaded " + file.getName() + " with its default values if not set.");
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    protected void loadFromFile(){
        FileConfiguration temporary = YamlConfiguration.loadConfiguration(StarWars.getInstance().getResource(file.getName()));

        for(String key: temporary.getKeys(true)){
            if(!config.isSet(key)){
                Object obj = temporary.get(key);

                if(obj instanceof MemorySection)
                    continue;

                config.set(key, obj);
                Logger.debug("[" + file.getName() + "] Set Default Value for '" + key + "' to '" + obj + "'");
            }
        }
    }

    public void save(){
        try {
            config.save(file);
        } catch (IOException e) {
            Logger.error("An error occured while saving config: " + file.getName());
            e.printStackTrace();
        }
    }

    private void load(){
        config = YamlConfiguration.loadConfiguration(file);
        postLoad();
    }

    /**
     * Override me! :)
     */
    public void postLoad(){}

}