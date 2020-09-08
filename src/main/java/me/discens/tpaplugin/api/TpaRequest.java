package me.discens.tpaplugin.api;

import org.bukkit.entity.Player;

public class TpaRequest {
    private Player sender, reciever;
    private Type type;

    public TpaRequest(Player sender, Player reciever, Type type) {
        this.sender = sender;
        this.reciever = reciever;
        this.type = type;
    }

    public Player getSender() {
        return sender;
    }

    public Player getReciever() {
        return reciever;
    }

    public Type getType() {
        return type;
    }
}
