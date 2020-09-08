package me.discens.tpaplugin.commands;

import me.discens.tpaplugin.TpaPlugin;
import me.discens.tpaplugin.api.TpaRequest;
import me.discens.tpaplugin.api.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpacceptCommand implements CommandExecutor {
    private TpaPlugin plugin;

    public TpacceptCommand(TpaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Error: Must be a player to use this command.");
            return true;
        }

        Player user = (Player) sender;

        if(plugin.getRequest(user) == null){
            sender.sendMessage(ChatColor.GRAY + "You do not have any existing requests.");
            return true;
        }

        TpaRequest request = plugin.getRequest(user);

        Player recipient = Bukkit.getPlayer(request.getSender().getName());

        if (recipient == null){
            sender.sendMessage(ChatColor.YELLOW + recipient.getName() + ChatColor.GRAY + " is not online anymore.");
            return true;
        }

        if(request.getType() == Type.TPA) {
            sender.sendMessage(ChatColor.GRAY + "Teleporting " + ChatColor.YELLOW + recipient.getName() + ChatColor.GRAY + "...");
            recipient.sendMessage(ChatColor.GRAY + "Teleporting...");
            recipient.teleport(user);
        }
        else {
            sender.sendMessage(ChatColor.GRAY + "Teleporting...");
            recipient.sendMessage(ChatColor.GRAY + "Teleporting " + ChatColor.YELLOW + sender.getName() + ChatColor.GRAY + "...");
            user.teleport(recipient);
        }

        plugin.removeRequest(user);
        return true;
    }

}