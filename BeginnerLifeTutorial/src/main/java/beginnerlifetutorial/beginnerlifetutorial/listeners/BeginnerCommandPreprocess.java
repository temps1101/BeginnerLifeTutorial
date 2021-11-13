package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BeginnerCommandPreprocess implements Listener {

    private final BeginnerLifeTutorial plugin;

    public BeginnerCommandPreprocess(BeginnerLifeTutorial beginnerLifeTutorial) {
        this.plugin = beginnerLifeTutorial;
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        String configWorld = plugin.getConfig().getString("worldName");
        World world = player.getWorld();
        if(world.getName().equals(configWorld)){
            if(e.getMessage().equalsIgnoreCase("/shop")) {
                e.setCancelled(true);
                Inventory inventory = player.getInventory();
                if (inventory.contains(Material.DIAMOND)) {
                    player.sendMessage("既にあなたはダイヤを持っています");
                }else{
                    Inventory shopMenu = Bukkit.createInventory(null, 27, "Menu");
                    ItemStack menu1 = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                    ItemStack menu2 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                    ItemStack menu3 = new ItemStack(Material.BEACON);
                    ItemStack menu4 = new ItemStack(Material.STICK);
                    ItemStack menu5 = new ItemStack(Material.EMERALD);
                    ItemStack menu6 = new ItemStack(Material.WHEAT);
                    ItemStack menu7 = new ItemStack(Material.STONE);
                    ItemStack menu8 = new ItemStack(Material.GLASS);
                    ItemStack menu9 = new ItemStack(Material.OAK_LOG);
                    ItemStack menu10 = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
                    ItemStack menu11 = new ItemStack(Material.HONEY_BLOCK);
                    ItemStack menu12 = new ItemStack(Material.ENCHANTING_TABLE);
                    ItemMeta itemMeta1 = menu1.getItemMeta();
                    ItemMeta itemMeta2 = menu2.getItemMeta();
                    ItemMeta itemMeta3 = menu3.getItemMeta();
                    ItemMeta itemMeta4 = menu4.getItemMeta();
                    ItemMeta itemMeta5 = menu5.getItemMeta();
                    ItemMeta itemMeta6 = menu6.getItemMeta();
                    ItemMeta itemMeta7 = menu7.getItemMeta();
                    ItemMeta itemMeta8 = menu8.getItemMeta();
                    ItemMeta itemMeta9 = menu9.getItemMeta();
                    ItemMeta itemMeta10 = menu10.getItemMeta();
                    ItemMeta itemMeta11 = menu11.getItemMeta();
                    ItemMeta itemMeta12 = menu12.getItemMeta();
                    if(itemMeta1 == null ||
                            itemMeta2 == null ||
                            itemMeta3 == null ||
                            itemMeta4 == null ||
                            itemMeta5 == null ||
                            itemMeta6 == null ||
                            itemMeta7 == null ||
                            itemMeta8 == null ||
                            itemMeta9 == null ||
                            itemMeta10 == null ||
                            itemMeta11 == null ||
                            itemMeta12 == null
                    ) return;
                    itemMeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
                    itemMeta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
                    itemMeta3.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a貴重品"));
                    itemMeta4.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&a雑貨&ゴミ"));
                    itemMeta5.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&a鉱石"));
                    itemMeta6.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&e農作物+α"));
                    itemMeta7.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&6建材"));
                    itemMeta8.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&6色付きブロック系の建材"));
                    itemMeta9.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&b木材"));
                    itemMeta10.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&6スポーンエッグ"));
                    itemMeta11.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&6装飾"));
                    itemMeta12.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&6エンチャント"));
                    menu1.setItemMeta(itemMeta1);
                    menu2.setItemMeta(itemMeta2);
                    menu3.setItemMeta(itemMeta3);
                    menu4.setItemMeta(itemMeta4);
                    menu5.setItemMeta(itemMeta5);
                    menu6.setItemMeta(itemMeta6);
                    menu7.setItemMeta(itemMeta7);
                    menu8.setItemMeta(itemMeta8);
                    menu9.setItemMeta(itemMeta9);
                    menu10.setItemMeta(itemMeta10);
                    menu11.setItemMeta(itemMeta11);
                    menu12.setItemMeta(itemMeta12);
                    int empty = shopMenu.firstEmpty();
                    switch (empty){
                        case 0:
                        case 2:
                        case 6:
                        case 8:
                        case 18:
                        case 20:
                        case 22:
                        case 24:
                        case 26:
                            shopMenu.setItem(0,menu1);
                            shopMenu.setItem(2,menu1);
                            shopMenu.setItem(6,menu1);
                            shopMenu.setItem(8,menu1);
                            shopMenu.setItem(18,menu1);
                            shopMenu.setItem(20,menu1);
                            shopMenu.setItem(22,menu1);
                            shopMenu.setItem(24,menu1);
                            shopMenu.setItem(26,menu1);
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 19:
                        case 21:
                        case 23:
                        case 25:
                            shopMenu.setItem(1,menu2);
                            shopMenu.setItem(3,menu2);
                            shopMenu.setItem(5,menu2);
                            shopMenu.setItem(7,menu2);
                            shopMenu.setItem(19,menu2);
                            shopMenu.setItem(21,menu2);
                            shopMenu.setItem(23,menu2);
                            shopMenu.setItem(25,menu2);
                        case 4:
                            shopMenu.setItem(4,menu3);
                        case 9:
                            shopMenu.setItem(9,menu4);
                        case 10:
                            shopMenu.setItem(10,menu5);
                        case 11:
                            shopMenu.setItem(11,menu6);
                        case 12:
                            shopMenu.setItem(12,menu7);
                        case 13:
                            shopMenu.setItem(13,menu8);
                        case 14:
                            shopMenu.setItem(14,menu9);
                        case 15:
                            shopMenu.setItem(15,menu10);
                        case 16:
                            shopMenu.setItem(16,menu11);
                        case 17:
                            shopMenu.setItem(17,menu12);
                    }
                    player.openInventory(shopMenu);
                }
            }
            if(e.getMessage().equalsIgnoreCase("/ott")) {
                e.setCancelled(true);
                ItemStack itemStack = new ItemStack(Material.PAPER);
                ItemMeta itemMeta = itemStack.getItemMeta();
                if(itemMeta == null) return;
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&aオンタイムチケット"));
                itemStack.setItemMeta(itemMeta);
                Inventory inventory = player.getInventory();
                if (inventory.contains(itemStack)) {
                    ItemMeta itemMeta1 = itemStack.getItemMeta();
                    String itemName = itemMeta1.getDisplayName();
                    if(itemName.equals(ChatColor.translateAlternateColorCodes('&',"&aオンタイムチケット"))){
                        player.sendMessage("既にあなたはオンタイムチケットを持っています");
                    }
                }else{
                    player.getInventory().addItem(itemStack);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"オンタイムチケットを入手しました"));
                }
            }
            if(e.getMessage().equalsIgnoreCase("/sell")) {
                e.setCancelled(true);
                try{
                    int value = plugin.map.get(player);
                    plugin.map.replace(player, value + 1);
                    if(value == 0){
                        Inventory shopMenu = Bukkit.createInventory(null, 54, "Menu §f> §rSell");
                        player.openInventory(shopMenu);
                    }else {
                        return;
                    }
                }catch (NullPointerException nullPointerException){
                    return;
                }
            }if(e.getMessage().equalsIgnoreCase("/smg")){
                e.setCancelled(true);
                Inventory smgMenu = Bukkit.createInventory(null, 9, "§8SELLMMITEM MENU");
                ItemStack menu1 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
                ItemStack menu2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
                ItemStack menu3 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta itemMeta1 = menu1.getItemMeta();
                ItemMeta itemMeta2 = menu2.getItemMeta();
                ItemMeta itemMeta3 = menu3.getItemMeta();
                if(itemMeta1 == null || itemMeta2 == null || itemMeta3 == null ) return;
                itemMeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aSHOPを開く"));
                itemMeta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eSHOP注意点"));
                itemMeta3.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8売却可能アイテム一覧をみる"));
                List<String> lore2 = new ArrayList<String>();
                lore2.add(ChatColor.translateAlternateColorCodes('&', "&dSHOPのインベントリに"));
                lore2.add(ChatColor.translateAlternateColorCodes('&', "&d指定アイテム以外を入れてしまうと"));
                lore2.add(ChatColor.translateAlternateColorCodes('&', "&d一円にもならずアイテムが消えます"));
                lore2.add(ChatColor.translateAlternateColorCodes('&', "&d消えたアイテムに関しては&c補填対象外&dです"));
                itemMeta2.setLore(lore2);
                List<String> lore3 = new ArrayList<String>();
                lore3.add(ChatColor.translateAlternateColorCodes('&', "&d売却可能アイテムは"));
                lore3.add(ChatColor.translateAlternateColorCodes('&', "&3一万円&f"));
                itemMeta3.setLore(lore3);
                menu1.setItemMeta(itemMeta1);
                menu2.setItemMeta(itemMeta2);
                menu3.setItemMeta(itemMeta3);
                smgMenu.setItem(0, menu1);
                smgMenu.setItem(5, menu2);
                smgMenu.setItem(3, menu3);
                Location loc = player.getLocation();
                player.playSound(loc, Sound.BLOCK_CHEST_OPEN, 2, 1);
                player.openInventory(smgMenu);
            }
        }
    }
}
