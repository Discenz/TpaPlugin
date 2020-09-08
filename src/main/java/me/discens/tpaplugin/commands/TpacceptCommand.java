package me.discens.tpaplugin.commands;

import me.discens.tpaplugin.TpaPlugin;
import me.discens.tpaplugin.api.TpaRequest;
import me.discens.tpaplugin.api.Type;
import org.bukkit.Bukkit;
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
            sender.sendMessage("Error: Must be a player to use this command.");
            return true;
        }

        Player user = (Player) sender;

        if(plugin.getRequest(user) == null){
            sender.sendMessage("You do not have any existing requests");
            return true;
        }

        TpaRequest request = plugin.getRequest(user);

        Player recipient = Bukkit.getPlayer(request.getSender().getName());

        if(request.getType() == Type.TPA) recipient.teleport(user);
        else user.teleport(recipient);

        plugin.removeRequest(user);

        return true;
    }

}