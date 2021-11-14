package beginnerlifetutorial.beginnerlifetutorial.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemUtil {
    /**
     *
     * @param type アイテムの種類
     * @param name アイテムにつけたい名前。MessageFormat, Color code対応
     * @param lore アイテムの説明文
     * @return 名付けられたアイテム
     */
    public static ItemStack createItem(Material type, String name, String... lore) {
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();

        if (name != null) {
            meta.setDisplayName(Chat.f(name, false));
        }

        if (lore.length != 0) {
            for (int i=0 ; i<lore.length ; i++) { // TODO このやり方をするやつは敗者
                lore[i] = Chat.f(lore[i], false);
            }
            meta.setLore(Arrays.asList(lore));
        }

        item.setItemMeta(meta);

        return item;
    }
}