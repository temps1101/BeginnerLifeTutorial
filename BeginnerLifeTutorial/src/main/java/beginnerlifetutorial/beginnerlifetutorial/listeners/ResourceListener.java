package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialItems;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType;
import beginnerlifetutorial.beginnerlifetutorial.events.StepTutorial;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import com.gmail.nossr50.events.skills.abilities.McMMOPlayerAbilityEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.*;

import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialItems.RESOURCE_PICKAXE;
import static org.bukkit.event.player.PlayerFishEvent.State.CAUGHT_FISH;

public class ResourceListener implements Listener {
    Random random = new Random();
    @EventHandler
    public void onBlockBroke(BlockBreakEvent event) {
        Player player = event.getPlayer();
        PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerStatus(player);

        if (playerStatus == null) {
            return;
        }
        if (playerStatus.getTutorialType() != TutorialType.RESOURCE) {
            return;
        }

        switch (event.getBlock().getType()) { // ブロック壊せないような条件の時はdefaultに流れるようにする。（むやみやたらにbreakを使わないこと）
            case WHEAT:
                if (playerStatus.getTutorialPhase() == 3) {
                    if (((Ageable) event.getBlock().getBlockData()).getAge() != 7) {
                        player.sendTitle(Chat.f("&aヒント", false), Chat.f("&6若い小麦はポイントとして加算されません！", false), 0, 140, 0);
                        event.setCancelled(true);
                    }
                    if (random.nextInt(10) == 5) {
                        playerStatus.setTutorialPhase(4);
                        BeginnerLifeTutorial.changePlayerStatus(player, playerStatus);
                        Bukkit.getPluginManager().callEvent(new StepTutorial(player));
                    }
                    return;
                }
                if (playerStatus.getTutorialPhase() == 11) {
                    return;
                }

            // セマンティックさ皆無だがスッキリしてるからこの方法で行くぜ 👇
            case STONE:
                if (stepOre(player, playerStatus, 5)) {
                    break;
                }
            case COAL_ORE:
                if (stepOre(player, playerStatus, 10)) {
                    break;
                }
            case IRON_ORE:
                if (stepOre(player, playerStatus, 15)) {
                    break;
                }
            case REDSTONE_ORE:
                if (stepOre(player, playerStatus, 20)) {
                    break;
                }
            case GOLD_ORE:
                if (stepOre(player, playerStatus, 25)) {
                    break;
                }
            case DIAMOND_ORE:
                if (stepOre(player, playerStatus, 30)) {
                    break;
                }
            // fin 👆

            default:
                if (!player.hasPermission("tutorial.permission.admin")) {
                    player.sendTitle(Chat.f("&aヒント", false), Chat.f("&6ここはチュートリアル用ワールドなので特定のブロックしか破壊できません！", false), 0, 140, 0);
                    event.setCancelled(true);
                }
        }
    }





    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (!event.getMessage().equals("/ott")) {
            return;
        }

        Player player = event.getPlayer();
        PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerStatus(player);

        if (playerStatus == null) {
            return;
        }
        if (playerStatus.getTutorialType() != TutorialType.RESOURCE) {
            return;
        }

        if (playerStatus.getTutorialPhase() == 20) {
            player.sendMessage(Chat.f("&6[MythicMobs] &aItem &bontime_ticket &awas put in your inventory!", false));
            player.sendMessage(Chat.f("&b10 オンタイムポイントをオンタイムチケット 1 枚に変換しました。", false));
            player.getInventory().addItem(TutorialItems.TUTORIAL_OTT.getItem());

            playerStatus.setTutorialPhase(21);
            BeginnerLifeTutorial.changePlayerStatus(player, playerStatus);
            Bukkit.getPluginManager().callEvent(new StepTutorial(player));

            event.setCancelled(true);
        } else {
            if (!player.hasPermission("tutorial.permission.admin")) {
                player.sendTitle(Chat.f("&aヒント", false), Chat.f("&6チュートリアル中はOTTコマンドは許可されていない時は使えません！", false), 0, 140, 0);
                event.setCancelled(true);
            }
        }
    }





    @EventHandler
    public void onPickaxeTrade(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerStatus(player);

        if (playerStatus == null) {
            return;
        }
        if (playerStatus.getTutorialType() != TutorialType.RESOURCE) {
            return;
        }

        if (event.getInventory().getType() == InventoryType.MERCHANT && player.getInventory().contains(RESOURCE_PICKAXE.getItem())) {
            playerStatus.setTutorialPhase(22);
            BeginnerLifeTutorial.changePlayerStatus(player, playerStatus);
            Bukkit.getPluginManager().callEvent(new StepTutorial(player));
        }
    }




    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (!event.getState().equals(CAUGHT_FISH)) {
            return;
        }

        Player player = (Player) event.getPlayer();
        PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerStatus(player);

        if (playerStatus == null) {
            return;
        }
        if (playerStatus.getTutorialType() != TutorialType.RESOURCE) {
            return;
        }

        playerStatus.setTutorialCounter(playerStatus.getTutorialCounter() + 1);
        BeginnerLifeTutorial.changePlayerStatus(player, playerStatus);

        if (playerStatus.getTutorialCounter() >= 5) {
            playerStatus.setTutorialPhase(28);
            BeginnerLifeTutorial.changePlayerStatus(player, playerStatus);
            Bukkit.getPluginManager().callEvent(new StepTutorial(player));
            playerStatus.setTutorialCounter(0);
        }
    }




    private static List<Player> usingGreenTerraPlayer = new ArrayList<>();
    @EventHandler
    public void onGreenTerra(McMMOPlayerAbilityEvent event) {
        Player player = event.getPlayer();
        if (BeginnerLifeTutorial.getPlayerStatus(player) == null) {
            return;
        }
        PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerStatus(player);
        if (!(event.getSkill() == PrimarySkillType.HERBALISM && playerStatus.getTutorialPhase() == 11)) {
            return;
        }
        if (usingGreenTerraPlayer.contains(event.getPlayer())) {
            playerStatus.setTutorialPhase(12);
            BeginnerLifeTutorial.changePlayerStatus(player, playerStatus);
            Bukkit.getPluginManager().callEvent(new StepTutorial(player));
            usingGreenTerraPlayer.remove(player);
        } else {
            usingGreenTerraPlayer.add(player);
        }
    }




    private boolean stepOre(Player player, PlayerStatus playerStatus, int addAmount) {
        if (playerStatus.getTutorialPhase() == 22) {
            playerStatus.setTutorialCounter(playerStatus.getTutorialCounter() + addAmount);
            BeginnerLifeTutorial.changePlayerStatus(player, playerStatus);

            if (playerStatus.getTutorialCounter() >= 100) {
                playerStatus.setTutorialPhase(23);
                BeginnerLifeTutorial.changePlayerStatus(player, playerStatus);
                Bukkit.getPluginManager().callEvent(new StepTutorial(player));
                playerStatus.setTutorialCounter(0);
            }
            return true;
        } else {
            return false;
        }
    }
}
