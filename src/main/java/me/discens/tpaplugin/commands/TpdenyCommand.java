package me.discens.tpaplugin.commands;

import me.discens.tpaplugin.TpaPlugin;
import me.discens.tpaplugin.api.TpaRequest;
import me.discens.tpaplugin.api.Type;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpdenyCommand implements CommandExecutor {
    private TpaPlugin plugin;

    public TpdenyCommand(TpaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Error: Must be a player to use this command.");
            return true;
        }

        Player user = (Player) sender;

        if(plugin.getRequest(user) == null){
            sender.sendMessage("You do not have any existing requests");
            return true;
        }

        plugin.removeRequest(user);

        return true;
    }

}