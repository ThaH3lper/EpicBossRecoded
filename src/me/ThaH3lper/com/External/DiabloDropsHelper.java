package me.ThaH3lper.com.External;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.modcrafting.diablodrops.DiabloDrops;

public class DiabloDropsHelper
{

    public static DiabloDrops getDiabloDrops()
    {
        Plugin plugin = Bukkit.getServer().getPluginManager()
                .getPlugin("DDChestRegen");

        if ((plugin == null) || !(plugin instanceof DiabloDrops))
            return null;

        return (DiabloDrops) plugin;
    }

    public static ItemStack getDiabloDropsCustomItem(String name)
    {
        DiabloDrops dd = getDiabloDrops();
        if (dd == null)
            return null;
        if (name.equalsIgnoreCase("RANDOM"))
            return dd.getDropAPI().getItem();
        ItemStack is = dd.getDropAPI().getCustomItem(name);
        if (is != null)
            return is;
        else
            return dd.getDropAPI().getItem();
    }

}
