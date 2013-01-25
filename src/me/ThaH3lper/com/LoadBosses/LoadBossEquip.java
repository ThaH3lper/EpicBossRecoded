package me.ThaH3lper.com.LoadBosses;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Boss.Boss;
import me.ThaH3lper.com.External.DiabloDropsHelper;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class LoadBossEquip
{
    public EpicBoss eb;

    public LoadBossEquip(EpicBoss boss)
    {
        eb = boss;
    }

    public void equip(String s, ItemStack stack, LivingEntity l)
    {
        String[] parts = s.split(" ");
        String[] itemParts = parts[0].split(":");
        if (itemParts.length == 4)
        {
            EntityEquipment eq = l.getEquipment();

            if (itemParts[3].equals("0"))
                eq.setItemInHand(stack);

            if (itemParts[3].equals("1"))
                eq.setBoots(stack);

            if (itemParts[3].equals("2"))
                eq.setLeggings(stack);

            if (itemParts[3].equals("3"))
                eq.setChestplate(stack);

            if (itemParts[3].equals("4"))
                eq.setHelmet(stack);
        }
    }

    public void setEquipment(Boss b)
    {
        if (b.getItems() != null)
        {
            if (b.getLivingEntity() != null)
            {
                for (String s : b.getItems())
                {
                    if (s.contains("DiabloDrops:"))
                    {
                        ItemStack is = DiabloDropsHelper
                                .getDiabloDropsCustomItem(s.replace(
                                        "DiabloDrops:", ""));
                        String place = "0:0:0:";
                        if (DiabloDropsHelper.getDiabloDrops().getItemAPI()
                                .isHelmet(is.getType()))
                            place = place + "4";
                        else if (DiabloDropsHelper.getDiabloDrops()
                                .getItemAPI().isChestPlate(is.getType()))
                            place = place + "3";
                        else if (DiabloDropsHelper.getDiabloDrops()
                                .getItemAPI().isLeggings(is.getType()))
                            place = place + "2";
                        else if (DiabloDropsHelper.getDiabloDrops()
                                .getItemAPI().isBoots(is.getType()))
                            place = place + "1";
                        else
                            place = place + "0";
                        equip(place, is, b.getLivingEntity());
                    }
                    else
                    {
                        equip(s, eb.loaditems.getItem(s), b.getLivingEntity());
                    }
                }
            }
        }
    }
}
