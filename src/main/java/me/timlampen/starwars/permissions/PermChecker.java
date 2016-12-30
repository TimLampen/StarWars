package me.timlampen.starwars.permissions;

import org.bukkit.permissions.Permissible;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */
public class PermChecker {

    public static boolean has(Permissible p, Perm perm){
        return perm == null || perm == Perm.GLOBAL_ALL || p.hasPermission(perm.getID());
    }

    public static boolean has(Permissible p, String s) {
        return s == null || s.equals("") || p.hasPermission(s);
    }

}
