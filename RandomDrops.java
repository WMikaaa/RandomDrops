package com.mika.randomdrops;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDrops extends JavaPlugin implements Listener {

    private final List<Material> dropList = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("RandomDrops aktiviert!");

        for (Material mat : Material.values()) {
            if (mat.isItem()) {
                dropList.add(mat);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setDropItems(false);

        Material randomMaterial = dropList.get(random.nextInt(dropList.size()));
        ItemStack randomDrop = new ItemStack(randomMaterial, 1);

        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), randomDrop);
    }
}
