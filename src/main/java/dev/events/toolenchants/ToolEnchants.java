package dev.events.toolenchants;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class ToolEnchants extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    private static boolean isTool(String name) {
        return name.endsWith("_PICKAXE") || name.endsWith("_AXE") || name.endsWith("_SHOVEL")
                || name.endsWith("_HOE") || name.endsWith("_SWORD");
    }

    /** Changing the preview result also covers normal clicks and shift-clicks. */
    @EventHandler
    public void onPrepare(PrepareItemCraftEvent e) {
        ItemStack result = e.getInventory().getResult();
        if (result == null || !isTool(result.getType().name())) return;

        ItemStack out = result.clone();
        ItemMeta meta = out.getItemMeta();
        meta.addEnchant(Enchantment.EFFICIENCY, 2, true);
        meta.addEnchant(Enchantment.SHARPNESS, 1, true);
        out.setItemMeta(meta);
        e.getInventory().setResult(out);
    }
}
