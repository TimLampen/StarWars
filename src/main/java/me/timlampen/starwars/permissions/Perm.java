package me.timlampen.starwars.permissions;

import org.bukkit.permissions.Permissible;

/**
 * Created by Timothy Lampen on 2016-12-30.
 * Copyright © 2016 Tim Plugins
 * Under no circumstances are you allowed to edit, copy, remove, or tamper with this file
 * unless given direct permission by myself.
 * If you have any problems or issues contact me at timplugins@gmail.com//
 */

// To add a permission, simply add a line. The permission will be lowercase, replacing '_' with '.'.
public enum Perm {
    GLOBAL_ALL,

    KEY_GIVE,
    KEY_CHEST_GENERATE,

    DEATHBAN_COMMANDS_DEATHBAN,
    DEATHBAN_COMMANDS_LIVES,
    DEATHBAN_COMMANDS_LIVES_CHECK,
    DEATHBAN_COMMANDS_LIVES_GIVE,
    DEATHBAN_COMMANDS_LIVES_REVIVE,
    DEATHBAN_COMMANDS_LIVES_ADD,
    DEATHBAN_COMMANDS_LIVES_SET,
    DEATHBAN_COMMANDS_DB_REVIVE,
    DEATHBAN_COMMANDS_DB,
    DEATHBAN_COMMANDS_DB_CHECK,

    STAFF_REPORT,
    STAFF_REPORT_GET,
    STAFF_REPORT_CLEAR,
    STAFF_WARN,
    STAFF_WARN_GET,
    STAFF_WARN_CLEAR,
    STAFF_CHAT_ALL,
    STAFF_ALERTS,
    STAFF_OWNER,
    STAFF_ADMIN,
    STAFF_MOD,
    STAFF_HELPER,
    STAFF_LIST,
    STAFF_STAFFMODE,

    WRENCH_COMMANDS_GIVE,

    ENDERCHEST_TOGGLE,

    SHOP_CREATE,

    COMBATLOG_COMMAND_LOGOUT;



    public String getID(){
        return this.toString().toLowerCase().replaceAll("_", ".");
    }

    public boolean has(Permissible p){
        return PermChecker.has(p, this);
    }

}
