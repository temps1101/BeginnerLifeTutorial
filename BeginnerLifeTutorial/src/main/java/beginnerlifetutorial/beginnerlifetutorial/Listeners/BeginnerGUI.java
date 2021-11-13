package beginnerlifetutorial.beginnerlifetutorial.Listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
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
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getView().getPlayer();
        ItemStack slot = e.getCurrentItem();
        if (slot == null) return;
        World world = player.getWorld();
        String worldName = world.getName();
        String configWorld = plugin.getConfig().getString("worldName");
        if(worldName.equals(configWorld)){
            if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "Menu"))) {
                if (slot.getType() == Material.EMERALD) {
                    ItemMeta itemMeta = slot.getItemMeta();
                    if(itemMeta == null) return;
                    if (itemMeta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&a鉱石"))) {
                        Inventory shopMenu = Bukkit.createInventory(null, 54, "Menu §f> §a鉱石");
                        ItemStack menu5 = new ItemStack(Material.DIAMOND);
                        ItemMeta itemMeta5 = menu5.getItemMeta();
                        if(itemMeta5 == null) return;
                        List<String> lore5 = new ArrayList<String>();
                        lore5.add(ChatColor.translateAlternateColorCodes('&', "&f購入: &c¥5000.0"));
                        lore5.add(ChatColor.translateAlternateColorCodes('&',"&f売却: &c¥5000"));
                        itemMeta5.setLore(lore5);
                        menu5.setItemMeta(itemMeta5);
                        shopMenu.setItem(0,menu5);
                        player.openInventory(shopMenu);
                    }
                } else {
                    e.setCancelled(true);
                }
            }
            if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "Menu &f> &a鉱石"))) {
                if (slot.getType() == Material.DIAMOND) {
                    Inventory shopMenu = Bukkit.createInventory(null, 54, "§c量を指定してください");
                    ItemStack menu1 = new ItemStack(Material.DIAMOND,1);
                    ItemStack menu2 = new ItemStack(Material.DIAMOND,2);
                    ItemStack menu3 = new ItemStack(Material.DIAMOND,4);
                    ItemStack menu4 = new ItemStack(Material.DIAMOND,8);
                    ItemStack menu5 = new ItemStack(Material.DIAMOND,16);
                    ItemStack menu6 = new ItemStack(Material.DIAMOND,32);
                    ItemStack menu7 = new ItemStack(Material.DIAMOND,64);
                    ItemMeta itemMeta1 = menu1.getItemMeta();
                    ItemMeta itemMeta2 = menu2.getItemMeta();
                    ItemMeta itemMeta3 = menu3.getItemMeta();
                    ItemMeta itemMeta4 = menu4.getItemMeta();
                    ItemMeta itemMeta5 = menu5.getItemMeta();
                    ItemMeta itemMeta6 = menu6.getItemMeta();
                    ItemMeta itemMeta7 = menu7.getItemMeta();
                    if(itemMeta1 == null ||
                            itemMeta2 == null ||
                            itemMeta3 == null ||
                            itemMeta4 == null ||
                            itemMeta5 == null ||
                            itemMeta6 == null ||
                            itemMeta7 == null
                    ) return;
                    List<String> lore1 = new ArrayList<>();
                    List<String> lore2 = new ArrayList<>();
                    List<String> lore3 = new ArrayList<>();
                    List<String> lore4 = new ArrayList<>();
                    List<String> lore5 = new ArrayList<>();
                    List<String> lore6 = new ArrayList<>();
                    List<String> lore7 = new ArrayList<>();
                    lore1.add(ChatColor.translateAlternateColorCodes('&', "&f購入: &c¥5000.0"));
                    lore2.add(ChatColor.translateAlternateColorCodes('&', "&f購入: &c¥10000.0"));
                    lore3.add(ChatColor.translateAlternateColorCodes('&', "&f購入: &c¥20000.0"));
                    lore4.add(ChatColor.translateAlternateColorCodes('&', "&f購入: &c¥40000.0"));
                    lore5.add(ChatColor.translateAlternateColorCodes('&', "&f購入: &c¥80000.0"));
                    lore6.add(ChatColor.translateAlternateColorCodes('&', "&f購入: &c¥160000.0"));
                    lore7.add(ChatColor.translateAlternateColorCodes('&', "&f購入: &c¥320000.0"));
                    itemMeta1.setLore(lore1);
                    itemMeta2.setLore(lore2);
                    itemMeta3.setLore(lore3);
                    itemMeta4.setLore(lore4);
                    itemMeta5.setLore(lore5);
                    itemMeta6.setLore(lore6);
                    itemMeta7.setLore(lore7);
                    menu1.setItemMeta(itemMeta1);
                    menu2.setItemMeta(itemMeta2);
                    menu3.setItemMeta(itemMeta3);
                    menu4.setItemMeta(itemMeta4);
                    menu5.setItemMeta(itemMeta5);
                    menu6.setItemMeta(itemMeta6);
                    menu7.setItemMeta(itemMeta7);
                    shopMenu.setItem(19,menu1);
                    shopMenu.setItem(20,menu2);
                    shopMenu.setItem(21,menu3);
                    shopMenu.setItem(22,menu4);
                    shopMenu.setItem(23,menu5);
                    shopMenu.setItem(24,menu6);
                    shopMenu.setItem(25,menu7);
                    player.openInventory(shopMenu);
                }
                e.setCancelled(true);
            }
            if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&c量を指定してください"))) {
                if (slot.getType() == Material.DIAMOND) {
                    int slotNum = e.getSlot();
                    if(slotNum == 19){
                        player.closeInventory();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"[&cGUIShop&f]購入が完了し、お金を支払いました&c5000.0円&fがあなたの口座から引き出されました"));
                        ItemStack menu1 = new ItemStack(Material.DIAMOND,1);
                        player.getInventory().addItem(menu1);
                    }else {
                        e.setCancelled(true);
                    }
                }
                e.setCancelled(true);
            }
            if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&8SELLMMITEM MENU"))) {
                ItemMeta itemMeta = slot.getItemMeta();
                if(itemMeta == null) return;
                if (slot.getType() == Material.GREEN_STAINED_GLASS_PANE) {
                    if (itemMeta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aSHOPを開く"))) {
                        Inventory smgMenu = Bukkit.createInventory(null, 9, "§8SELLMMITEM SHOP");
                        Location loc = player.getLocation();
                        player.playSound(loc,Sound.BLOCK_CHEST_OPEN, 2, 1);
                        player.openInventory(smgMenu);
                    }
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }
}
