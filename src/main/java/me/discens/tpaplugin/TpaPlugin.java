package me.discens.tpaplugin;

import me.discens.tpaplugin.api.TpaRequest;
import me.discens.tpaplugin.api.Type;
import me.discens.tpaplugin.commands.TpaCommand;
import me.discens.tpaplugin.commands.TpacceptCommand;
import me.discens.tpaplugin.commands.TpahereCommand;
import me.discens.tpaplugin.commands.TpdenyCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class TpaPlugin extends JavaPlugin {

    private ArrayList<TpaRequest> requests = new ArrayList<TpaRequest>();

    @Override
    public void onEnable() {
        this.getCommand("tpa").setExecutor(new TpaCommand(this));
        this.getCommand("tpaccept").setExecutor(new TpacceptCommand(this));
        this.getCommand("tpahere").setExecutor(new TpahereCommand(this));
        this.getCommand("tpdeny").setExecutor(new TpdenyCommand(this));
    }

    public ArrayList<TpaRequest> getRequests () {
        return requests;
    }

    public TpaRequest getRequest (Player user) {
        for (TpaRequest request: requests){
            if (request.getReciever().getName().equalsIgnoreCase(user.getName())) {
                return request;
            }
        }
        return null;
    }

    public void addRequest (Player sender, Player receiver, Type type) {
        TpaRequest tpaRequest = new TpaRequest(sender, receiver, type);
        requests.add(tpaRequest);
    }

    public void removeRequest (Player user) {
        requests.remove(getRequest(user));
    }

}
