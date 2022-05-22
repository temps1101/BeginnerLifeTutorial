package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType;
import beginnerlifetutorial.beginnerlifetutorial.events.StepTutorial;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class TutorialHomeListener implements Listener {
    private FileConfiguration configuration;
    public TutorialHomeListener(FileConfiguration configuration) {
        this.configuration = configuration;
    }

    @EventHandler
    public void onBlockClicked(PlayerInteractEvent event) {
        if (event.getClickedBlock().getType() != Material.DARK_OAK_DOOR) {
            return;
        }

        TutorialType tutorialType = null;

        if (doorOpened(event.getClickedBlock().getLocation(), configuration.getLocation("resourceDoorLocation"))) {
            tutorialType = TutorialType.RESOURCE;
        }

        if (doorOpened(event.getClickedBlock().getLocation(), configuration.getLocation("shopMoneyDoorLocation"))) {
            tutorialType = TutorialType.SHOPMONEY;
        }

        if (doorOpened(event.getClickedBlock().getLocation(), configuration.getLocation("dungeonDoorLocation"))) {
            tutorialType = TutorialType.DUNGEON;
        }

        if (doorOpened(event.getClickedBlock().getLocation(), configuration.getLocation("raidDoorLocation"))) {
            tutorialType = TutorialType.RAID;
        }

        if (tutorialType != null) {
            BeginnerLifeTutorial.setPlayerStatus(event.getPlayer(), new PlayerStatus(event.getPlayer(), tutorialType));
            Bukkit.getPluginManager().callEvent(new StepTutorial(event.getPlayer()));
        }
    }

    private boolean doorOpened(Location doorBottomLocation, Location clickedLocation) {
        return clickedLocation.getBlockX() == doorBottomLocation.getBlockX() &&
                (clickedLocation.getBlockY() == doorBottomLocation.getBlockY() || clickedLocation.getBlockY() == doorBottomLocation.getBlockY() + 1) &&
                clickedLocation.getBlockZ() == doorBottomLocation.getBlockZ();
    }
}
