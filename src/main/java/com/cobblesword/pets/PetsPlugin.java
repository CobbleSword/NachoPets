package com.cobblesword.pets;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PetsPlugin extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onShift(PlayerToggleSneakEvent e)
            {
                Player player = e.getPlayer();
                Location location = player.getLocation();

                Pig pig = location.getWorld().spawn(location, Pig.class);
                Bukkit.getScheduler().scheduleSyncRepeatingTask(PetsPlugin.this, () -> {
                    ((EntityInsentient) ((CraftEntity) pig).getHandle()).getNavigation().a(location.getX(), location.getY(), location.getZ(), 1f);
                }, 20L, 20L);
            }
        }, this);
    }

    @Override
    public void onDisable()
    {

    }
}
