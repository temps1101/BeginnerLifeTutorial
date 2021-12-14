package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.events.TutorialStepEvent;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import com.gmail.nossr50.events.skills.abilities.McMMOPlayerAbilityEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase.*;
import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType.RESOURCE;

public class ResourceTutorialWorldListener implements Listener {
    private final int REGENERATION_DELAY_MINUTES = 1; // 一分遅らせてブロック再

    private static Map<Player, Long> blockchangeWarnCooldownCache = new HashMap<>();
    @EventHandler
    public void onDestroyBlock(BlockBreakEvent event) {
        if (event.getBlock().getWorld() == TutorialConfig.getResourceLocation().getWorld()) {
            Player player = event.getPlayer();

            if (BeginnerLifeTutorial.getPlayerCache().containsKey(player)) {
                PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerCache().get(player);
                if (playerStatus.getTutorialType() == RESOURCE && (playerStatus.getTutorialPhase() == WAITING_WHEAT_HARVESTED || playerStatus.getTutorialPhase() == WAITING_GREEN_TERRA_FINISHED) && event.getBlock().getType() == Material.WHEAT) {
                    if (playerStatus.getTutorialPhase() == WAITING_WHEAT_HARVESTED) {
                        playerStatus.setTutorialPhase(WHEAT_HARVESTED);
                        BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);
                        Bukkit.getPluginManager().callEvent(new TutorialStepEvent(player));
                    }

                    Block wheat = event.getBlock();
                    int age = ((Ageable) event.getBlock().getBlockData()).getAge();

                    Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                        wheat.setType(Material.WHEAT);
                        ((Ageable) wheat.getBlockData()).setAge(age);
                    }, 20*REGENERATION_DELAY_MINUTES);
                } else if (!player.hasPermission("ltutorial.permission.admin")) {
                    if (blockchangeWarnCooldownCache.containsKey(player)) {
                        int timeRemaining = 5 - (int) (System.currentTimeMillis() - blockchangeWarnCooldownCache.get(player)) / 1000;
                        if (timeRemaining <= 0) {
                            player.sendMessage(Chat.f("&6本来の資源ワールドなら、このブロックの破壊はできますが、ここはチュートリアル用ワールドなのでできません！", false));
                            blockchangeWarnCooldownCache.put(player, System.currentTimeMillis());
                        }
                    } else {
                        player.sendMessage(Chat.f("&6本来の資源ワールドなら、このブロックの破壊はできますが、ここはチュートリアル用ワールドなのでできません！", false));
                        blockchangeWarnCooldownCache.put(player, System.currentTimeMillis());
                    }
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

    @EventHandler
    public void onBlockChanged(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.DIRT && !event.getPlayer().hasPermission("ltutorial.permission.admin")) {
            event.setCancelled(true);
        }
    }

    private static List<Player> usingGreenTerraPlayer = new ArrayList<>();
    @EventHandler
    public void onGreenTerra(McMMOPlayerAbilityEvent event) {
        if (BeginnerLifeTutorial.getPlayerCache().containsKey(event.getPlayer())) {
            Player player = event.getPlayer();
            PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerCache().get(player);
            if (playerStatus.getTutorialPhase() == WAITING_GREEN_TERRA_FINISHED) {
                if (usingGreenTerraPlayer.contains(player)) {
                    playerStatus.setTutorialPhase(GREEN_TERRA_FINISHED);
                    BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);
                    Bukkit.getPluginManager().callEvent(new TutorialStepEvent(player));
                } else {
                    usingGreenTerraPlayer.add(player);
                }
            }
        }
    }
}
