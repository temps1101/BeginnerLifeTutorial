package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerDoorOpenedListener implements Listener {
    private Material DOOR = Material.DARK_OAK_DOOR;
    @EventHandler
    public void onDoorOpened(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && clickedBlock.getType() == DOOR) {
            Player player = event.getPlayer();

            if (isDoorSameCoordinate(clickedBlock.getLocation(), TutorialConfig.getResourceDoorLocation()) ) {
                player.sendMessage("資源を開けましたねん？"); // TODO デバッグだからあとでとる。
                event.setCancelled(true);
                // 資源チュートリアルにワープ
            } else if (isDoorSameCoordinate(clickedBlock.getLocation(), TutorialConfig.getShopMoneyDoorLocation()) ) {
                player.sendMessage("ショップ系を開けましたねん？"); // TODO デバッグだからあとでとる。
                event.setCancelled(true);
                // ショップ&マネーチュートリアルにワープ
            } else if (isDoorSameCoordinate(clickedBlock.getLocation(), TutorialConfig.getDungeonDoorLocation()) ) {
                player.sendMessage("ダンジョンを開けましたねん？"); // TODO デバッグだからあとでとる。
                event.setCancelled(true);
                // ダンジョンチュートリアルにワープ
            } else if (isDoorSameCoordinate(clickedBlock.getLocation(), TutorialConfig.getRaidDoorLocation()) ) {
                player.sendMessage("レイドを開けましたねん？"); // TODO デバッグだからあとでとる。
                event.setCancelled(true);
                // レイドチュートリアルにワープ
            }
        }
    }

    private boolean isDoorSameCoordinate(Location location1, Location location2) {
        return location1.getWorld().getName().equals(location2.getWorld().getName()) &&
               location1.getBlockX() == location2.getBlockX() &&
               location1.getBlockZ() == location2.getBlockZ() &&
               Math.abs(location1.getY() - location2.getY()) <= 1;
    }
}
