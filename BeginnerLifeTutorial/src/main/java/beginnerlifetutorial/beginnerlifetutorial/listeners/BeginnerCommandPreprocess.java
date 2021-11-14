package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.Inventories;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.ItemUtil;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BeginnerCommandPreprocess implements Listener {

    private final BeginnerLifeTutorial plugin;
    private Inventory shopMenu = Inventories.getShopInventory();
    private Inventory smgMenu = Inventories.getSmgMenu();

    public BeginnerCommandPreprocess(BeginnerLifeTutorial beginnerLifeTutorial) {
        this.plugin = beginnerLifeTutorial;
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        String configWorld = plugin.getConfig().getString("worldName");
        World world = player.getWorld();
        if (world.getName().equals(configWorld)) {
            if (e.getMessage().equalsIgnoreCase("/shop")) {
                e.setCancelled(true);
                Inventory inventory = player.getInventory();
                if (inventory.contains(Material.DIAMOND)) {
                    player.sendMessage(Chat.f("&c既にあなたはダイヤを持っています", false));
                }else {
                    player.openInventory(shopMenu);
                }
            }
            if (e.getMessage().equalsIgnoreCase("/ott")) {
                e.setCancelled(true);
                ItemStack ontimeTicket = ItemUtil.createItem(Material.PAPER, "&aオンタイムチケット");
                Inventory inventory = player.getInventory();
                if (inventory.contains(ontimeTicket)) {
                    player.sendMessage(Chat.f("&c既にあなたはオンタイムチケットを持っています", false));
                } else {
                    player.getInventory().addItem(ontimeTicket);
                    player.sendMessage(Chat.f("オンタイムチケットを入手しました", false)); // TODO いろ変更
                }
            }
            if (e.getMessage().equalsIgnoreCase("/sell")) {
                e.setCancelled(true);
                try {
                    int value = plugin.map.get(player);
                    plugin.map.replace(player, value + 1);
                    if (value == 0) {
                        Inventory shopMenu = Bukkit.createInventory(null, 54, "Menu §f> §rSell");
                        player.openInventory(shopMenu);
                    } else {
                        return;
                    }
                } catch (NullPointerException nullPointerException) {
                    return;
                }
            } if(e.getMessage().equalsIgnoreCase("/smg")) {
                e.setCancelled(true);

                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 2, 1);
                player.openInventory(smgMenu);
            }
        }
    }
}
