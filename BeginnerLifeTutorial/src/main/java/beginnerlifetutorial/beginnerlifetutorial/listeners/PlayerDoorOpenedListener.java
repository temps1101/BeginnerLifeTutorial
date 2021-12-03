package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.events.TutorialStartEvent;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial.getPlugin;
import static beginnerlifetutorial.beginnerlifetutorial.events.TutorialStartEvent.TutorialType.*;

public class PlayerDoorOpenedListener implements Listener {
    private Material DOOR = Material.DARK_OAK_DOOR;
    @EventHandler
    public void onDoorOpened(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock.getType() == DOOR) {
                Player player = event.getPlayer();
                TutorialStartEvent.TutorialType tutorialType;

                if (isDoorSameCoordinate(clickedBlock.getLocation(), TutorialConfig.getResourceDoorLocation()) ) {
                    tutorialType = RESOURCE;
                } else if (isDoorSameCoordinate(clickedBlock.getLocation(), TutorialConfig.getShopMoneyDoorLocation()) ) {
                    tutorialType = SHOPMONEY;
                } else if (isDoorSameCoordinate(clickedBlock.getLocation(), TutorialConfig.getDungeonDoorLocation()) ) {
                    tutorialType = DUNGEON;
                } else if (isDoorSameCoordinate(clickedBlock.getLocation(), TutorialConfig.getRaidDoorLocation()) ) {
                    tutorialType = RAID;
                } else {
                    return;
                }

                long ticks = 20 * (long) 3; // 三秒待つってことです
                Bukkit.getScheduler().scheduleSyncDelayedTask(getPlugin(), () -> {
                    Bukkit.getPluginManager().callEvent(new TutorialStartEvent(player, tutorialType));
                    event.setCancelled(true);
                }, ticks);
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
