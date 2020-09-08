package me.discens.tpaplugin;

import me.discens.tpaplugin.commands.TpaCommand;
import me.discens.tpaplugin.commands.TpacceptCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class TpaPlugin extends JavaPlugin {

    private HashMap<String, String> requests;

    @Override
    public void onEnable() {
        this.getCommand("tpa").setExecutor(new TpaCommand(this));
        this.getCommand("tpaccept").setExecutor(new TpacceptCommand(this));
        requests = new HashMap<String, String>();
    }

    public HashMap<String, String> getRequests () {
        return requests;
    }

    public String getRequest (Player user) {
        return requests.get(user.getName());
    }

    public void addRequest (Player sender, Player receiver) {
        requests.put(receiver.getName(), sender.getName());
    }

    public void removeRequest (Player user) {
        requests.remove(user.getName());
    }

}
