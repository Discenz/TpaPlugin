package me.discens.tpaplugin.commands;

import me.discens.tpaplugin.TpaPlugin;
import me.discens.tpaplugin.api.TpaRequest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
            sender.sendMessage(ChatColor.RED + "Error: Must be a player to use this command.");
            return true;
        }


        TpaRequest request = plugin.getRequest((Player) sender);
        Player recipient = Bukkit.getPlayer(request.getSender().getName());

        if(request == null){
            sender.sendMessage(ChatColor.GRAY + "You do not have any existing requests.");
            return true;
        }

        sender.sendMessage(ChatColor.GRAY + "Request from " + ChatColor.YELLOW + recipient.getName() + ChatColor.GRAY + " denied.");
        recipient.sendMessage(ChatColor.GRAY + "Your request to " + ChatColor.YELLOW + sender.getName() + ChatColor.GRAY + " was denied.");

        plugin.removeRequest((Player) sender);

        return true;
    }

}