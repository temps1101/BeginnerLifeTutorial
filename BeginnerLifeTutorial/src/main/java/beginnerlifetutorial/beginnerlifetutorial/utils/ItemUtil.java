package beginnerlifetutorial.beginnerlifetutorial.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {
    /**
     *
     * @param type アイテムの種類
     * @param name アイテムにつけたい名前。MessageFormat, Color code対応
     * @return 名付けられたアイテム
     */
    public static ItemStack createItem(Material type, String name) {
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();

        if (name != null) {
            meta.setDisplayName(Chat.f(name, false));
            item.setItemMeta(meta);
        }

        return item;
    }
}