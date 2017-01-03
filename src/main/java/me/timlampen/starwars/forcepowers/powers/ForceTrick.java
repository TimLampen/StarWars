package me.timlampen.starwars.forcepowers.powers;

import de.slikey.effectlib.effect.FlameEffect;
import me.timlampen.starwars.StarWars;
import org.bukkit.Color;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Timothy Lampen on 2016-12-31.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class ForceTrick extends ForcePower{

    private HashMap<UUID, ArrayList<UUID>> invinPlayers = new HashMap<>();

    @Override
    public void onInteract(final Player player){
        FlameEffect effect = new FlameEffect(StarWars.getInstance().getEffectManager());
        effect.iterations = 20*15;
        effect.setEntity(player);
        effect.start();

        ArrayList<UUID> players = invinPlayers.containsKey(player.getUniqueId()) ? invinPlayers.get(player.getUniqueId()) : new ArrayList<UUID>();
        for(Entity e : player.getNearbyEntities(10, 10, 10)){
            if(e instanceof Player){
                Player target = (Player)e;
                players.add(target.getUniqueId());
                invinPlayers.put(player.getUniqueId(),players);
            }
        }

        new BukkitRunnable(){
            @Override
            public void run(){
                invinPlayers.remove(player.getUniqueId());
            }
        }.runTaskLater(StarWars.getInstance(), 20*15);
    }

    @Override
    public int getCooldownTime(){
        return 180;
    }

    @Override
    public String getName(){
        return "Force Trick";
    }

    @Override
    public short getPaneColor(){
        return 3;
    }

    public ArrayList<UUID> getInvinPlayers(UUID uuid){
        return invinPlayers.containsKey(uuid) ? invinPlayers.get(uuid) : null;
    }
}
