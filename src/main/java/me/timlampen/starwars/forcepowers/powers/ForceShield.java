package me.timlampen.starwars.forcepowers.powers;

import de.slikey.effectlib.Effect;
import de.slikey.effectlib.effect.ShieldEffect;
import me.timlampen.starwars.StarWars;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Timothy Lampen on 2017-01-01.
 * Copyright © 2017 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class ForceShield extends ForcePower{
    private ArrayList<UUID> invincible = new ArrayList<>();
    @Override
    public void onInteract(final Player player){
        ShieldEffect effect = new ShieldEffect(StarWars.getInstance().getEffectManager());
        effect.setEntity(player);
        effect.particles = 5;
        effect.radius = 2;
        effect.sphere = true;
        effect.iterations = 20*30;
        effect.start();

        invincible.add(player.getUniqueId());

        new BukkitRunnable(){
            @Override
            public void run(){
                invincible.remove(player.getUniqueId());
            }
        }.runTaskLater(StarWars.getInstance(), 20*30);


    }

    @Override
    public int getCooldownTime(){
        return 300;
    }

    @Override
    public String getName(){
        return "Force Shield";
    }

    @Override
    public short getPaneColor(){
        return 6;
    }
}
