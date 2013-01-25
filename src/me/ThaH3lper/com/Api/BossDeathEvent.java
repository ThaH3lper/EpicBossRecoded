package me.ThaH3lper.com.Api;

import me.ThaH3lper.com.Boss.Boss;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BossDeathEvent extends Event
{

    public static HandlerList getHandlerList()
    {
        return handlers;
    }

    private final Player player;
    private final Boss boss;

    private static final HandlerList handlers = new HandlerList();

    public BossDeathEvent(Player p, Boss b)
    {
        player = p;
        boss = b;
    }

    public String getBossName()
    {
        return boss.getName();
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public Player getPlayer()
    {
        return player;
    }
}
