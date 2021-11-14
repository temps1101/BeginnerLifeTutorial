package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.Inventories;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.ItemUtil;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BeginnerGUI implements Listener {

    private final BeginnerLifeTutorial plugin;

    public BeginnerGUI(BeginnerLifeTutorial beginnerLifeTutorial) {
        this.plugin = beginnerLifeTutorial;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getView().getPlayer();
        ItemStack clickedItem = event.getCurrentItem();
        String worldName = player.getWorld().getName();
        String configWorld = plugin.getConfig().getString("worldName");

        if (worldName.equals(configWorld)) {
            if (event.getView().getTitle().equals(Chat.f("Menu", false))) {
                if (clickedItem.getType() == Material.EMERALD) {
                    ItemMeta itemMeta = clickedItem.getItemMeta();
                    if (itemMeta.getDisplayName().equals(Chat.f("&a鉱石", false))) {
                        Inventory shopMenu = Bukkit.createInventory(null, 54, "Menu §f> §a鉱石");
                        ItemStack diamond = ItemUtil.createItem(Material.DIAMOND, null, 1, "&f購入: &c¥5000.0", "&f売却: &c¥5000");
                        shopMenu.setItem(0,diamond);
                        player.openInventory(shopMenu);
                    }
                } else {
                    event.setCancelled(true);
                }
            }
            if (event.getView().getTitle().equals(Chat.f("Menu &f> &a鉱石", false))) {
                if (clickedItem.getType() == Material.DIAMOND) {
                    player.openInventory(Inventories.getDiamondAmountMenu());
                }
                event.setCancelled(true);
            }
            if (event.getView().getTitle().equals(Chat.f("&c量を指定してください", false))) {
                if (clickedItem.getType() == Material.DIAMOND) {
                    int slotNum = event.getSlot();
                    if(slotNum == 19){
                        player.closeInventory();
                        player.sendMessage(Chat.f("[&cGUIShop&f]購入が完了し、お金を支払いました&c5000.0円&fがあなたの口座から引き出されました", false));
                        ItemStack diamond = new ItemStack(Material.DIAMOND,1);
                        player.getInventory().addItem(diamond);
                    }else {
                        event.setCancelled(true);
                    }
                }
                event.setCancelled(true);
            }
            if (event.getView().getTitle().equals(Chat.f("&8SELLMMITEM MENU", false))) {
                if (clickedItem.getType() == Material.GREEN_STAINED_GLASS_PANE) {
                    ItemMeta itemMeta = clickedItem.getItemMeta();
                    if (itemMeta.getDisplayName().equals(Chat.f("&aSHOPを開く", false))) {
                        Inventory smgMenu = Bukkit.createInventory(null, 9, "§8SELLMMITEM SHOP");
                        player.openInventory(smgMenu);
                        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 2, 1);
                    }
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }
}
