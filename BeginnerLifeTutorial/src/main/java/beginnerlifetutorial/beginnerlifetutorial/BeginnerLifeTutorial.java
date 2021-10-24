package beginnerlifetutorial.beginnerlifetutorial;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class BeginnerLifeTutorial extends JavaPlugin implements Listener {

    public HashMap<Player, Integer> map = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new TutorialButtonSelect(this), this);
        Bukkit.getPluginManager().registerEvents(new BeginnerLifeJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new BeginnerCommandPreprocess(this), this);
        Bukkit.getPluginManager().registerEvents(new BeginnerGUI(this), this);
        getCommand("ltutorial").setExecutor(new TutorialCommand(this));
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        Inventory inventory = e.getInventory();
        Player player = (Player) e.getPlayer();
        World world = player.getWorld();
        String worldName = world.getName();
        String configWorld = getConfig().getString("worldName");
        if(worldName.equals(configWorld)){
            if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "Menu &f> &rSell"))) {
                ItemStack[] contents = inventory.getContents();
                for (int i = 0; i < 53; i++) {
                    ItemStack content = contents[i];
                    if (content == null) {
                        continue;
                    }
                    Material material = content.getType();
                    if(material == Material.COBBLESTONE){
                        if(content.getAmount() == 64){
                            player.sendMessage("64個の丸石を回収し10万円が報酬として付与されました");
                            getServer().dispatchCommand(getServer().getConsoleSender(),"mm items give " + player.getName() + " yukiti_ticket 10");
                        }else{
                            player.getInventory().addItem(content);
                        }
                    }
                }
            }
            if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&8SELLMMITEM SHOP"))) {
                ItemStack[] contents = inventory.getContents();
                for (int i = 0; i < 9; i++) {
                    ItemStack content = contents[i];
                    if (content == null) {
                        continue;
                    }
                    ItemMeta itemMeta = content.getItemMeta();
                    if(itemMeta == null) return;
                    Material material = content.getType();
                    if(material == Material.PAPER){
                        if(content.getAmount() == 10){
                            if (itemMeta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&3一万円"))) {
                                getServer().dispatchCommand(getServer().getConsoleSender(),"mm items give " + player.getName() + " obsidian_sword 1");
                                getServer().dispatchCommand(getServer().getConsoleSender(),"mm items give " + player.getName() + " obsidian_chestplate 1");
                            }
                        }
                    }
                }
                Location loc = player.getLocation();
                player.playSound(loc,Sound.ENTITY_PLAYER_LEVELUP, 2, 1);
            }
        }
    }
}
