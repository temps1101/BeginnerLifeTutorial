package beginnerlifetutorial.beginnerlifetutorial;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class TutorialButtonSelect implements Listener {

    private final BeginnerLifeTutorial plugin;

    public TutorialButtonSelect(BeginnerLifeTutorial beginnerLifeTutorial) {
        this.plugin = beginnerLifeTutorial;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        if (block == null) return;
        World world = block.getWorld();
        String configWorld = plugin.getConfig().getString("worldName");
        if (world.getName().equals(configWorld)){
            if (block.getType() == Material.ACACIA_BUTTON){
                player.sendMessage("ルールに同意しました");
                plugin.getLogger().info(player.getName() + "がルールに同意しました");
            }
        }
    }
}
