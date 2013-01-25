package me.ThaH3lper.com.Damage;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.External.DiabloDropsHelper;

import org.bukkit.inventory.ItemStack;

public class DamageMethods
{
    private final EpicBoss eb;

    public DamageMethods(EpicBoss boss)
    {
        eb = boss;
    }

    public void deathBoss(Boss b)
    {
        eb.timerstuff.Death(b);
        if (b.getItems() != null)
        {
            for (String s : b.getItems())
            {
                if (s.contains("DiabloDrops:"))
                {
                    ItemStack is = DiabloDropsHelper.getDiabloDropsCustomItem(s
                            .replace("DiabloDrops:", ""));
                    eb.dropitems.dropItems(is, 1.0F, b.getLocation(),
                            DiabloDropsHelper.getDiabloDrops().getItemAPI()
                                    .getName(is));
                }
                else
                {
                    eb.dropitems.dropItems(eb.loaditems.getItem(s),
                            eb.loaditems.getItemChance(s), b.getLocation(),
                            eb.loaditems.getDisplayName(s));
                }
            }
        }
        if (b.getLivingEntity() != null)
        {
            b.getLivingEntity().remove();
        }
        eb.BossList.remove(b);
    }
}
