package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.events.TutorialStepEvent;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase.START;
import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase.WHEAT_HARVESTED;
import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType.RESOURCE;

public class PlayerDestroyBlockListener implements Listener {
    private final int REGENERATION_DELAY_MINUTES = 1; // 一分遅らせてブロック再生
    @EventHandler
    public void onDestroyBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (BeginnerLifeTutorial.getPlayerCache().containsKey(player)) {
            PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerCache().get(player);
            if (playerStatus.getTutorialType() == RESOURCE && playerStatus.getTutorialPhase() == START && event.getBlock().getType() == Material.WHEAT) {
                playerStatus.setTutorialPhase(WHEAT_HARVESTED);
                BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);
                Bukkit.getPluginManager().callEvent(new TutorialStepEvent(player));
            }
        }

        if (event.getBlock().getWorld() == TutorialConfig.getResourceLocation().getWorld()) {
            Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> event.setCancelled(true), 1200*REGENERATION_DELAY_MINUTES);
        }
    }
}