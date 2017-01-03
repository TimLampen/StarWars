package me.timlampen.starwars.forcepowers.powers;

import de.slikey.effectlib.effect.LineEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;
import me.timlampen.starwars.StarWars;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Timothy Lampen on 2016-12-31.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

public class ForceChoke extends ForcePower{
    private HashMap<UUID, Integer> forceChoke = new HashMap<>();
    @Override
    public void onInteract(Player player){
        findSelectedPlayer(player);
    }

    @Override
    public int getCooldownTime(){
        return 180;
    }

    @Override
    public String getName(){
        return "Force Choke";
    }

    @Override
    public short getPaneColor(){
        return 5;
    }

    public void onCallback(final Player player, final Player target){
        Horse horse = target.getWorld().spawn(target.getLocation(), Horse.class);
        horse.setInvulnerable(true);
        horse.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
        horse.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 10));
        horse.setTamed(true);
        horse.setAI(false);
        horse.setPassenger(target);

        LineEffect effect = new LineEffect(StarWars.getInstance().getEffectManager());
        effect.particle = ParticleEffect.FLAME;
        effect.setDynamicOrigin(new DynamicLocation(player));
        effect.setDynamicTarget(new DynamicLocation(target));
        effect.start();


        new BukkitRunnable(){
            @Override
            public void run(){
                if(forceChoke.containsKey(target.getUniqueId()) && forceChoke.get(target.getUniqueId())>=15){
                    cancel();
                    return;
                }
                if(target.isOnline()){
                    target.damage(1, player);
/*                    target.setHealth(!target.isDead() ? target.getHealth() - 1 : 0);
                    target.playEffect(EntityEffect.HURT);
                    target.getWorld().playSound(target.getLocation(), Sound.ENTITY_PLAYER_HURT, 10, 10);
                   */
                }
                forceChoke.put(target.getUniqueId(), forceChoke.containsKey(target.getUniqueId()) ? forceChoke.get(target.getUniqueId())+1 : 1);
            }
        }.runTaskTimer(StarWars.getInstance(), 0, 20);
    }
}
