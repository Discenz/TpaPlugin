package me.discens.tpaplugin;

import me.discens.tpaplugin.api.TpaRequest;
import me.discens.tpaplugin.api.Type;
import me.discens.tpaplugin.commands.TpaCommand;
import me.discens.tpaplugin.commands.TpacceptCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class TpaPlugin extends JavaPlugin {

    private ArrayList<TpaRequest> requests = new ArrayList<TpaRequest>();

    @Override
    public void onEnable() {
        this.getCommand("tpa").setExecutor(new TpaCommand(this));
        this.getCommand("tpaccept").setExecutor(new TpacceptCommand(this));
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
        int i = 0;
        for (TpaRequest request: requests){
            if (request.getReciever().getName().equalsIgnoreCase(user.getName())) {
                requests.remove(i);
            }
            i++;
        }
    }

}
