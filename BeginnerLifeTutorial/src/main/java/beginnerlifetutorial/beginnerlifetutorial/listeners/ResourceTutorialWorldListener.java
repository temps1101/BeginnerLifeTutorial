package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.events.TutorialStepEvent;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase.START;
import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase.WHEAT_HARVESTED;
import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType.RESOURCE;

public class ResourceTutorialWorldListener implements Listener {
    private final int REGENERATION_DELAY_MINUTES = 1; // 一分遅らせてブロック再

    @EventHandler
    public void onDestroyBlock(BlockBreakEvent event) {
        if (event.getBlock().getWorld() == TutorialConfig.getResourceLocation().getWorld()) {
            Player player = event.getPlayer();

            if (BeginnerLifeTutorial.getPlayerCache().containsKey(player)) {
                PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerCache().get(player);
                if (playerStatus.getTutorialType() == RESOURCE && playerStatus.getTutorialPhase() == START && event.getBlock().getType() == Material.WHEAT) {
                    playerStatus.setTutorialPhase(WHEAT_HARVESTED);
                    BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);
                    Bukkit.getPluginManager().callEvent(new TutorialStepEvent(player));

                    Block wheat = event.getBlock();
                    int age = ((Ageable) event.getBlock().getBlockData()).getAge();

                    Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                        wheat.setType(Material.WHEAT);
                        ((Ageable) wheat.getBlockData()).setAge(age);
                    }, 1200*REGENERATION_DELAY_MINUTES);
                } else if (!player.hasPermission("ltutorial.permission.admin")) {
                    player.sendMessage(Chat.f("&6本来の資源ワールドなら、このブロックの破壊はできますが、ここはチュートリアル用ワールドなのでできません！", false));
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event) {
        if (event.getBlock().getWorld() == TutorialConfig.getResourceLocation().getWorld()) {
            Player player = event.getPlayer();
            if (!player.hasPermission("ltutorial.permission.admin")) {
                player.sendMessage(Chat.f("&6本来の資源ワールドなら、このブロックの設置はできますが、ここはチュートリアル用ワールドなのでできません！", false));
                event.setCancelled(true);
            }
        }
    }
}
