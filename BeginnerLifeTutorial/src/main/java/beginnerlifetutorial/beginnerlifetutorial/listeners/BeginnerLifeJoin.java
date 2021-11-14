package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BeginnerLifeJoin implements Listener {

    private final BeginnerLifeTutorial plugin;

    public BeginnerLifeJoin(BeginnerLifeTutorial beginnerLifeTutorial) {
        this.plugin = beginnerLifeTutorial;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        World world = player.getWorld();
        String worldName = world.getName();
        String configWorld = plugin.getConfig().getString("worldName");
        String joinText = plugin.getConfig().getString("joinText");
        plugin.map.put(player, 0);
        if(worldName.equals(configWorld)){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "" + joinText));
        }
    }
}
